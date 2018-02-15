package br.server.control.action;

/*
 * Document   : PerfilAction.java
 * Created on : 05/09/2013, 21:04:57
 * Author     : Caio
 */
import br.server.control.action.session.SessionUsuario;
import br.server.model.entities.Perfil;
import br.server.model.entities.Usuario;
import br.server.model.util.ConexaoUtil;
import br.server.model.util.repository.PerfilRepository;
import br.server.model.util.repository.UsuarioRepository;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "perfilBean")
@ViewScoped
public class PerfilAction implements Serializable {

    private Perfil perfil;
    private List<Perfil> perfis;
    private Usuario usuario;
    private List<Usuario> usuarios;
    private String dsPesquisa;
    private long nrSeqUsuario;

    public PerfilAction() {
        this.perfil = new Perfil();
        obterPerfilEdicao();
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        PerfilRepository repository = getPerfilRepository();
        perfil.setDtAtualizacao(new Date());
        perfil.setNmUsuarioAtualizacao(context.getExternalContext().getRemoteUser());
        if (perfil.getNrSequencia() != 0) {
            if (repository.getPerfil(perfil.getNrSequencia(), perfil.getNmPerfil()) != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil", "Este nome de perfil já existe.\n"
                        + "Entre com outro nome de perfil!"));
                return;
            }
            Perfil p = repository.getPerfil(perfil.getNrSequencia());
            perfil.setUsuarios(p.getUsuarios());
            perfil.setRecursos(p.getRecursos());
            repository.updatePerfil(perfil);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil", "Perfil atualizado com sucesso"));
        } else {
            if (repository.getPerfil(perfil.getNmPerfil()) != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil", "Este nome de perfil já existe.\n"
                        + "Entre com outro nome de perfil!"));
                return;
            }
            repository.insertPerfil(perfil);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil", "Perfil cadastrado com sucesso"));
        }
    }

    public String alterar() {
        return "/pages/admin/cadasPerfil";
    }

    public void excluir() {
        getPerfilRepository().deletePerfil(perfil.getNrSequencia());
        perfis = null;
        usuarios = null;
    }

    public String pesquisar() {
        perfis = getPerfilRepository().getPerfis(getDsPesquisa());
        return null;
    }

    public List<Perfil> getPerfis() {
        if (perfis == null) {
            perfis = getPerfilRepository().getPerfis();
        }
        return perfis;
    }

    public List<Usuario> getUsuarios() {
        return getUsuarioRepository().getUsuarios();
    }

    public void addUsuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (perfil.getNrSequencia() == 0l) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil", "Para adicionar usuários é necessário selecionar um perfil"));
            return;
        }
        if (nrSeqUsuario == 0l) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil", "Favor selecionar um usuário!"));
            return;
        }
        PerfilRepository repository = getPerfilRepository();
        Perfil p = repository.getPerfil(perfil.getNrSequencia());
        perfil.setUsuarios(p.getUsuarios());
        perfil.setRecursos(p.getRecursos());
        for (Usuario u : perfil.getUsuarios()) {
            if (u.getNrSequencia() == nrSeqUsuario) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil", "Este perfil já possui este usuário"));
                return;
            }
        }
        Usuario user = getUsuarioRepository().getUsuario(nrSeqUsuario);
        perfil.getUsuarios().add(user);
        perfil.setDtAtualizacao(new Date());
        perfil.setNmUsuarioAtualizacao(context.getExternalContext().getRemoteUser());
        repository.updatePerfil(perfil);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil", "Novo usuário adicionado ao perfil"));
        usuarios = null;
        nrSeqUsuario = 0;
    }

    public void excluirUsuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        perfil.getUsuarios().remove(usuario);
        perfil.setDtAtualizacao(new Date());
        perfil.setNmUsuarioAtualizacao(context.getExternalContext().getRemoteUser());
        getPerfilRepository().updatePerfil(perfil);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil", "Usuário removido do perfil"));
        usuarios = null;
    }

    public List<Usuario> getUsuariosPerfil() {
        if (usuarios == null
                || usuarios.isEmpty()) {
            usuarios = perfil.getUsuarios();
        }
        return usuarios;
    }

    private void obterPerfilEdicao() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        if (sessionMap != null
                && !sessionMap.isEmpty()
                && sessionMap.containsKey("sessionBean")) {
            SessionUsuario sessionUsuario = (SessionUsuario) sessionMap.get("sessionBean");
            if (sessionUsuario != null
                    && sessionUsuario.getNrSeqPerfilEdicao() != 0) {
                perfil = getPerfilRepository().getPerfil(sessionUsuario.getNrSeqPerfilEdicao());
                sessionUsuario.setNrSeqPerfilEdicao(0);
                context.getExternalContext().getSessionMap().put("sessionBean", sessionUsuario);
            }
        }
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getDsPesquisa() {
        return dsPesquisa;
    }

    public void setDsPesquisa(String dsPesquisa) {
        this.dsPesquisa = dsPesquisa;
    }

    public long getNrSeqUsuario() {
        return nrSeqUsuario;
    }

    public void setNrSeqUsuario(long nrSeqUsuario) {
        this.nrSeqUsuario = nrSeqUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private UsuarioRepository getUsuarioRepository() {
        return new UsuarioRepository(new ConexaoUtil());
    }

    private PerfilRepository getPerfilRepository() {
        return new PerfilRepository(new ConexaoUtil());
    }
}