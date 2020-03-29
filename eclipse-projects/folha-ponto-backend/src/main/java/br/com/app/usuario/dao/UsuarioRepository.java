package br.com.app.usuario.dao;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;

import br.com.app.infra.database.RepositoryBase;

@Repository
public interface UsuarioRepository extends RepositoryBase<UsuarioEntity> {

    public UsuarioEntity findByEmail(String email);

    public boolean existsByEmail(String email);

    public default boolean existsByEmailIgnoringCurrentUser(long id, String email) {
        QUsuarioEntity usuario = QUsuarioEntity.usuarioEntity;

        BooleanExpression bol = usuario.email.eq(email) //
                .and(usuario.id.ne(id));

        return exists(bol);
    }
}
