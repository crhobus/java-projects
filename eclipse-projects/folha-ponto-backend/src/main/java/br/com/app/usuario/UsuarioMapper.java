package br.com.app.usuario;

import org.springframework.stereotype.Component;

import br.com.app.infra.security.utils.SecurityUtils;
import br.com.app.usuario.dao.UsuarioEntity;
import br.com.app.usuario.dto.PerfilEnum;
import br.com.app.usuario.dto.SituacaoUserEnum;

@Component
public class UsuarioMapper {

    public UsuarioEntity toEntity(SecurityUtils securityUtils, String email, String senha, SituacaoUserEnum situacao, PerfilEnum perfil) {
        UsuarioEntity entity = new UsuarioEntity();

        merge(entity, securityUtils, email, senha, situacao, perfil);

        return entity;
    }

    public void merge(UsuarioEntity entity, SecurityUtils securityUtils, String email, String senha, SituacaoUserEnum situacao, PerfilEnum perfil) {
        entity.setEmail(email);
        entity.setPerfil(perfil);
        entity.setSituacao(situacao);
        entity.setSenha(securityUtils.gerarSenha(senha));
    }
}
