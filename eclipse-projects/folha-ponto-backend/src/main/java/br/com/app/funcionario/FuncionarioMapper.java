package br.com.app.funcionario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.querydsl.core.Tuple;

import br.com.app.empresa.dao.EmpresaEntity;
import br.com.app.funcionario.dao.FuncionarioEntity;
import br.com.app.funcionario.dto.FuncionarioDto;
import br.com.app.funcionario.dto.LancamentosByFuncionarioOutput;
import br.com.app.funcionario.dto.LancamentosIncorretosFuncByEmpresaOutput;
import br.com.app.infra.locale.Resource;
import br.com.app.lancamento.dto.LancamentoFuncOutput;
import br.com.app.usuario.dao.UsuarioEntity;

@Component
public class FuncionarioMapper {

    @Autowired
    private Resource resource;

    public FuncionarioEntity toEntity(FuncionarioDto dto, EmpresaEntity empresa, UsuarioEntity usuario) {
        FuncionarioEntity entity = new FuncionarioEntity();

        merge(entity, empresa, usuario, dto);

        return entity;
    }

    public void merge(FuncionarioEntity entity, EmpresaEntity empresa, UsuarioEntity usuario, FuncionarioDto dto) {
        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setRg(dto.getRg());
        entity.setValorHora(dto.getValorHora());
        entity.setQtHorasTrabalhoDia(dto.getQtHorasTrabalhoDia());
        entity.setQtHorasAlmoco(dto.getQtHorasAlmoco());
        entity.setEmpresa(empresa);
        entity.setUsuario(usuario);
    }

    public FuncionarioDto toDto(FuncionarioEntity entity) {
        FuncionarioDto dto = new FuncionarioDto();

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCpf(entity.getCpf());
        dto.setRg(entity.getRg());
        dto.setValorHora(entity.getValorHora());
        dto.setQtHorasTrabalhoDia(entity.getQtHorasTrabalhoDia());
        dto.setQtHorasAlmoco(entity.getQtHorasAlmoco());
        dto.setEmpresaId(entity.getEmpresa().getId());
        dto.setEmail(entity.getUsuario().getEmail());
        dto.setSituacao(entity.getUsuario().getSituacao().name());
        dto.setPerfil(entity.getUsuario().getPerfil().name());

        return dto;
    }

    public LancamentosByFuncionarioOutput toLancamentosByFuncionarioOutput(FuncionarioEntity entity, List<LancamentoFuncOutput> lancamentosFunc) {
        LancamentosByFuncionarioOutput output = new LancamentosByFuncionarioOutput();

        output.setFuncionarioId(entity.getId());
        output.setNome(entity.getNome());
        output.setCpf(entity.getCpf());
        output.setRg(entity.getRg());
        output.setEmail(entity.getUsuario().getEmail());
        output.setEmpresaId(entity.getEmpresa().getId());
        output.setNomeEmpresa(entity.getEmpresa().getNome());
        output.setCnpjEmpresa(entity.getEmpresa().getCnpj());
        output.setLancamentosFunc(lancamentosFunc);

        return output;
    }

    public LancamentosIncorretosFuncByEmpresaOutput toLancamentosIncorretosFuncByEmpresaOutput(Tuple funcionarioDataLanc) {
        LancamentosIncorretosFuncByEmpresaOutput output = new LancamentosIncorretosFuncByEmpresaOutput();

        long qt = funcionarioDataLanc.get(3, Long.class);
        Object[] parameters = { //
                funcionarioDataLanc.get(0, String.class), //
                funcionarioDataLanc.get(1, String.class), //
                qt, qt == 1 ? 0 : 1, //
                funcionarioDataLanc.get(2, Integer.class)// 
        };
        String message = resource.getResource("app.Lancamentos_incorretos_func", parameters);

        output.setMessage(message);

        return output;
    }

}
