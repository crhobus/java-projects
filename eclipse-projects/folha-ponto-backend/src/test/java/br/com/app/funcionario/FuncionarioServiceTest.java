package br.com.app.funcionario;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.app.CommonBaseTest;
import br.com.app.common.builder.FuncionarioDtoBuilder;
import br.com.app.common.builder.FuncionarioEntityBuilder;
import br.com.app.empresa.EmpresaService;
import br.com.app.empresa.dao.EmpresaEntity;
import br.com.app.funcionario.dao.FuncionarioEntity;
import br.com.app.funcionario.dao.FuncionarioRepository;
import br.com.app.funcionario.dto.FuncionarioDto;
import br.com.app.lancamento.LancamentoService;
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
            assertThat(capturedFuncionario.getEmpresa()).isEqualTo(funcionario.getEmpresa());
            assertThat(capturedFuncionario.getUsuario()).isEqualTo(funcionario.getUsuario());

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
            assertThat(capturedFuncionario.getEmpresa()).isEqualTo(funcionario.getEmpresa());
            assertThat(capturedFuncionario.getUsuario()).isEqualTo(funcionario.getUsuario());

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
}
