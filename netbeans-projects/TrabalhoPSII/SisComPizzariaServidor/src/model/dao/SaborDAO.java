package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.entidades.Sabor;

public class SaborDAO extends ServidorDAO {

    private ServidorDAO servidorDAO;

    public SaborDAO(ServidorDAO servidorDAO) {
        this.servidorDAO = servidorDAO;
    }

    public List<Sabor> getSabores() {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select c from Sabor as c");
        List<Sabor> sabores = q.getResultList();
        em.close();
        return sabores;
    }

    public void excluirSabor(int codSabor) throws Exception {
        EntityManager em = null;
        try {
            em = servidorDAO.getEntityManager();
            em.getTransaction().begin();
            Sabor sabor = em.find(Sabor.class, codSabor);
            em.remove(sabor);
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

    public Sabor getSabor(int cd_sabor, String ds_sabor) throws Exception {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select u from Sabor as u "
                + "where u.cd_sabor = :cd_sabor "
                + "and u.ds_sabor = :ds_sabor ");
        q.setParameter("cod_produto", cd_sabor);
        q.setParameter("ds_sabor", ds_sabor);
        return (Sabor) q.getSingleResult();
    }
    
    public Sabor getSabor(String ds_sabor) throws Exception {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select u from Sabor as u "
                + "where u.ds_sabor = :ds_sabor ");
        q.setParameter("ds_sabor", ds_sabor);
        return (Sabor) q.getSingleResult();
    }
    
}
