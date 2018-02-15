package br.server.model.util.repository;

/*
 * Document   : RecursoRepository.java
 * Created on : 10/09/2013, 19:25:32
 * Author     : Caio
 */
import br.server.model.entities.Perfil;
import br.server.model.entities.Recurso;
import br.server.model.entities.RecursoPermissao;
import br.server.model.entities.Usuario;
import br.server.model.util.ConexaoUtil;

public class RecursoRepository {

    private ConexaoUtil con;

    public RecursoRepository(ConexaoUtil con) {
        this.con = con;
    }

    public void insertRecurso(Recurso recurso, Perfil perfil, Usuario usuario) {
        if (perfil != null) {
            perfil.getRecursos().add(recurso);
            getPerfilRepository().updatePerfil(perfil);
        } else if (usuario != null) {
            usuario.getRecursos().add(recurso);
            getUsuarioRepository().updateUsuario(usuario);
        }
    }

    public void deleteRecurso(Recurso recurso, Perfil perfil, Usuario usuario) {
        if (perfil != null) {
            perfil.getRecursos().remove(recurso);
            getPerfilRepository().updatePerfil(perfil);
        } else if (usuario != null) {
            usuario.getRecursos().remove(recurso);
            getUsuarioRepository().updateUsuario(usuario);
        }
        recurso = getRecurso(recurso.getNrSequencia());
        con.getEntityManager().remove(recurso);
    }

    public void insertRecursoPermissao(Recurso recurso, RecursoPermissao recursoPermissao) {
        recurso.getRecursoPermissoes().add(recursoPermissao);
        con.getEntityManager().merge(recurso);
    }

    public void deleteRecursoPermissao(Recurso recurso, RecursoPermissao recursoPermissao) {
        recursoPermissao = getRecursoPermissao(recursoPermissao.getNrSequencia());
        recurso.getRecursoPermissoes().remove(recursoPermissao);
        con.getEntityManager().merge(recurso);
        con.getEntityManager().remove(recursoPermissao);
    }

    public Recurso getRecurso(long nrSequencia) {
        return con.getEntityManager().find(Recurso.class, nrSequencia);
    }

    public void updateRecurso(Recurso recurso) {
        con.getEntityManager().merge(recurso);
    }

    public RecursoPermissao getRecursoPermissao(long nrSequencia) {
        return con.getEntityManager().find(RecursoPermissao.class, nrSequencia);
    }

    private UsuarioRepository getUsuarioRepository() {
        return new UsuarioRepository(con);
    }

    private PerfilRepository getPerfilRepository() {
        return new PerfilRepository(con);
    }
}