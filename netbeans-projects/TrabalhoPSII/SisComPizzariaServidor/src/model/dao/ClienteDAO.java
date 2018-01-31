package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.entidades.Cliente;
import model.entidades.Usuario;

public class ClienteDAO {

    private ServidorDAO servidorDAO;

    public ClienteDAO(ServidorDAO servidorDAO) {
        this.servidorDAO = servidorDAO;
    }

    public List<Cliente> getClientes() {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select c from Cliente as c");
        List<Cliente> clientes = q.getResultList();
        em.close();
        return clientes;
    }

    public List<Cliente> getClientes(String nmCliente, String nrTefefone, String nrCelular) {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select c from Cliente c "
                + "where c.nmCliente like :nmCliente "
                + "and c.nrTefefone like :nrTefefone "
                + "and c.nrCelular like :nrCelular");
        q.setParameter("nmCliente", "%" + nmCliente + "%");
        q.setParameter("nrTefefone", "%" + nrTefefone + "%");
        q.setParameter("nrCelular", "%" + nrCelular + "%");
        List<Cliente> clientes = q.getResultList();
        em.close();
        return clientes;
    }

    public void excluirCliente(int cdCliente) throws Exception {
        EntityManager em = null;
        try {
            em = servidorDAO.getEntityManager();
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, cdCliente);
            em.remove(cliente);
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

    public Cliente getCliente(String nmUsuario) throws Exception {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select c "
                + "from  Cliente c "
                + "join  c.usuario u "
                + "where u.nmUsuario = :nmUsuario ");
        q.setParameter("nmUsuario", nmUsuario);
        Cliente cliente = (Cliente) q.getSingleResult();
        em.close();
        return cliente;
    }

    public Cliente getCliente(int cdCliente) throws Exception {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select c "
                + "from  Cliente c "
                + "where c.cdCliente = :cdCliente ");
        q.setParameter("cdCliente", cdCliente);
        Cliente cliente = (Cliente) q.getSingleResult();
        em.close();
        return cliente;
    }
}
