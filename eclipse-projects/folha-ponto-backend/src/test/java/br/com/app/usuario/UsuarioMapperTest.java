package br.com.app.usuario;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.app.CommonBaseTest;
import br.com.app.infra.security.utils.SecurityUtils;
import br.com.app.usuario.dao.UsuarioEntity;
import br.com.app.usuario.dto.PerfilEnum;
import br.com.app.usuario.dto.SituacaoUserEnum;

public class UsuarioMapperTest extends CommonBaseTest {

    @Mock
    private SecurityUtils securityUtils;

    @InjectMocks
    private UsuarioMapper mapper;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toEntity() {
        String email = "teste@teste.com.br";
        String senha = "12345678";
        SituacaoUserEnum situacao = SituacaoUserEnum.ATIVO;
        PerfilEnum perfil = PerfilEnum.ROLE_ADMIN;

        UsuarioEntity result = mapper.toEntity(securityUtils, email, senha, situacao, perfil);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(email);
        assertThat(result.getPerfil()).isEqualTo(perfil);
        assertThat(result.getSituacao()).isEqualTo(situacao);
        assertThat(result.getSenha()).isEqualTo(securityUtils.gerarSenha(senha));
    }

    @Test
    public void merge() {
        String email = "teste@teste.com.br";
        String senha = "12345678";
        SituacaoUserEnum situacao = SituacaoUserEnum.ATIVO;
        PerfilEnum perfil = PerfilEnum.ROLE_ADMIN;
        UsuarioEntity usuario = new UsuarioEntity();

        mapper.merge(usuario, securityUtils, email, senha, situacao, perfil);

        assertThat(usuario.getEmail()).isEqualTo(email);
        assertThat(usuario.getPerfil()).isEqualTo(perfil);
        assertThat(usuario.getSituacao()).isEqualTo(situacao);
        assertThat(usuario.getSenha()).isEqualTo(securityUtils.gerarSenha(senha));
    }
}
