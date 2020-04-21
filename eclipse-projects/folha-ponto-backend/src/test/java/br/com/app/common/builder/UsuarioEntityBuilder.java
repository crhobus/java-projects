package br.com.app.common.builder;

import br.com.app.usuario.dao.UsuarioEntity;
import br.com.app.usuario.dto.PerfilEnum;
import br.com.app.usuario.dto.SituacaoUserEnum;

public class UsuarioEntityBuilder {

    private UsuarioEntity entity;

    public static UsuarioEntityBuilder create(long id) {
        UsuarioEntityBuilder builder = new UsuarioEntityBuilder();
        builder.entity = new UsuarioEntity();
        builder.entity.setId(id);
        return builder;
    }

    public static UsuarioEntityBuilder create() {
        UsuarioEntityBuilder builder = new UsuarioEntityBuilder();
        builder.entity = new UsuarioEntity();
        return builder;
    }

    public UsuarioEntityBuilder withEmail(String email) {
        entity.setEmail(email);
        return this;
    }

    public UsuarioEntityBuilder withSenha(String senha) {
        entity.setSenha(senha);
        return this;
    }

    public UsuarioEntityBuilder withSituacao(SituacaoUserEnum situacao) {
        entity.setSituacao(situacao);
        return this;
    }

    public UsuarioEntityBuilder withPerfil(PerfilEnum perfil) {
        entity.setPerfil(perfil);
        return this;
    }

    public UsuarioEntity build() {
        return entity;
    }
}
