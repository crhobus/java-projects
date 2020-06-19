package br.com.app.usuario;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.app.CommonBaseTest;
import br.com.app.common.builder.UsuarioEntityBuilder;
import br.com.app.infra.security.utils.SecurityUtils;
import br.com.app.usuario.dao.UsuarioEntity;
import br.com.app.usuario.dao.UsuarioRepository;
import br.com.app.usuario.dto.PerfilEnum;
import br.com.app.usuario.dto.SituacaoUserEnum;

public class UsuarioServiceTest extends CommonBaseTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private UsuarioMapper mapper;

    @Mock
    private SecurityUtils securityUtils;

    @InjectMocks
    private UsuarioService service;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUsuario() {
        String email = "teste@teste.com.br";

        UsuarioEntity usuario = new UsuarioEntity();
        when(repository.findByEmail(email)).thenReturn(usuario);

        Optional<UsuarioEntity> usuarioOpt = service.getUsuario(email);

        assertThat(usuarioOpt.get()).isEqualTo(usuario);

        verify(repository).findByEmail(email);
    }

    @Test
    public void create() {
        try {
            String email = "teste@teste.com.br";
            String senha = "12345678";
            SituacaoUserEnum situacao = SituacaoUserEnum.ATIVO;
            PerfilEnum perfil = PerfilEnum.ROLE_ADMIN;

            when(repository.existsByEmail(email)).thenReturn(Boolean.FALSE);

            UsuarioEntity usuario = UsuarioEntityBuilder.create(1L).withEmail(email).withSenha(senha).withSituacao(situacao).withPerfil(perfil).build();
            when(mapper.toEntity(securityUtils, email, senha, situacao, perfil)).thenReturn(usuario);

            when(repository.save(usuario)).thenReturn(usuario);

            UsuarioEntity result = service.create(email, senha, situacao, perfil);

            ArgumentCaptor<UsuarioEntity> captor = ArgumentCaptor.forClass(UsuarioEntity.class);
            verify(repository).save(captor.capture());
            UsuarioEntity capturedUsuario = captor.getValue();

            assertThat(capturedUsuario).isNotNull();
            assertThat(capturedUsuario.getId()).isEqualTo(usuario.getId());
            assertThat(capturedUsuario.getEmail()).isEqualTo(usuario.getEmail());
            assertThat(capturedUsuario.getSenha()).isEqualTo(usuario.getSenha());
            assertThat(capturedUsuario.getPerfil()).isEqualTo(usuario.getPerfil());
            assertThat(capturedUsuario.getSituacao()).isEqualTo(usuario.getSituacao());

            assertThat(capturedUsuario).isSameAs(result);

            verify(repository).existsByEmail(email);
            verify(mapper).toEntity(securityUtils, email, senha, situacao, perfil);
            verify(repository).save(usuario);
        } catch (Exception ex) {
            failBecauseExceptionWasNotThrown(Exception.class);
        }
    }

    @Test
    public void create_emailInvalido() {
        String email = "teste@teste.com.br";
        String senha = "12345678";
        SituacaoUserEnum situacao = SituacaoUserEnum.ATIVO;
        PerfilEnum perfil = PerfilEnum.ROLE_ADMIN;

        try {
            when(repository.existsByEmail(email)).thenReturn(Boolean.TRUE);

            service.create(email, senha, situacao, perfil);
            failBecauseExceptionWasNotThrown(Exception.class);
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("E-mail inválido, o mesmo já se encontra cadastrado");
        }

        verify(repository).existsByEmail(email);
        verify(mapper, never()).toEntity(any(SecurityUtils.class), any(String.class), any(String.class), any(SituacaoUserEnum.class), any(PerfilEnum.class));
        verify(repository, never()).save(any(UsuarioEntity.class));
    }

    @Test
    public void update() {
        try {
            long id = 1L;
            String email = "teste@teste.com.br";
            String senha = "12345678";
            SituacaoUserEnum situacao = SituacaoUserEnum.ATIVO;
            PerfilEnum perfil = PerfilEnum.ROLE_ADMIN;

            UsuarioEntity usuario = UsuarioEntityBuilder.create(1L).withEmail(email).withSenha("123456@abc").withSituacao(SituacaoUserEnum.INATIVO).withPerfil(PerfilEnum.ROLE_USUARIO).build();
            Optional<UsuarioEntity> usuarioOpt = Optional.of(usuario);
            when(repository.findById(id)).thenReturn(usuarioOpt);

            when(repository.existsByEmailIgnoringCurrentUser(id, email)).thenReturn(Boolean.FALSE);

            when(repository.save(usuario)).thenReturn(usuario);

            UsuarioEntity result = service.update(id, email, senha, situacao, perfil);

            ArgumentCaptor<UsuarioEntity> captor = ArgumentCaptor.forClass(UsuarioEntity.class);
            verify(repository).save(captor.capture());
            UsuarioEntity capturedUsuario = captor.getValue();

            assertThat(capturedUsuario).isNotNull();
            assertThat(capturedUsuario.getId()).isEqualTo(usuario.getId());
            assertThat(capturedUsuario.getEmail()).isEqualTo(usuario.getEmail());
            assertThat(capturedUsuario.getSenha()).isEqualTo(usuario.getSenha());
            assertThat(capturedUsuario.getPerfil()).isEqualTo(usuario.getPerfil());
            assertThat(capturedUsuario.getSituacao()).isEqualTo(usuario.getSituacao());

            assertThat(capturedUsuario).isSameAs(result);

            verify(repository).findById(id);
            verify(repository).existsByEmailIgnoringCurrentUser(id, email);
            verify(mapper).merge(usuario, securityUtils, email, senha, situacao, perfil);
            verify(repository).save(usuario);
        } catch (Exception ex) {
            failBecauseExceptionWasNotThrown(Exception.class);
        }
    }

    @Test
    public void update_idNaoEncontrado() {
        try {
            long id = 1L;
            String email = "teste@teste.com.br";
            String senha = "12345678";
            SituacaoUserEnum situacao = SituacaoUserEnum.ATIVO;
            PerfilEnum perfil = PerfilEnum.ROLE_ADMIN;

            Optional<UsuarioEntity> usuarioOpt = Optional.empty();
            when(repository.findById(id)).thenReturn(usuarioOpt);

            UsuarioEntity result = service.update(id, email, senha, situacao, perfil);

            assertThat(result).isNull();

            verify(repository).findById(id);
            verify(repository, never()).existsByEmailIgnoringCurrentUser(any(Long.class), any(String.class));
            verify(mapper, never()).merge(any(UsuarioEntity.class), any(SecurityUtils.class), any(String.class), any(String.class), any(SituacaoUserEnum.class), any(PerfilEnum.class));
            verify(repository, never()).save(any(UsuarioEntity.class));
        } catch (Exception ex) {
            failBecauseExceptionWasNotThrown(Exception.class);
        }
    }

    @Test
    public void update_emailInvalido() {
        long id = 1L;
        String email = "teste@teste.com.br";
        String senha = "12345678";
        SituacaoUserEnum situacao = SituacaoUserEnum.ATIVO;
        PerfilEnum perfil = PerfilEnum.ROLE_ADMIN;

        try {
            UsuarioEntity usuario = UsuarioEntityBuilder.create(1L).withEmail(email).withSenha("123456@abc").withSituacao(SituacaoUserEnum.INATIVO).withPerfil(PerfilEnum.ROLE_USUARIO).build();
            Optional<UsuarioEntity> usuarioOpt = Optional.of(usuario);
            when(repository.findById(id)).thenReturn(usuarioOpt);

            when(repository.existsByEmailIgnoringCurrentUser(id, email)).thenReturn(Boolean.TRUE);

            service.update(id, email, senha, situacao, perfil);
            failBecauseExceptionWasNotThrown(Exception.class);
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("E-mail inválido, o mesmo já se encontra cadastrado");
        }

        verify(repository).findById(id);
        verify(repository).existsByEmailIgnoringCurrentUser(id, email);
        verify(mapper, never()).merge(any(UsuarioEntity.class), any(SecurityUtils.class), any(String.class), any(String.class), any(SituacaoUserEnum.class), any(PerfilEnum.class));
        verify(repository, never()).save(any(UsuarioEntity.class));
    }
}
