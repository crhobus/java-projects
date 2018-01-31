package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.entidades.Pedido;

public class PedidoDAO extends ServidorDAO {

    private ServidorDAO servidorDAO;

    public PedidoDAO(ServidorDAO servidorDAO) {
        this.servidorDAO = servidorDAO;
    }

    public List<Pedido> getPedidos() {
        EntityManager em = servidorDAO.getEntityManager(); 
        Query q = em.createQuery("select i from Pedido as i");
        List<Pedido> itens = q.getResultList();
        em.close();
        return itens;
    }

    public void excluirPedido(int codPedido) throws Exception {
        EntityManager em = null;
        try {
            em = servidorDAO.getEntityManager();
            em.getTransaction().begin();
            Pedido pedido = em.find(Pedido.class, codPedido);
            em.remove(pedido);
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

    public Pedido getPedido(int cd_pedido) throws Exception {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select u from Pedido as u "
                + "where u.cd_pedido = :cd_pedido "
                + "and u.ds_sabor = :ds_sabor ");
        q.setParameter("cd_pedido", cd_pedido);
        return (Pedido) q.getSingleResult();
    }
    
    
}
