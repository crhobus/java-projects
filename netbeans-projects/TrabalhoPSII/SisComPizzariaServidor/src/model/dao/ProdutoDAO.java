package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.entidades.Cliente;
import model.entidades.Produto;
import model.entidades.Usuario;

public class ProdutoDAO extends ServidorDAO {

    private ServidorDAO servidorDAO;

    public ProdutoDAO(ServidorDAO servidorDAO) {
        this.servidorDAO = servidorDAO;
    }

    public List<Produto> getProdutos() {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select c from Produto as c");
        List<Produto> produtos = q.getResultList();
        em.close();
        return produtos;
    }

    public void excluirProduto(int codProdito) throws Exception {
        EntityManager em = null;
        try {
            em = servidorDAO.getEntityManager();
            em.getTransaction().begin();
            Produto produto = em.find(Produto.class, codProdito);
            em.remove(produto);
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

    public Produto getProduto(int cod_produto, String des_produto) throws Exception {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select u from Produto as u "
                + "where u.cod_produto = :cod_produto "
                + "and u.des_produto = :des_produto ");
        q.setParameter("cod_produto", cod_produto);
        q.setParameter("des_produto", des_produto);
        return (Produto) q.getSingleResult();
    }
    
    public Produto getProduto(String des_produto) throws Exception {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select u from Produto as u "
                + "where u.des_produto = :des_produto ");
        q.setParameter("des_produto", des_produto);
        return (Produto) q.getSingleResult();
    }
    
        public Usuario getUsuario(String nmUsuario, int cdCliente) throws Exception {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select u "
                + "from  Cliente c "
                + "join  c.usuario u "
                + "where u.nmUsuario = :nmUsuario "
                + "and   c.cdCliente <> :cdCliente");
        q.setParameter("nmUsuario", nmUsuario);
        q.setParameter("cdCliente", cdCliente);
        Usuario usuario = (Usuario) q.getSingleResult();
        em.close();
        return usuario;
    }
}
