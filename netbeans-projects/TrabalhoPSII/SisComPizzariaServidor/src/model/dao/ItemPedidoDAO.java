package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.entidades.ItemPedido;
import model.entidades.Tamanho;

public class ItemPedidoDAO extends ServidorDAO {

    private ServidorDAO servidorDAO;

    public ItemPedidoDAO(ServidorDAO servidorDAO) {
        this.servidorDAO = servidorDAO;
    }

    public List<ItemPedido> getTamanhos() {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select c from ItemPedido as c");
        List<ItemPedido> itens = q.getResultList();
        em.close();
        return itens;
    }

    public void excluirItemPedido(int codItemPedido) throws Exception {
        EntityManager em = null;
        try {
            em = servidorDAO.getEntityManager();
            em.getTransaction().begin();
            ItemPedido itemPedido = em.find(ItemPedido.class, codItemPedido);
            em.remove(itemPedido);
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
    
    public Tamanho getItensPedido(String cd_item_pedido) throws Exception {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select u from ItemPedido as u "
                + "where u.cd_item_pedido = :cd_item_pedido ");
        q.setParameter("cd_item_pedido", cd_item_pedido);
        return (Tamanho) q.getSingleResult();
    }
    
}
