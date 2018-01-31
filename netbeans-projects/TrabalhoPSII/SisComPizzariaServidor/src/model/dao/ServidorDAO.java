package model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ServidorDAO {

    private EntityManagerFactory emf;

    public ServidorDAO() {
        emf = Persistence.createEntityManagerFactory("unitSisComPizzaria");
    }

    public void desconectar() {
        if (emf != null) {
            emf.close();
        }
    }

    public void salvar(Object obj, boolean novoObj) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            if (novoObj) {
                em.persist(obj);
            } else {
                em.merge(obj);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em != null) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
