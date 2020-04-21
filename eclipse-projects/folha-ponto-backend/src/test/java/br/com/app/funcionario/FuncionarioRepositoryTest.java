package br.com.app.funcionario;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.core.Tuple;

import br.com.app.DatabaseTest;
import br.com.app.common.builder.EmpresaEntityBuilder;
import br.com.app.common.builder.FuncionarioEntityBuilder;
import br.com.app.common.builder.LancamentoEntityBuilder;
import br.com.app.common.builder.UsuarioEntityBuilder;
import br.com.app.empresa.dao.EmpresaEntity;
import br.com.app.empresa.dao.EmpresaRepository;
import br.com.app.funcionario.dao.FuncionarioEntity;
import br.com.app.funcionario.dao.FuncionarioRepository;
import br.com.app.lancamento.dao.LancamentoEntity;
import br.com.app.lancamento.dao.LancamentoRepository;
import br.com.app.lancamento.dto.TipoEnum;
import br.com.app.usuario.dao.UsuarioEntity;
import br.com.app.usuario.dao.UsuarioRepository;
import br.com.app.usuario.dto.PerfilEnum;
import br.com.app.usuario.dto.SituacaoUserEnum;

public class FuncionarioRepositoryTest extends DatabaseTest {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Override
    public void setUp() {
        lancamentoRepository.deleteAllInBatch();
        repository.deleteAllInBatch();
        empresaRepository.deleteAllInBatch();
        usuarioRepository.deleteAllInBatch();
    }

    @Test
    public void findByCpf() {
        EmpresaEntity empresa1 = EmpresaEntityBuilder.create().withNome("Empresa Teste X").withCnpj("12345678000011").build();
        empresa1 = empresaRepository.save(empresa1);

        EmpresaEntity empresa2 = EmpresaEntityBuilder.create().withNome("Empresa Teste Y").withCnpj("12345678000012").build();
        empresa2 = empresaRepository.save(empresa2);

        UsuarioEntity usuario1 = UsuarioEntityBuilder.create().withEmail("teste1@teste.com.br").withSenha("123").withSituacao(SituacaoUserEnum.ATIVO).withPerfil(PerfilEnum.ROLE_ADMIN).build();
        usuario1 = usuarioRepository.save(usuario1);

        UsuarioEntity usuario2 = UsuarioEntityBuilder.create().withEmail("teste2@teste.com.br").withSenha("456").withSituacao(SituacaoUserEnum.ATIVO).withPerfil(PerfilEnum.ROLE_USUARIO).build();
        usuario2 = usuarioRepository.save(usuario2);

        UsuarioEntity usuario3 = UsuarioEntityBuilder.create().withEmail("teste3@teste.com.br").withSenha("789").withSituacao(SituacaoUserEnum.ATIVO).withPerfil(PerfilEnum.ROLE_USUARIO).build();
        usuario3 = usuarioRepository.save(usuario3);

        FuncionarioEntity funcionario1 = FuncionarioEntityBuilder.create().withNome("Teste Teste").withCpf("12345678900").withRg("123456789").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresa(empresa1).withUsuario(usuario1).build();
        repository.save(funcionario1);

        FuncionarioEntity funcionario2 = FuncionarioEntityBuilder.create().withNome("Teste New").withCpf("12345678901").withRg("123456780").withValorHora(new BigDecimal(20.0)).withQtHorasTrabalhoDia(new BigDecimal(10.0)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresa(empresa1).withUsuario(usuario2).build();
        repository.save(funcionario2);

        FuncionarioEntity funcionario3 = FuncionarioEntityBuilder.create().withNome("Teste Novo").withCpf("12345678902").withRg("123456781").withValorHora(new BigDecimal(12.0)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.0)).withEmpresa(empresa2).withUsuario(usuario3).build();
        repository.save(funcionario3);

        FuncionarioEntity result = repository.findByCpf("12345678901");

        assertThat(result).isSameAs(funcionario2);
    }

    @Test
    public void findByEmpresaWithIncorrectLancamentos() {
        EmpresaEntity empresa1 = EmpresaEntityBuilder.create().withNome("emp 01").withCnpj("99152288000187").build();
        empresa1 = empresaRepository.save(empresa1);

        EmpresaEntity empresa2 = EmpresaEntityBuilder.create().withNome("emp 02").withCnpj("41609327000142").build();
        empresa2 = empresaRepository.save(empresa2);

        UsuarioEntity usuario1 = UsuarioEntityBuilder.create().withEmail("teste1@teste.com.br").withSenha("123456@abc").withSituacao(SituacaoUserEnum.ATIVO).withPerfil(PerfilEnum.ROLE_ADMIN).build();
        usuario1 = usuarioRepository.save(usuario1);

        UsuarioEntity usuario2 = UsuarioEntityBuilder.create().withEmail("teste2@teste.com.br").withSenha("654321@cba").withSituacao(SituacaoUserEnum.ATIVO).withPerfil(PerfilEnum.ROLE_USUARIO).build();
        usuario2 = usuarioRepository.save(usuario2);

        UsuarioEntity usuario3 = UsuarioEntityBuilder.create().withEmail("teste3@teste.com.br").withSenha("987321@acb").withSituacao(SituacaoUserEnum.ATIVO).withPerfil(PerfilEnum.ROLE_USUARIO).build();
        usuario3 = usuarioRepository.save(usuario3);

        FuncionarioEntity funcionario1 = FuncionarioEntityBuilder.create().withNome("Fulano Teste").withCpf("80496539000").withRg("261433556").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresa(empresa1).withUsuario(usuario1).build();
        funcionario1 = repository.save(funcionario1);

        FuncionarioEntity funcionario2 = FuncionarioEntityBuilder.create().withNome("Caio Teste").withCpf("01576806065").withRg("105687029").withValorHora(new BigDecimal(20.0)).withQtHorasTrabalhoDia(new BigDecimal(10.0)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresa(empresa1).withUsuario(usuario2).build();
        funcionario2 = repository.save(funcionario2);

        FuncionarioEntity funcionario3 = FuncionarioEntityBuilder.create().withNome("Aline Teste").withCpf("39036555035").withRg("226029839").withValorHora(new BigDecimal(12.0)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.0)).withEmpresa(empresa2).withUsuario(usuario3).build();
        funcionario3 = repository.save(funcionario3);

        LancamentoEntity lancamento1 = LancamentoEntityBuilder.create().withData(LocalDateTime.of(2020, 4, 20, 8, 0, 0).toInstant(ZoneOffset.UTC)).withDescricao("teste 1").withLocalizacao("Local").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionario(funcionario1).build();
        lancamentoRepository.save(lancamento1);
        LancamentoEntity lancamento2 = LancamentoEntityBuilder.create().withData(LocalDateTime.of(2020, 4, 20, 12, 0, 0).toInstant(ZoneOffset.UTC)).withDescricao("teste 2").withLocalizacao("Local").withTipo(TipoEnum.INICIO_ALMOCO).withFuncionario(funcionario1).build();
        lancamentoRepository.save(lancamento2);
        LancamentoEntity lancamento3 = LancamentoEntityBuilder.create().withData(LocalDateTime.of(2020, 4, 20, 13, 30, 0).toInstant(ZoneOffset.UTC)).withDescricao("teste 3").withLocalizacao("Local").withTipo(TipoEnum.TERMINO_ALMOCO).withFuncionario(funcionario1).build();
        lancamentoRepository.save(lancamento3);
        LancamentoEntity lancamento4 = LancamentoEntityBuilder.create().withData(LocalDateTime.of(2020, 4, 20, 18, 0, 0).toInstant(ZoneOffset.UTC)).withDescricao("teste 4").withLocalizacao("Local").withTipo(TipoEnum.TERMINO_TRABALHO).withFuncionario(funcionario1).build();
        lancamentoRepository.save(lancamento4);

        LancamentoEntity lancamento5 = LancamentoEntityBuilder.create().withData(LocalDateTime.of(2020, 4, 18, 8, 0, 0).toInstant(ZoneOffset.UTC)).withTipo(TipoEnum.INICIO_TRABALHO).withFuncionario(funcionario1).build();
        lancamentoRepository.save(lancamento5);
        LancamentoEntity lancamento6 = LancamentoEntityBuilder.create().withData(LocalDateTime.of(2020, 4, 18, 12, 0, 0).toInstant(ZoneOffset.UTC)).withTipo(TipoEnum.TERMINO_TRABALHO).withFuncionario(funcionario1).build();
        lancamentoRepository.save(lancamento6);

        LancamentoEntity lancamento7 = LancamentoEntityBuilder.create().withData(LocalDateTime.of(2020, 3, 18, 8, 0, 0).toInstant(ZoneOffset.UTC)).withDescricao("teste 7").withLocalizacao("Local").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionario(funcionario1).build();
        lancamentoRepository.save(lancamento7);
        LancamentoEntity lancamento8 = LancamentoEntityBuilder.create().withData(LocalDateTime.of(2020, 3, 18, 12, 0, 0).toInstant(ZoneOffset.UTC)).withDescricao("teste 8").withLocalizacao("Local").withTipo(TipoEnum.TERMINO_TRABALHO).withFuncionario(funcionario1).build();
        lancamentoRepository.save(lancamento8);

        LancamentoEntity lancamento9 = LancamentoEntityBuilder.create().withData(LocalDateTime.of(2020, 4, 18, 8, 0, 0).toInstant(ZoneOffset.UTC)).withDescricao("teste 9").withLocalizacao("Local").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionario(funcionario2).build();
        lancamentoRepository.save(lancamento9);
        LancamentoEntity lancamento10 = LancamentoEntityBuilder.create().withData(LocalDateTime.of(2020, 4, 18, 12, 0, 0).toInstant(ZoneOffset.UTC)).withDescricao("teste 10").withLocalizacao("Local").withTipo(TipoEnum.TERMINO_TRABALHO).withFuncionario(funcionario2).build();
        lancamentoRepository.save(lancamento10);

        LancamentoEntity lancamento11 = LancamentoEntityBuilder.create().withData(LocalDateTime.of(2020, 4, 15, 8, 0, 0).toInstant(ZoneOffset.UTC)).withDescricao("teste 11").withLocalizacao("Local").withTipo(TipoEnum.INICIO_PAUSA).withFuncionario(funcionario3).build();
        lancamentoRepository.save(lancamento11);
        LancamentoEntity lancamento12 = LancamentoEntityBuilder.create().withData(LocalDateTime.of(2020, 4, 15, 12, 0, 0).toInstant(ZoneOffset.UTC)).withDescricao("teste 12").withLocalizacao("Local").withTipo(TipoEnum.TERMINO_PAUSA).withFuncionario(funcionario3).build();
        lancamentoRepository.save(lancamento12);

        List<Tuple> result = repository.findByEmpresaWithIncorrectLancamentos(empresa1.getCnpj(), LocalDate.of(2020, 4, 1));

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);

        assertThat(result.get(0).get(0, String.class)).isEqualTo(funcionario2.getCpf());
        assertThat(result.get(0).get(1, String.class)).isEqualTo(funcionario2.getNome());
        assertThat(result.get(0).get(2, Integer.class)).isEqualTo(18L);
        assertThat(result.get(0).get(3, Long.class)).isEqualTo(2);

        assertThat(result.get(1).get(0, String.class)).isEqualTo(funcionario1.getCpf());
        assertThat(result.get(1).get(1, String.class)).isEqualTo(funcionario1.getNome());
        assertThat(result.get(1).get(2, Integer.class)).isEqualTo(18L);
        assertThat(result.get(1).get(3, Long.class)).isEqualTo(2);
    }

    @Test
    public void updateHorariosByEmpresa() {
        EmpresaEntity empresa1 = EmpresaEntityBuilder.create().withNome("emp 01").withCnpj("99152288000187").build();
        empresa1 = empresaRepository.save(empresa1);

        EmpresaEntity empresa2 = EmpresaEntityBuilder.create().withNome("emp 02").withCnpj("41609327000142").build();
        empresa2 = empresaRepository.save(empresa2);

        UsuarioEntity usuario1 = UsuarioEntityBuilder.create().withEmail("teste1@teste.com.br").withSenha("123456@abc").withSituacao(SituacaoUserEnum.ATIVO).withPerfil(PerfilEnum.ROLE_ADMIN).build();
        usuario1 = usuarioRepository.save(usuario1);

        UsuarioEntity usuario2 = UsuarioEntityBuilder.create().withEmail("teste2@teste.com.br").withSenha("654321@cba").withSituacao(SituacaoUserEnum.ATIVO).withPerfil(PerfilEnum.ROLE_USUARIO).build();
        usuario2 = usuarioRepository.save(usuario2);

        UsuarioEntity usuario3 = UsuarioEntityBuilder.create().withEmail("teste3@teste.com.br").withSenha("987321@acb").withSituacao(SituacaoUserEnum.ATIVO).withPerfil(PerfilEnum.ROLE_USUARIO).build();
        usuario3 = usuarioRepository.save(usuario3);

        FuncionarioEntity funcionario1 = FuncionarioEntityBuilder.create().withNome("Fulano Teste").withCpf("80496539000").withRg("261433556").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresa(empresa1).withUsuario(usuario1).build();
        funcionario1 = repository.save(funcionario1);

        FuncionarioEntity funcionario2 = FuncionarioEntityBuilder.create().withNome("Caio Teste").withCpf("01576806065").withRg("105687029").withValorHora(new BigDecimal(20.0)).withQtHorasTrabalhoDia(new BigDecimal(10.0)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresa(empresa1).withUsuario(usuario2).build();
        funcionario2 = repository.save(funcionario2);

        FuncionarioEntity funcionario3 = FuncionarioEntityBuilder.create().withNome("Aline Teste").withCpf("39036555035").withRg("226029839").withValorHora(new BigDecimal(12.0)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.0)).withEmpresa(empresa2).withUsuario(usuario3).build();
        funcionario3 = repository.save(funcionario3);

        Integer result = repository.updateHorariosByEmpresa(new BigDecimal(9.0), new BigDecimal(2), empresa1);

        assertThat(result).isEqualTo(2);
    }

    @Test
    public void findVlSalarioByFuncionario() {
        EmpresaEntity empresa1 = EmpresaEntityBuilder.create().withNome("emp 01").withCnpj("99152288000187").build();
        empresa1 = empresaRepository.save(empresa1);

        EmpresaEntity empresa2 = EmpresaEntityBuilder.create().withNome("emp 02").withCnpj("41609327000142").build();
        empresa2 = empresaRepository.save(empresa2);

        UsuarioEntity usuario1 = UsuarioEntityBuilder.create().withEmail("teste1@teste.com.br").withSenha("123456@abc").withSituacao(SituacaoUserEnum.ATIVO).withPerfil(PerfilEnum.ROLE_ADMIN).build();
        usuario1 = usuarioRepository.save(usuario1);

        UsuarioEntity usuario2 = UsuarioEntityBuilder.create().withEmail("teste2@teste.com.br").withSenha("654321@cba").withSituacao(SituacaoUserEnum.ATIVO).withPerfil(PerfilEnum.ROLE_USUARIO).build();
        usuario2 = usuarioRepository.save(usuario2);

        UsuarioEntity usuario3 = UsuarioEntityBuilder.create().withEmail("teste3@teste.com.br").withSenha("987321@acb").withSituacao(SituacaoUserEnum.ATIVO).withPerfil(PerfilEnum.ROLE_USUARIO).build();
        usuario3 = usuarioRepository.save(usuario3);

        FuncionarioEntity funcionario1 = FuncionarioEntityBuilder.create().withNome("Fulano Teste").withCpf("80496539000").withRg("261433556").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresa(empresa1).withUsuario(usuario1).build();
        funcionario1 = repository.save(funcionario1);

        FuncionarioEntity funcionario2 = FuncionarioEntityBuilder.create().withNome("Caio Teste").withCpf("01576806065").withRg("105687029").withValorHora(new BigDecimal(20.0)).withQtHorasTrabalhoDia(new BigDecimal(10.0)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresa(empresa1).withUsuario(usuario2).build();
        funcionario2 = repository.save(funcionario2);

        FuncionarioEntity funcionario3 = FuncionarioEntityBuilder.create().withNome("Aline Teste").withCpf("39036555035").withRg("226029839").withValorHora(new BigDecimal(12.0)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.0)).withEmpresa(empresa2).withUsuario(usuario3).build();
        funcionario3 = repository.save(funcionario3);

        List<Object[]> result = repository.findVlSalarioByFuncionario();

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(3);

        assertThat(result.get(0)[0]).isEqualTo(funcionario1.getCpf());
        assertThat(result.get(0)[1]).isEqualTo(funcionario1.getNome());
        assertThat(((BigDecimal) result.get(0)[2]).intValue()).isEqualTo(funcionario1.getUsuario().getPerfil().getValue());
        assertThat(((BigDecimal) result.get(0)[3]).setScale(2, RoundingMode.HALF_UP)).isEqualTo(new BigDecimal(2910.0).setScale(2));

        assertThat(result.get(1)[0]).isEqualTo(funcionario2.getCpf());
        assertThat(result.get(1)[1]).isEqualTo(funcionario2.getNome());
        assertThat(((BigDecimal) result.get(1)[2]).intValue()).isEqualTo(funcionario2.getUsuario().getPerfil().getValue());
        assertThat(((BigDecimal) result.get(1)[3]).setScale(2, RoundingMode.HALF_UP)).isEqualTo(new BigDecimal(3600.0).setScale(2));

        assertThat(result.get(2)[0]).isEqualTo(funcionario3.getCpf());
        assertThat(result.get(2)[1]).isEqualTo(funcionario3.getNome());
        assertThat(((BigDecimal) result.get(2)[2]).intValue()).isEqualTo(funcionario3.getUsuario().getPerfil().getValue());
        assertThat(((BigDecimal) result.get(2)[3]).setScale(2, RoundingMode.HALF_UP)).isEqualTo(new BigDecimal(2160.0).setScale(2));
    }

}
