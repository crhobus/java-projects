package br.com.app.funcionario;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.querydsl.core.Tuple;

import br.com.app.CommonBaseTest;
import br.com.app.common.builder.EmpresaEntityBuilder;
import br.com.app.common.builder.FuncionarioDtoBuilder;
import br.com.app.common.builder.FuncionarioEntityBuilder;
import br.com.app.common.builder.UsuarioEntityBuilder;
import br.com.app.empresa.dao.EmpresaEntity;
import br.com.app.funcionario.dao.FuncionarioEntity;
import br.com.app.funcionario.dto.FuncionarioDto;
import br.com.app.funcionario.dto.LancamentosByFuncionarioOutput;
import br.com.app.funcionario.dto.LancamentosIncorretosFuncByEmpresaOutput;
import br.com.app.funcionario.dto.SalarioByFuncionarioOutput;
import br.com.app.infra.locale.Resource;
import br.com.app.lancamento.dto.LancamentoFuncOutput;
import br.com.app.usuario.dao.UsuarioEntity;
import br.com.app.usuario.dto.PerfilEnum;
import br.com.app.usuario.dto.SituacaoUserEnum;

public class FuncionarioMapperTest extends CommonBaseTest {

    @Mock
    private Resource resource;

    @InjectMocks
    private FuncionarioMapper mapper;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toEntity() {
        FuncionarioDto dto = FuncionarioDtoBuilder.create().withNome("Fulano Teste").withCpf("12345678900").withRg("123456789").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresaId(1L).withEmail("teste@teste.com.br").withSenha("123456@abc").withSituacao(SituacaoUserEnum.ATIVO.toString()).withPerfil(PerfilEnum.ROLE_ADMIN.toString()).build();
        EmpresaEntity empresa = new EmpresaEntity();
        UsuarioEntity usuario = new UsuarioEntity();

        FuncionarioEntity result = mapper.toEntity(dto, empresa, usuario);

        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo(dto.getNome());
        assertThat(result.getCpf()).isEqualTo(dto.getCpf());
        assertThat(result.getRg()).isEqualTo(dto.getRg());
        assertThat(result.getValorHora()).isEqualTo(dto.getValorHora());
        assertThat(result.getQtHorasTrabalhoDia()).isEqualTo(dto.getQtHorasTrabalhoDia());
        assertThat(result.getQtHorasAlmoco()).isEqualTo(dto.getQtHorasAlmoco());
        assertThat(result.getEmpresa()).isSameAs(empresa);
        assertThat(result.getUsuario()).isEqualTo(usuario);
    }

    @Test
    public void merge() {
        FuncionarioDto dto = FuncionarioDtoBuilder.create().withNome("Fulano Teste").withCpf("12345678900").withRg("123456789").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresaId(1L).withEmail("teste@teste.com.br").withSenha("123456@abc").withSituacao(SituacaoUserEnum.ATIVO.toString()).withPerfil(PerfilEnum.ROLE_ADMIN.toString()).build();
        EmpresaEntity empresa = new EmpresaEntity();
        UsuarioEntity usuario = new UsuarioEntity();
        FuncionarioEntity funcionario = FuncionarioEntityBuilder.create(1L).withNome("Fulano Teste").withCpf("80496539000").withRg("261433556").withValorHora(new BigDecimal(9.0)).withQtHorasTrabalhoDia(new BigDecimal(8)).withQtHorasAlmoco(new BigDecimal(1)).withEmpresa(new EmpresaEntity()).withUsuario(new UsuarioEntity()).build();

        mapper.merge(funcionario, empresa, usuario, dto);

        assertThat(funcionario.getNome()).isEqualTo(dto.getNome());
        assertThat(funcionario.getCpf()).isEqualTo(dto.getCpf());
        assertThat(funcionario.getRg()).isEqualTo(dto.getRg());
        assertThat(funcionario.getValorHora()).isEqualTo(dto.getValorHora());
        assertThat(funcionario.getQtHorasTrabalhoDia()).isEqualTo(dto.getQtHorasTrabalhoDia());
        assertThat(funcionario.getQtHorasAlmoco()).isEqualTo(dto.getQtHorasAlmoco());
        assertThat(funcionario.getEmpresa()).isSameAs(empresa);
        assertThat(funcionario.getUsuario()).isEqualTo(usuario);
    }

    @Test
    public void toDto() {
        EmpresaEntity empresa = EmpresaEntityBuilder.create(1L).build();
        UsuarioEntity usuario = UsuarioEntityBuilder.create().withEmail("teste@teste.com.br").withSenha("123456@abc").withSituacao(SituacaoUserEnum.ATIVO).withPerfil(PerfilEnum.ROLE_USUARIO).build();
        FuncionarioEntity funcionario = FuncionarioEntityBuilder.create(1L).withNome("Fulano Teste").withCpf("80496539000").withRg("261433556").withValorHora(new BigDecimal(9.0)).withQtHorasTrabalhoDia(new BigDecimal(8)).withQtHorasAlmoco(new BigDecimal(1)).withEmpresa(empresa).withUsuario(usuario).build();

        FuncionarioDto result = mapper.toDto(funcionario);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(funcionario.getId());
        assertThat(result.getNome()).isEqualTo(funcionario.getNome());
        assertThat(result.getCpf()).isEqualTo(funcionario.getCpf());
        assertThat(result.getRg()).isEqualTo(funcionario.getRg());
        assertThat(result.getValorHora()).isEqualTo(funcionario.getValorHora());
        assertThat(result.getQtHorasTrabalhoDia()).isEqualTo(funcionario.getQtHorasTrabalhoDia());
        assertThat(result.getQtHorasAlmoco()).isEqualTo(funcionario.getQtHorasAlmoco());
        assertThat(result.getEmpresaId()).isEqualTo(funcionario.getEmpresa().getId());
        assertThat(result.getEmail()).isEqualTo(funcionario.getUsuario().getEmail());
        assertThat(result.getSituacao()).isEqualTo(funcionario.getUsuario().getSituacao());
        assertThat(result.getPerfil()).isEqualTo(funcionario.getUsuario().getPerfil());
    }

    @Test
    public void toLancamentosByFuncionarioOutput() {
        EmpresaEntity empresa = EmpresaEntityBuilder.create(1L).build();
        UsuarioEntity usuario = UsuarioEntityBuilder.create().withEmail("teste@teste.com.br").withSenha("123456@abc").withSituacao(SituacaoUserEnum.ATIVO).withPerfil(PerfilEnum.ROLE_USUARIO).build();
        FuncionarioEntity funcionario = FuncionarioEntityBuilder.create(1L).withNome("Fulano Teste").withCpf("80496539000").withRg("261433556").withValorHora(new BigDecimal(9.0)).withQtHorasTrabalhoDia(new BigDecimal(8)).withQtHorasAlmoco(new BigDecimal(1)).withEmpresa(empresa).withUsuario(usuario).build();

        LancamentoFuncOutput lancamentoFuncOutput = new LancamentoFuncOutput();
        List<LancamentoFuncOutput> listLancamentosFunc = List.of(lancamentoFuncOutput);

        LancamentosByFuncionarioOutput result = mapper.toLancamentosByFuncionarioOutput(funcionario, listLancamentosFunc);

        assertThat(result).isNotNull();
        assertThat(result.getFuncionarioId()).isEqualTo(funcionario.getId());
        assertThat(result.getNome()).isEqualTo(funcionario.getNome());
        assertThat(result.getCpf()).isEqualTo(funcionario.getCpf());
        assertThat(result.getRg()).isEqualTo(funcionario.getRg());
        assertThat(result.getEmail()).isEqualTo(funcionario.getUsuario().getEmail());
        assertThat(result.getEmpresaId()).isEqualTo(funcionario.getEmpresa().getId());
        assertThat(result.getNomeEmpresa()).isEqualTo(funcionario.getEmpresa().getNome());
        assertThat(result.getCnpjEmpresa()).isEqualTo(funcionario.getEmpresa().getCnpj());
        assertThat(result.getLancamentosFunc()).isNotEmpty();
        assertThat(result.getLancamentosFunc()).hasSize(1);
        assertThat(result.getLancamentosFunc().get(0)).isSameAs(lancamentoFuncOutput);
    }

    @Test
    public void toLancamentosIncorretosFuncByEmpresaOutput() {
        Tuple funcionarioDataLanc = Mockito.mock(Tuple.class);

        when(funcionarioDataLanc.get(0, String.class)).thenReturn("123.456.789.00");
        when(funcionarioDataLanc.get(1, String.class)).thenReturn("Teste");
        when(funcionarioDataLanc.get(2, Integer.class)).thenReturn(15);
        when(funcionarioDataLanc.get(3, Long.class)).thenReturn(3L);

        String message = "mensagem de teste";
        when(resource.getResource(refEq("app.Lancamentos_incorretos_func"), refEq(new Object[4]))).thenReturn(message);

        LancamentosIncorretosFuncByEmpresaOutput result = mapper.toLancamentosIncorretosFuncByEmpresaOutput(funcionarioDataLanc);

        assertThat(result).isNotNull();
        assertThat(result.getMessage()).isEqualTo(message);
    }

    @Test
    public void toSalarioByFuncionarioOutput() {
        Object[] row = { "12345678900", "Teste", new BigDecimal(0), new BigDecimal(50.697) };

        SalarioByFuncionarioOutput result = mapper.toSalarioByFuncionarioOutput(row);

        assertThat(result).isNotNull();

        assertThat(result.getCpf()).isEqualTo("12345678900");
        assertThat(result.getNome()).isEqualTo("Teste");
        assertThat(result.getPerfil()).isEqualTo(PerfilEnum.ROLE_ADMIN);
        assertThat(result.getVlSalario().doubleValue()).isEqualTo(50.70);
    }

}
