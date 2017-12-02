package edu.furb.catalogo.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Caio
 */
public class JPAUtils {

    @Produces
    @ApplicationScoped
    public EntityManagerFactory createFactory() {
        return Persistence.createEntityManagerFactory("CatalogoEnderecoPU");
    }

    @Produces
    @RequestScoped
    public EntityManager getEntityManager(EntityManagerFactory factory) {
        return factory.createEntityManager();
    }

    public void closeEntityManager(@Disposes EntityManager manager) {
        manager.close();
    }
}
