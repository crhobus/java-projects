package br.server.model.util.repository;

/*
 * Document   : UsuarioRepository.java
 * Created on : 25/05/2013, 08:50:45
 * Author     : Caio
 */
import br.server.model.entities.Usuario;
import br.server.model.util.Conexao;
import br.server.model.util.ConexaoUtil;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.util.DigestUtils;

public class UsuarioRepository {

    private ConexaoUtil con;

    public UsuarioRepository(ConexaoUtil con) {
        this.con = con;
    }

    public void insertUsuario(Usuario usuario) {
        con.getEntityManager().persist(usuario);
    }

    public void updateUsuario(Usuario usuario) {
        con.getEntityManager().merge(usuario);
    }

    public Usuario getUsuario(long nrSequencia) {
        return con.getEntityManager().find(Usuario.class, nrSequencia);
    }

    public void deleteUsuario(long nrSequencia) {
        Usuario usuario = getUsuario(nrSequencia);
        con.getEntityManager().remove(usuario);
    }

    public Usuario getUsuario(String nmUsuario) {
        Query query = con.getEntityManager().createQuery("select u "
                + "from  Usuario u "
                + "where upper(u.nmUsuario) = upper(:nmUsuario)");
        query.setParameter("nmUsuario", nmUsuario);
        List<Usuario> usuarios = query.getResultList();
        if (!usuarios.isEmpty()) {
            return usuarios.get(0);
        }
        return null;
    }

    public List<Usuario> getUsuarios() {
        Query query = con.getEntityManager().createQuery("select u "
                + "from Usuario as u "
                + "order by u.nmPessoa");
        return query.getResultList();
    }

    public List<Usuario> getUsuarios(String nmPessoa) {
        Query query = con.getEntityManager().createQuery("select u "
                + "from Usuario as u "
                + "where upper(u.nmPessoa) like upper(:nmPessoa) "
                + "order by u.nmPessoa");
        query.setParameter("nmPessoa", "%" + nmPessoa + "%");
        return query.getResultList();
    }

    public void inserirUsuarioPadrao(Conexao conexao) {
        EntityManager entityManager = null;
        try {
            entityManager = conexao.getEntityManagerFactory().createEntityManager();
            Query query = entityManager.createQuery("select count(u) from Usuario as u");
            Long qtd = (Long) query.getSingleResult();
            if (qtd == null
                    || qtd.equals(0L)) {
                Usuario usuarioPadrao = new Usuario();
                usuarioPadrao.setNmPessoa("Administrador");
                usuarioPadrao.setNmUsuario("user");
                usuarioPadrao.setDsSenha(DigestUtils.md5DigestAsHex("user".getBytes()));
                usuarioPadrao.setDsEmail("user@admin.com.br");
                usuarioPadrao.setIeAtivo(true);
                usuarioPadrao.setDtAtualizacao(new Date());
                usuarioPadrao.setNmUsuarioAtualizacao("user");
                usuarioPadrao.getDsPermissao().add("ROLE_ADMINISTRADOR");
                entityManager.getTransaction().begin();
                entityManager.persist(usuarioPadrao);
                entityManager.getTransaction().commit();
            }
        } catch (Exception ex) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}