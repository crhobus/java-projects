package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.entidades.Funcao;
import model.entidades.Perfil;
import model.entidades.TipoUsuario;
import model.entidades.Usuario;

public class UsuarioDAO {

    private ServidorDAO servidorDAO;

    public UsuarioDAO(ServidorDAO servidorDAO) {
        this.servidorDAO = servidorDAO;
    }

    public Usuario getUsuario(String nmUsuario) throws Exception {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select u from Usuario as u where u.nmUsuario = :nmUsuario");
        q.setParameter("nmUsuario", nmUsuario);
        Usuario usuario = (Usuario) q.getSingleResult();
        em.close();
        return usuario;
    }

    public List<Usuario> getUsuarios() throws Exception {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select u from Usuario as u");
        List<Usuario> usuarios = q.getResultList();
        em.close();
        return usuarios;
    }

    public List<Funcao> getFuncoes(int cdUsuario) throws Exception {
        EntityManager em = servidorDAO.getEntityManager();
        Query q = em.createQuery("select distinct f "
                + "from  Usuario as u,"
                + "      Funcao as f "
                + "join  u.perfis p "
                + "join  p.funcao pf "
                + "where u.cdUsuario = :cdUsuario "
                + "and   pf.cdFuncao <> f.cdFuncao");
        q.setParameter("cdUsuario", cdUsuario);
        List<Funcao> funcoes = q.getResultList();
        if (funcoes.isEmpty()) {
            q = em.createQuery("select f "
                    + "from  Funcao as f ");
            funcoes = q.getResultList();
        }
        em.close();
        return funcoes;
    }

    public void addPerfilUsuario(Usuario usuario, Perfil perfil) throws Exception {
        EntityManager em = null;
        try {
            em = servidorDAO.getEntityManager();
            em.getTransaction().begin();
            em.persist(perfil);
            usuario.getPerfis().add(perfil);
            em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new Exception("Erro ao adicionar um novo perfil ao usuário");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean removePerfilFuncao(Usuario usuario, Perfil perfil) throws Exception {
        EntityManager em = null;
        try {
            em = servidorDAO.getEntityManager();
            em.getTransaction().begin();
            usuario.getPerfis().remove(perfil);
            Perfil p = em.merge(perfil);
            em.remove(p);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new Exception("Erro ao remover a função do usuário");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public TipoUsuario getTipoUsuario(int tipo) throws Exception {
        EntityManager em = servidorDAO.getEntityManager();
        TipoUsuario tipoUsuario = em.find(TipoUsuario.class, tipo);
        em.close();
        return tipoUsuario;
    }
}
