package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.entidades.Sabor;
import model.entidades.Tamanho;

public class TamanhoDAO extends ServidorDAO {

    private ServidorDAO servidorDAO;

    public TamanhoDAO(ServidorDAO servidorDAO) {
        this.servidorDAO = servidorDAO;
    }

    public List<Tamanho> getTamanhos() {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select c from Tamanho as c");
        List<Tamanho> tamanhos = q.getResultList();
        em.close();
        return tamanhos;
    }

    public void excluirTamanho(int codTamanho) throws Exception {
        EntityManager em = null;
        try {
            em = servidorDAO.getEntityManager();
            em.getTransaction().begin();
            Tamanho tamanho = em.find(Tamanho.class, codTamanho);
            em.remove(tamanho);
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
    
    public Tamanho getTamanho(String ds_tamanho) throws Exception {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select u from Tamanho as u "
                + "where u.ds_tamanho = :ds_tamanho ");
        q.setParameter("ds_tamanho", ds_tamanho);
        return (Tamanho) q.getSingleResult();
    }
    
    public Tamanho getTamanho(int cd_tamanho) throws Exception {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select u from Tamanho as u "
                + "where u.cd_tamanho = :cd_tamanho ");
        q.setParameter("cd_tamanho", cd_tamanho);
        return (Tamanho) q.getSingleResult();
    }
    
    public List<Tamanho> getTamanhos(boolean tm_ativo) {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select c from Tamanho as c"
                + "where c.tm_ativo = :tm_ativo ");
        q.setParameter("tm_ativo", tm_ativo);
        List<Tamanho> tamanhos = q.getResultList();
        em.close();
        return tamanhos;
    }
}
