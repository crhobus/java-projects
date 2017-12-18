package edu.furb.easyboleto.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {

    @Produces
    @ApplicationScoped
    public EntityManagerFactory createFactory() {
        return Persistence.createEntityManagerFactory("EasyBoletoPU");
    }

    @Produces
    @RequestScoped
    public EntityManager getEntityManager(EntityManagerFactory factory) {
        return factory.createEntityManager();
    }

    public void closeEntityManager(@Disposes EntityManager manager) {
        manager.close();
    }

    public void closeEntityManagerFactory(@Disposes EntityManagerFactory factory) {
        factory.close();
    }
}
