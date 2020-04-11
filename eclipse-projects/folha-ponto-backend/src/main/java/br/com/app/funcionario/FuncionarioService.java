package br.com.app.funcionario;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.querydsl.core.Tuple;

import br.com.app.empresa.EmpresaService;
import br.com.app.empresa.dao.EmpresaEntity;
import br.com.app.funcionario.dao.FuncionarioEntity;
import br.com.app.funcionario.dao.FuncionarioRepository;
import br.com.app.funcionario.dto.FuncionarioDto;
import br.com.app.funcionario.dto.LancamentosByFuncionarioOutput;
import br.com.app.funcionario.dto.LancamentosIncorretosFuncByEmpresaOutput;
import br.com.app.lancamento.LancamentoService;
import br.com.app.lancamento.dao.LancamentoEntity;
import br.com.app.usuario.UsuarioService;
import br.com.app.usuario.dao.UsuarioEntity;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private FuncionarioMapper mapper;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LancamentoService lancamentoService;

    @Transactional
    public long create(FuncionarioDto dto) throws Exception {
        EmpresaEntity empresa = empresaService.getEmpresa(dto.getEmpresaId());
        if (empresa == null) {
            throw new Exception("A empresa informada não existe");
        }

        UsuarioEntity usuario = usuarioService.create(dto.getEmail(), dto.getSenha(), dto.getSituacao(), dto.getPerfil());
        FuncionarioEntity funcionario = mapper.toEntity(dto, empresa, usuario);

        repository.save(funcionario);

        return funcionario.getId();
    }

    @Transactional
    public boolean update(FuncionarioDto dto) throws Exception {
        if (dto.getId() < 1) {
            throw new Exception("ID do funcionário inválido");
        }

        Optional<FuncionarioEntity> funcionarioOpt = repository.findById(dto.getId());
        if (funcionarioOpt.isPresent()) {
            FuncionarioEntity funcionario = funcionarioOpt.get();

            EmpresaEntity empresa = empresaService.getEmpresa(dto.getEmpresaId());
            if (empresa == null) {
                throw new Exception("A empresa informada não existe");
            }

            UsuarioEntity usuario = usuarioService.update(funcionario.getUsuario().getId(), dto.getEmail(), dto.getSenha(), dto.getSituacao(), dto.getPerfil());
            mapper.merge(funcionario, empresa, usuario, dto);

            repository.save(funcionario);

            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public FuncionarioDto retrieve(String cpf) {
        FuncionarioEntity funcionario = repository.findByCpf(cpf);

        if (funcionario == null) {
            return null;
        }

        return mapper.toDto(funcionario);
    }

    @Transactional
    public boolean delete(long id) throws Exception {
        if (id < 1) {
            throw new Exception("ID do funcionário inválido");
        }

        repository.deleteById(id);
        return true;
    }

    @Transactional(readOnly = true)
    public List<FuncionarioDto> list() {
        List<FuncionarioEntity> funcionarios = repository.findAll();

        if (CollectionUtils.isEmpty(funcionarios)) {
            return new ArrayList<>();
        }

        return funcionarios.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FuncionarioEntity getFuncionario(long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public LancamentosByFuncionarioOutput listLancamentosFuncPorData(String cpf, String data) {
        FuncionarioEntity funcionario = repository.findByCpf(cpf);

        if (funcionario == null) {
            return null;
        }

        LocalDate date = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Instant dataIni = date.atStartOfDay().atOffset(ZoneOffset.UTC).toInstant();
        Instant dataFim = date.atTime(23, 59, 59).atOffset(ZoneOffset.UTC).toInstant();

        List<LancamentoEntity> lancamentos = funcionario.getLancamentos().stream() //
                .filter(f -> f.getData().toEpochMilli() >= dataIni.toEpochMilli() && f.getData().toEpochMilli() <= dataFim.toEpochMilli()) //
                .collect(Collectors.toList());

        return mapper.toLancamentosByFuncionarioOutput(funcionario, lancamentoService.toListLancamentoFuncOutput(lancamentos));
    }

    @Transactional(readOnly = true)
    public List<LancamentosIncorretosFuncByEmpresaOutput> listLancamentosIncorretosFuncPorEmpresa(String cnpj, String data) {
        LocalDate referenceDate = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        List<Tuple> funcionariosDataLanc = repository.findByEmpresaWithIncorrectLancamentos(cnpj, referenceDate);

        if (CollectionUtils.isEmpty(funcionariosDataLanc)) {
            return new ArrayList<>();
        }

        return funcionariosDataLanc.stream().map(mapper::toLancamentosIncorretosFuncByEmpresaOutput).collect(Collectors.toList());
    }
}
