package br.com.app.funcionario;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.querydsl.core.Tuple;

import br.com.app.CommonBaseTest;
import br.com.app.common.builder.FuncionarioDtoBuilder;
import br.com.app.common.builder.FuncionarioEntityBuilder;
import br.com.app.common.builder.LancamentoEntityBuilder;
import br.com.app.empresa.EmpresaService;
import br.com.app.empresa.dao.EmpresaEntity;
import br.com.app.funcionario.dao.FuncionarioEntity;
import br.com.app.funcionario.dao.FuncionarioRepository;
import br.com.app.funcionario.dto.AtualizarHorariosByEmpresaInput;
import br.com.app.funcionario.dto.FuncionarioDto;
import br.com.app.funcionario.dto.LancamentosByFuncionarioOutput;
import br.com.app.funcionario.dto.LancamentosIncorretosFuncByEmpresaOutput;
import br.com.app.funcionario.dto.SalarioByFuncionarioOutput;
import br.com.app.lancamento.LancamentoService;
import br.com.app.lancamento.dao.LancamentoEntity;
import br.com.app.lancamento.dto.LancamentoFuncOutput;
import br.com.app.lancamento.dto.TipoEnum;
import br.com.app.usuario.UsuarioService;
import br.com.app.usuario.dao.UsuarioEntity;
import br.com.app.usuario.dto.PerfilEnum;
import br.com.app.usuario.dto.SituacaoUserEnum;

public class FuncionarioServiceTest extends CommonBaseTest {

    @Mock
    private FuncionarioRepository repository;

    @Mock
    private FuncionarioMapper mapper;

    @Mock
    private EmpresaService empresaService;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private LancamentoService lancamentoService;

    @InjectMocks
    private FuncionarioService service;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create() {
        try {
            FuncionarioDto dto = FuncionarioDtoBuilder.create().withNome("Fulano Teste").withCpf("12345678900").withRg("123456789").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresaId(1L).withEmail("teste@teste.com.br").withSenha("123456@abc").withSituacao(SituacaoUserEnum.ATIVO.toString()).withPerfil(PerfilEnum.ROLE_ADMIN.toString()).build();
            EmpresaEntity empresa = new EmpresaEntity();
            UsuarioEntity usuario = new UsuarioEntity();
            FuncionarioEntity funcionario = FuncionarioEntityBuilder.create(1L).withNome("Fulano Teste").withCpf("80496539000").withRg("261433556").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresa(empresa).withUsuario(usuario).build();

            when(empresaService.getEmpresa(dto.getEmpresaId())).thenReturn(empresa);
            when(usuarioService.create(dto.getEmail(), dto.getSenha(), dto.getSituacao(), dto.getPerfil())).thenReturn(usuario);
            when(mapper.toEntity(dto, empresa, usuario)).thenReturn(funcionario);

            long result = service.create(dto);

            ArgumentCaptor<FuncionarioEntity> captor = ArgumentCaptor.forClass(FuncionarioEntity.class);
            verify(repository).save(captor.capture());
            FuncionarioEntity capturedFuncionario = captor.getValue();

            assertThat(capturedFuncionario).isNotNull();
            assertThat(capturedFuncionario.getId()).isEqualTo(funcionario.getId());
            assertThat(capturedFuncionario.getNome()).isEqualTo(funcionario.getNome());
            assertThat(capturedFuncionario.getCpf()).isEqualTo(funcionario.getCpf());
            assertThat(capturedFuncionario.getRg()).isEqualTo(funcionario.getRg());
            assertThat(capturedFuncionario.getValorHora()).isEqualTo(funcionario.getValorHora());
            assertThat(capturedFuncionario.getQtHorasTrabalhoDia()).isEqualTo(funcionario.getQtHorasTrabalhoDia());
            assertThat(capturedFuncionario.getQtHorasAlmoco()).isEqualTo(funcionario.getQtHorasAlmoco());
            assertThat(capturedFuncionario.getEmpresa()).isSameAs(funcionario.getEmpresa());
            assertThat(capturedFuncionario.getUsuario()).isSameAs(funcionario.getUsuario());

            assertThat(result).isEqualTo(funcionario.getId());

            verify(empresaService).getEmpresa(dto.getEmpresaId());
            verify(usuarioService).create(dto.getEmail(), dto.getSenha(), dto.getSituacao(), dto.getPerfil());
            verify(mapper).toEntity(dto, empresa, usuario);
            verify(repository).save(funcionario);
        } catch (Exception ex) {
            failBecauseExceptionWasNotThrown(Exception.class);
        }
    }

    @Test
    public void create_idEmpresaInvalido() {
        FuncionarioDto dto = FuncionarioDtoBuilder.create().withNome("Fulano Teste").withCpf("12345678900").withRg("123456789").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresaId(1L).withEmail("teste@teste.com.br").withSenha("123456@abc").withSituacao(SituacaoUserEnum.ATIVO.toString()).withPerfil(PerfilEnum.ROLE_ADMIN.toString()).build();

        try {
            when(empresaService.getEmpresa(dto.getEmpresaId())).thenReturn(null);
            service.create(dto);
            failBecauseExceptionWasNotThrown(Exception.class);
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("A empresa informada não existe");
        }

        try {
            verify(empresaService).getEmpresa(dto.getEmpresaId());
            verify(usuarioService, never()).create(dto.getEmail(), dto.getSenha(), dto.getSituacao(), dto.getPerfil());
            verify(mapper, never()).toEntity(any(FuncionarioDto.class), any(EmpresaEntity.class), any(UsuarioEntity.class));
            verify(repository, never()).save(any(FuncionarioEntity.class));
        } catch (Exception ex) {
            failBecauseExceptionWasNotThrown(Exception.class);
        }
    }

    @Test
    public void update() {
        try {
            FuncionarioDto dto = FuncionarioDtoBuilder.create().withId(1L).withNome("Fulano Teste").withCpf("12345678900").withRg("123456789").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresaId(1L).withEmail("teste@teste.com.br").withSenha("123456@abc").withSituacao(SituacaoUserEnum.ATIVO.toString()).withPerfil(PerfilEnum.ROLE_ADMIN.toString()).build();
            EmpresaEntity empresa = new EmpresaEntity();
            UsuarioEntity usuario = new UsuarioEntity();
            FuncionarioEntity funcionario = FuncionarioEntityBuilder.create(1L).withNome("Fulano Teste").withCpf("80496539000").withRg("261433556").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresa(empresa).withUsuario(usuario).build();

            Optional<FuncionarioEntity> funcionarioOpt = Optional.of(funcionario);
            when(repository.findById(dto.getId())).thenReturn(funcionarioOpt);
            when(empresaService.getEmpresa(dto.getEmpresaId())).thenReturn(empresa);
            when(usuarioService.update(funcionario.getUsuario().getId(), dto.getEmail(), dto.getSenha(), dto.getSituacao(), dto.getPerfil())).thenReturn(usuario);

            boolean result = service.update(dto);

            ArgumentCaptor<FuncionarioEntity> captor = ArgumentCaptor.forClass(FuncionarioEntity.class);
            verify(repository).save(captor.capture());
            FuncionarioEntity capturedFuncionario = captor.getValue();

            assertThat(capturedFuncionario).isNotNull();
            assertThat(capturedFuncionario.getId()).isEqualTo(funcionario.getId());
            assertThat(capturedFuncionario.getNome()).isEqualTo(funcionario.getNome());
            assertThat(capturedFuncionario.getCpf()).isEqualTo(funcionario.getCpf());
            assertThat(capturedFuncionario.getRg()).isEqualTo(funcionario.getRg());
            assertThat(capturedFuncionario.getValorHora()).isEqualTo(funcionario.getValorHora());
            assertThat(capturedFuncionario.getQtHorasTrabalhoDia()).isEqualTo(funcionario.getQtHorasTrabalhoDia());
            assertThat(capturedFuncionario.getQtHorasAlmoco()).isEqualTo(funcionario.getQtHorasAlmoco());
            assertThat(capturedFuncionario.getEmpresa()).isSameAs(funcionario.getEmpresa());
            assertThat(capturedFuncionario.getUsuario()).isSameAs(funcionario.getUsuario());

            assertThat(result).isTrue();

            verify(repository).findById(dto.getId());
            verify(empresaService).getEmpresa(dto.getEmpresaId());
            verify(usuarioService).update(funcionario.getUsuario().getId(), dto.getEmail(), dto.getSenha(), dto.getSituacao(), dto.getPerfil());
            verify(mapper).merge(funcionario, empresa, usuario, dto);
            verify(repository).save(funcionario);
        } catch (Exception ex) {
            failBecauseExceptionWasNotThrown(Exception.class);
        }
    }

    @Test
    public void update_funcionarioNaoEncontrado() {
        try {
            FuncionarioDto dto = FuncionarioDtoBuilder.create().withId(1L).withNome("Fulano Teste").withCpf("12345678900").withRg("123456789").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresaId(1L).withEmail("teste@teste.com.br").withSenha("123456@abc").withSituacao(SituacaoUserEnum.ATIVO.toString()).withPerfil(PerfilEnum.ROLE_ADMIN.toString()).build();

            Optional<FuncionarioEntity> funcionarioOpt = Optional.empty();
            when(repository.findById(dto.getId())).thenReturn(funcionarioOpt);

            boolean result = service.update(dto);

            assertThat(result).isFalse();

            verify(repository).findById(dto.getId());
            verify(empresaService, never()).getEmpresa(dto.getEmpresaId());
            verify(usuarioService, never()).update(any(Long.class), any(String.class), any(String.class), any(SituacaoUserEnum.class), any(PerfilEnum.class));
            verify(mapper, never()).merge(any(FuncionarioEntity.class), any(EmpresaEntity.class), any(UsuarioEntity.class), any(FuncionarioDto.class));
            verify(repository, never()).save(any(FuncionarioEntity.class));
        } catch (Exception ex) {
            failBecauseExceptionWasNotThrown(Exception.class);
        }
    }

    @Test
    public void update_idInvalido() {
        try {
            FuncionarioDto dto = FuncionarioDtoBuilder.create().withNome("Fulano Teste").withCpf("12345678900").withRg("123456789").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresaId(1L).withEmail("teste@teste.com.br").withSenha("123456@abc").withSituacao(SituacaoUserEnum.ATIVO.toString()).withPerfil(PerfilEnum.ROLE_ADMIN.toString()).build();
            service.update(dto);
            failBecauseExceptionWasNotThrown(Exception.class);
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("ID do funcionário inválido");
        }

        try {
            verify(repository, never()).findById(any(Long.class));
            verify(empresaService, never()).getEmpresa(any(Long.class));
            verify(usuarioService, never()).update(any(Long.class), any(String.class), any(String.class), any(SituacaoUserEnum.class), any(PerfilEnum.class));
            verify(mapper, never()).merge(any(FuncionarioEntity.class), any(EmpresaEntity.class), any(UsuarioEntity.class), any(FuncionarioDto.class));
            verify(repository, never()).save(any(FuncionarioEntity.class));
        } catch (Exception ex) {
            failBecauseExceptionWasNotThrown(Exception.class);
        }
    }

    @Test
    public void update_empresaNaoEncontrada() {
        FuncionarioDto dto = FuncionarioDtoBuilder.create().withId(1L).withNome("Fulano Teste").withCpf("12345678900").withRg("123456789").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresaId(4L).withEmail("teste@teste.com.br").withSenha("123456@abc").withSituacao(SituacaoUserEnum.ATIVO.toString()).withPerfil(PerfilEnum.ROLE_ADMIN.toString()).build();
        EmpresaEntity empresa = new EmpresaEntity();
        UsuarioEntity usuario = new UsuarioEntity();
        FuncionarioEntity funcionario = FuncionarioEntityBuilder.create(1L).withNome("Fulano Teste").withCpf("80496539000").withRg("261433556").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresa(empresa).withUsuario(usuario).build();

        try {
            Optional<FuncionarioEntity> funcionarioOpt = Optional.of(funcionario);
            when(repository.findById(dto.getId())).thenReturn(funcionarioOpt);
            when(empresaService.getEmpresa(dto.getEmpresaId())).thenReturn(null);

            service.update(dto);
            failBecauseExceptionWasNotThrown(Exception.class);
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("A empresa informada não existe");
        }

        try {
            verify(repository).findById(dto.getId());
            verify(empresaService).getEmpresa(dto.getEmpresaId());
            verify(usuarioService, never()).update(any(Long.class), any(String.class), any(String.class), any(SituacaoUserEnum.class), any(PerfilEnum.class));
            verify(mapper, never()).merge(any(FuncionarioEntity.class), any(EmpresaEntity.class), any(UsuarioEntity.class), any(FuncionarioDto.class));
            verify(repository, never()).save(any(FuncionarioEntity.class));
        } catch (Exception ex) {
            failBecauseExceptionWasNotThrown(Exception.class);
        }
    }

    @Test
    public void retrieve() {
        String cpf = "12345678900";
        FuncionarioEntity funcionario = new FuncionarioEntity();
        FuncionarioDto dto = new FuncionarioDto();

        when(repository.findByCpf(cpf)).thenReturn(funcionario);
        when(mapper.toDto(funcionario)).thenReturn(dto);

        FuncionarioDto result = service.retrieve(cpf);

        assertThat(result).isNotNull();
        assertThat(result).isSameAs(dto);

        verify(repository).findByCpf(cpf);
        verify(mapper).toDto(funcionario);
    }

    @Test
    public void retrieve_FuncionarioNaoEncontrado() {
        String cpf = "12345678900";

        when(repository.findByCpf(cpf)).thenReturn(null);

        FuncionarioDto result = service.retrieve(cpf);

        assertThat(result).isNull();

        verify(repository).findByCpf(cpf);
        verify(mapper, never()).toDto(any(FuncionarioEntity.class));
    }

    @Test
    public void delete() {
        long id = 1L;

        boolean result = false;
        try {
            result = service.delete(id);
        } catch (Exception e) {
            failBecauseExceptionWasNotThrown(Exception.class);

        }
        assertThat(result).isTrue();

        verify(repository).deleteById(id);
    }

    @Test
    public void delete_idInvalido() {
        long id = 0L;

        try {
            service.delete(id);
            failBecauseExceptionWasNotThrown(Exception.class);
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("ID do funcionário inválido");
        }

        verify(repository, never()).deleteById(id);
    }

    @Test
    public void list() {
        FuncionarioDto dto1 = FuncionarioDtoBuilder.create().withId(1L).withNome("Fulano Teste").withCpf("12345678900").withRg("123456789").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresaId(1L).withEmail("teste1@teste.com.br").withSenha("123456@abc").withSituacao(SituacaoUserEnum.ATIVO.toString()).withPerfil(PerfilEnum.ROLE_ADMIN.toString()).build();
        EmpresaEntity empresa1 = new EmpresaEntity();
        UsuarioEntity usuario1 = new UsuarioEntity();
        FuncionarioEntity funcionario1 = FuncionarioEntityBuilder.create(1L).withNome("Fulano Teste").withCpf("12345678900").withRg("123456789").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresa(empresa1).withUsuario(usuario1).build();

        FuncionarioDto dto2 = FuncionarioDtoBuilder.create().withId(2L).withNome("Fulano Tal").withCpf("12345678901").withRg("123456781").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresaId(1L).withEmail("teste2@teste.com.br").withSenha("123456@abc").withSituacao(SituacaoUserEnum.ATIVO.toString()).withPerfil(PerfilEnum.ROLE_ADMIN.toString()).build();
        EmpresaEntity empresa2 = new EmpresaEntity();
        UsuarioEntity usuario2 = new UsuarioEntity();
        FuncionarioEntity funcionario2 = FuncionarioEntityBuilder.create(2L).withNome("Fulano Tal").withCpf("12345678901").withRg("123456781").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresa(empresa2).withUsuario(usuario2).build();

        List<FuncionarioEntity> funcionarios = List.of(funcionario1, funcionario2);
        when(repository.findAll()).thenReturn(funcionarios);

        when(mapper.toDto(funcionario1)).thenReturn(dto1);
        when(mapper.toDto(funcionario2)).thenReturn(dto2);

        List<FuncionarioDto> result = service.list();

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isSameAs(dto1);
        assertThat(result.get(1)).isSameAs(dto2);

        verify(repository).findAll();
        verify(mapper).toDto(funcionario1);
        verify(mapper).toDto(funcionario2);
    }

    @Test
    public void list_nenhumFuncionarioCadastrado() {
        List<FuncionarioEntity> funcionarios = new ArrayList<>();
        when(repository.findAll()).thenReturn(funcionarios);

        List<FuncionarioDto> result = service.list();

        assertThat(result).isEmpty();

        verify(repository).findAll();
        verify(mapper, never()).toDto(any(FuncionarioEntity.class));
    }

    @Test
    public void getFuncionario() {
        long id = 1L;

        FuncionarioEntity funcionario = new FuncionarioEntity();
        Optional<FuncionarioEntity> funcionarioOpt = Optional.of(funcionario);
        when(repository.findById(id)).thenReturn(funcionarioOpt);

        FuncionarioEntity result = service.getFuncionario(id);

        assertThat(result).isNotNull();
        assertThat(result).isSameAs(funcionario);

        verify(repository).findById(id);
    }

    @Test
    public void getFuncionario_funcionarioNaoEncontrado() {
        long id = 1L;

        Optional<FuncionarioEntity> funcionarioOpt = Optional.empty();
        when(repository.findById(id)).thenReturn(funcionarioOpt);

        FuncionarioEntity result = service.getFuncionario(id);

        assertThat(result).isNull();

        verify(repository).findById(id);
    }

    @Test
    public void listLancamentosFuncPorData() {
        String cpf = "12345678900";
        String data = "12/05/2020";

        FuncionarioEntity funcionario = FuncionarioEntityBuilder.create(1L).withNome("Fulano Teste").withCpf(cpf).withRg("261433556").withValorHora(new BigDecimal(10.5)).withQtHorasTrabalhoDia(new BigDecimal(8.5)).withQtHorasAlmoco(new BigDecimal(1.5)).withEmpresa(new EmpresaEntity()).withUsuario(new UsuarioEntity()).build();

        LancamentoEntity lancamento1 = LancamentoEntityBuilder.create(1L).withData(LocalDateTime.of(2020, 5, 12, 10, 48, 15).toInstant(ZoneOffset.UTC)).withDescricao("Teste 1").withLocalizacao("Local de teste 1").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionario(funcionario).build();
        funcionario.addLancamento(lancamento1);
        LancamentoEntity lancamento2 = LancamentoEntityBuilder.create(2L).withData(LocalDateTime.of(2020, 4, 8, 15, 25, 00).toInstant(ZoneOffset.UTC)).withDescricao("Teste 2").withLocalizacao("Local de teste 2").withTipo(TipoEnum.INICIO_ALMOCO).withFuncionario(funcionario).build();
        funcionario.addLancamento(lancamento2);
        LancamentoEntity lancamento3 = LancamentoEntityBuilder.create(3L).withData(LocalDateTime.of(2020, 5, 12, 23, 59, 02).toInstant(ZoneOffset.UTC)).withDescricao("Teste 3").withLocalizacao("Local de teste 3").withTipo(TipoEnum.TERMINO_ALMOCO).withFuncionario(funcionario).build();
        funcionario.addLancamento(lancamento3);

        when(repository.findByCpf(cpf)).thenReturn(funcionario);

        List<LancamentoFuncOutput> lancamentosFuncOutput = new ArrayList<>();
        List<LancamentoEntity> lancamentos = List.of(lancamento1, lancamento3);
        when(lancamentoService.toListLancamentoFuncOutput(lancamentos)).thenReturn(lancamentosFuncOutput);

        LancamentosByFuncionarioOutput lancamentosByFuncionarioOutput = new LancamentosByFuncionarioOutput();
        when(mapper.toLancamentosByFuncionarioOutput(funcionario, lancamentosFuncOutput)).thenReturn(lancamentosByFuncionarioOutput);

        LancamentosByFuncionarioOutput result = service.listLancamentosFuncPorData(cpf, data);

        assertThat(result).isSameAs(lancamentosByFuncionarioOutput);

        verify(repository).findByCpf(cpf);
        verify(lancamentoService).toListLancamentoFuncOutput(lancamentos);
        verify(mapper).toLancamentosByFuncionarioOutput(funcionario, lancamentosFuncOutput);
    }

    @Test
    public void listLancamentosFuncPorData_funcionarioNaoEncontrado() {
        String cpf = "12345678900";
        String data = "12/05/2020";

        when(repository.findByCpf(cpf)).thenReturn(null);

        LancamentosByFuncionarioOutput result = service.listLancamentosFuncPorData(cpf, data);

        assertThat(result).isNull();

        verify(repository).findByCpf(cpf);
        verify(lancamentoService, never()).toListLancamentoFuncOutput(anyList());
        verify(mapper, never()).toLancamentosByFuncionarioOutput(any(FuncionarioEntity.class), anyList());
    }

    @Test
    public void listLancamentosIncorretosFuncPorEmpresa() {
        String cnpj = "12345678000011";
        String data = "12/05/2020";

        LocalDate referenceDate = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        List<Tuple> funcionariosDataLanc = new ArrayList<>();
        funcionariosDataLanc.add(Mockito.mock(Tuple.class));
        when(repository.findByEmpresaWithIncorrectLancamentos(cnpj, referenceDate)).thenReturn(funcionariosDataLanc);

        LancamentosIncorretosFuncByEmpresaOutput lancamentosIncorretosFuncByEmpresaOutput = new LancamentosIncorretosFuncByEmpresaOutput();
        when(mapper.toLancamentosIncorretosFuncByEmpresaOutput(funcionariosDataLanc.get(0))).thenReturn(lancamentosIncorretosFuncByEmpresaOutput);

        List<LancamentosIncorretosFuncByEmpresaOutput> result = service.listLancamentosIncorretosFuncPorEmpresa(cnpj, data);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isSameAs(lancamentosIncorretosFuncByEmpresaOutput);

        verify(repository).findByEmpresaWithIncorrectLancamentos(cnpj, referenceDate);
        verify(mapper).toLancamentosIncorretosFuncByEmpresaOutput(funcionariosDataLanc.get(0));
    }

    @Test
    public void listLancamentosIncorretosFuncPorEmpresa_nenhumLancamentoIncorreto() {
        String cnpj = "12345678000011";
        String data = "12/05/2020";

        LocalDate referenceDate = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        List<Tuple> funcionariosDataLanc = new ArrayList<>();
        when(repository.findByEmpresaWithIncorrectLancamentos(cnpj, referenceDate)).thenReturn(funcionariosDataLanc);

        List<LancamentosIncorretosFuncByEmpresaOutput> result = service.listLancamentosIncorretosFuncPorEmpresa(cnpj, data);

        assertThat(result).isEmpty();

        verify(repository).findByEmpresaWithIncorrectLancamentos(cnpj, referenceDate);
        verify(mapper, never()).toLancamentosIncorretosFuncByEmpresaOutput(any(Tuple.class));
    }

    @Test
    public void atualizarHorariosPorEmpresa() {
        AtualizarHorariosByEmpresaInput input = new AtualizarHorariosByEmpresaInput();
        input.setEmpresaId(1L);
        input.setQtHorasTrabalhoDia(new BigDecimal(8.5));
        input.setQtHorasAlmoco(new BigDecimal(1.5));

        EmpresaEntity empresa = new EmpresaEntity();
        when(empresaService.getEmpresa(input.getEmpresaId())).thenReturn(empresa);

        when(repository.updateHorariosByEmpresa(input.getQtHorasTrabalhoDia(), input.getQtHorasAlmoco(), empresa)).thenReturn(1);

        try {
            boolean result = service.atualizarHorariosPorEmpresa(input);
            assertThat(result).isTrue();
        } catch (Exception ex) {
            failBecauseExceptionWasNotThrown(Exception.class);
        }

        verify(empresaService).getEmpresa(input.getEmpresaId());
        verify(repository).updateHorariosByEmpresa(input.getQtHorasTrabalhoDia(), input.getQtHorasAlmoco(), empresa);
    }

    @Test
    public void atualizarHorariosPorEmpresa_empresaNaoPossuiFuncionarios() {
        AtualizarHorariosByEmpresaInput input = new AtualizarHorariosByEmpresaInput();
        input.setEmpresaId(1L);
        input.setQtHorasTrabalhoDia(new BigDecimal(8.5));
        input.setQtHorasAlmoco(new BigDecimal(1.5));

        EmpresaEntity empresa = new EmpresaEntity();
        when(empresaService.getEmpresa(input.getEmpresaId())).thenReturn(empresa);

        when(repository.updateHorariosByEmpresa(input.getQtHorasTrabalhoDia(), input.getQtHorasAlmoco(), empresa)).thenReturn(0);

        try {
            boolean result = service.atualizarHorariosPorEmpresa(input);
            assertThat(result).isFalse();
        } catch (Exception ex) {
            failBecauseExceptionWasNotThrown(Exception.class);
        }

        verify(empresaService).getEmpresa(input.getEmpresaId());
        verify(repository).updateHorariosByEmpresa(input.getQtHorasTrabalhoDia(), input.getQtHorasAlmoco(), empresa);
    }

    @Test
    public void atualizarHorariosPorEmpresa_empresaInvalida() {
        AtualizarHorariosByEmpresaInput input = new AtualizarHorariosByEmpresaInput();
        input.setEmpresaId(0L);
        input.setQtHorasTrabalhoDia(new BigDecimal(8.5));
        input.setQtHorasAlmoco(new BigDecimal(1.5));

        try {
            service.atualizarHorariosPorEmpresa(input);
            failBecauseExceptionWasNotThrown(Exception.class);
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("ID da empresa inválido");
        }

        verify(empresaService, never()).getEmpresa(input.getEmpresaId());
        verify(repository, never()).updateHorariosByEmpresa(any(BigDecimal.class), any(BigDecimal.class), any(EmpresaEntity.class));
    }

    @Test
    public void atualizarHorariosPorEmpresa_empresaNaoExiste() {
        AtualizarHorariosByEmpresaInput input = new AtualizarHorariosByEmpresaInput();
        input.setEmpresaId(1L);
        input.setQtHorasTrabalhoDia(new BigDecimal(8.5));
        input.setQtHorasAlmoco(new BigDecimal(1.5));

        when(empresaService.getEmpresa(input.getEmpresaId())).thenReturn(null);

        try {
            service.atualizarHorariosPorEmpresa(input);
            failBecauseExceptionWasNotThrown(Exception.class);
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("A empresa informada não existe");
        }

        verify(empresaService).getEmpresa(input.getEmpresaId());
        verify(repository, never()).updateHorariosByEmpresa(any(BigDecimal.class), any(BigDecimal.class), any(EmpresaEntity.class));
    }

    @Test
    public void listSalarioByFuncionario() {
        Object[] obj = new Object[] { "123.456.789.14", "Teste", PerfilEnum.ROLE_ADMIN, new BigDecimal(10000) };
        List<Object[]> salarioByFuncionario = new ArrayList<>();
        salarioByFuncionario.add(obj);

        when(repository.findVlSalarioByFuncionario()).thenReturn(salarioByFuncionario);

        SalarioByFuncionarioOutput salarioByFuncionarioOutput = new SalarioByFuncionarioOutput();
        when(mapper.toSalarioByFuncionarioOutput(salarioByFuncionario.get(0))).thenReturn(salarioByFuncionarioOutput);

        List<SalarioByFuncionarioOutput> result = service.listSalarioByFuncionario();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isSameAs(salarioByFuncionarioOutput);

        verify(repository).findVlSalarioByFuncionario();
        verify(mapper).toSalarioByFuncionarioOutput(salarioByFuncionario.get(0));
    }

    @Test
    public void listSalarioByFuncionario_funcionariosNaoEncontrados() {
        List<Object[]> salarioByFuncionario = new ArrayList<>();

        when(repository.findVlSalarioByFuncionario()).thenReturn(salarioByFuncionario);

        List<SalarioByFuncionarioOutput> result = service.listSalarioByFuncionario();

        assertThat(result).isEmpty();

        verify(repository).findVlSalarioByFuncionario();
        verify(mapper, never()).toSalarioByFuncionarioOutput(any(Object[].class));
    }

}
