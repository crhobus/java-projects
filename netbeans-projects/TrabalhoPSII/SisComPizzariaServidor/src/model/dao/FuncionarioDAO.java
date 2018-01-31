package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.entidades.Funcionario;
import model.entidades.Usuario;

public class FuncionarioDAO {

    private ServidorDAO servidorDAO;

    public FuncionarioDAO(ServidorDAO servidorDAO) {
        this.servidorDAO = servidorDAO;
    }

    public List<Funcionario> getFuncionarios() {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select f from Funcionario as f");
        List<Funcionario> funcionarios = q.getResultList();
        em.close();
        return funcionarios;
    }

    public void excluirFuncionario(int cdFuncionario) throws Exception {
        EntityManager em = null;
        try {
            em = servidorDAO.getEntityManager();
            em.getTransaction().begin();
            Funcionario funcionario = em.find(Funcionario.class, cdFuncionario);
            em.remove(funcionario);
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

    public Usuario getUsuario(String nmUsuario, int cdFuncionario) throws Exception {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select u "
                + "from  Funcionario f "
                + "join  f.usuario u "
                + "where u.nmUsuario = :nmUsuario "
                + "and   f.cdFuncionario <> :cdFuncionario");
        q.setParameter("nmUsuario", nmUsuario);
        q.setParameter("cdFuncionario", cdFuncionario);
        Usuario usuario = (Usuario) q.getSingleResult();
        em.close();
        return usuario;
    }
}
