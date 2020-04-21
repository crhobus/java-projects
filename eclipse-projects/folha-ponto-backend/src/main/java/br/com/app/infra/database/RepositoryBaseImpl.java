package br.com.app.infra.database;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQuery;

public abstract class RepositoryBaseImpl<T> {

    @PersistenceContext
    protected EntityManager ctx;

    public <U> JPAQuery<U> select(Expression<U> expr) {
        return new JPAQuery<>(ctx).select(expr);
    }

    public JPAQuery<Tuple> select(Expression<?>... exprs) {
        return new JPAQuery<>(ctx).select(exprs);
    }

    protected <U> JPAQuery<U> from(EntityPath<U> entityPath) {
        return select(entityPath).from(entityPath);
    }

    @SuppressWarnings("unchecked")
    protected <N> NativeQuery<N> createNativeQuery(String query) {
        Session session = ctx.unwrap(Session.class);
        return (NativeQuery<N>) session.createSQLQuery(query);
    }

    public void refresh(T entity) {
        ctx.refresh(entity);
    }

}
