package br.server.control.action;

/*
 * Document   : RecursoAction.java
 * Created on : 10/09/2013, 19:28:56
 * Author     : Caio
 */
import br.server.control.action.session.SessionUsuario;
import br.server.model.entities.Perfil;
import br.server.model.entities.Permissao;
import br.server.model.entities.Recurso;
import br.server.model.entities.RecursoPermissao;
import br.server.model.entities.Regra;
import br.server.model.entities.Usuario;
import br.server.model.util.ConexaoUtil;
import br.server.model.util.repository.PerfilRepository;
import br.server.model.util.repository.PermissaoRepository;
import br.server.model.util.repository.RecursoRepository;
import br.server.model.util.repository.RegraRepository;
import br.server.model.util.repository.UsuarioRepository;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "recursoBean")
@ViewScoped
public class RecursoAction implements Serializable {

    private long nrSeqUsuario;
    private long nrSeqPerfil;
    private long nrSeqRegra;
    private long nrSeqPermissao;
    private List<Recurso> recursos;
    private List<RecursoPermissao> recursoPermissoes;
    private Perfil perfil;
    private Usuario usuario;
    private Recurso recurso;
    private RecursoPermissao recursoPermissao;

    public RecursoAction() {
        nrSeqPerfil = obterPerfilEdicaoRecurso();
        if (nrSeqPerfil != 0) {
            perfil = getPerfilRepository().getPerfil(nrSeqPerfil);
        }
        nrSeqUsuario = obterUsuarioEdicaoRecurso();
        if (nrSeqUsuario != 0) {
            usuario = getUsuarioRepository().getUsuario(nrSeqUsuario);
        }
    }

    public String voltar() {
        if (nrSeqUsuario != 0) {
            setUsuarioEdicao();
            nrSeqUsuario = 0;
            usuario = null;
            return "/pages/admin/cadasUsuario";
        } else if (nrSeqPerfil != 0) {
            setPerfilEdicao();
            nrSeqPerfil = 0;
            perfil = null;
            return "/pages/admin/cadasPerfil";
        }
        return null;
    }

    public String abrirPaginaRecurso() {
        return "/pages/admin/recursos";
    }

    private long obterPerfilEdicaoRecurso() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        if (sessionMap != null
                && !sessionMap.isEmpty()
                && sessionMap.containsKey("sessionBean")) {
            SessionUsuario sessionUsuario = (SessionUsuario) sessionMap.get("sessionBean");
            if (sessionUsuario != null
                    && sessionUsuario.getNrSeqPerfilEdicaoRecurso() != 0) {
                return sessionUsuario.getNrSeqPerfilEdicaoRecurso();
            }
        }
        return 0;
    }

    private long obterUsuarioEdicaoRecurso() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        if (sessionMap != null
                && !sessionMap.isEmpty()
                && sessionMap.containsKey("sessionBean")) {
            SessionUsuario sessionUsuario = (SessionUsuario) sessionMap.get("sessionBean");
            if (sessionUsuario != null
                    && sessionUsuario.getNrSeqUsuarioEdicaoRecurso() != 0) {
                return sessionUsuario.getNrSeqUsuarioEdicaoRecurso();
            }
        }
        return 0;
    }

    private void setPerfilEdicao() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        if (sessionMap != null
                && !sessionMap.isEmpty()
                && sessionMap.containsKey("sessionBean")) {
            SessionUsuario sessionUsuario = (SessionUsuario) sessionMap.get("sessionBean");
            if (sessionUsuario != null) {
                sessionUsuario.setNrSeqPerfilEdicao(nrSeqPerfil);
                context.getExternalContext().getSessionMap().put("sessionBean", sessionUsuario);
            }
        }
    }

    private void setUsuarioEdicao() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        if (sessionMap != null
                && !sessionMap.isEmpty()
                && sessionMap.containsKey("sessionBean")) {
            SessionUsuario sessionUsuario = (SessionUsuario) sessionMap.get("sessionBean");
            if (sessionUsuario != null) {
                sessionUsuario.setNrSeqUsuarioEdicao(nrSeqUsuario);
                context.getExternalContext().getSessionMap().put("sessionBean", sessionUsuario);
            }
        }
    }

    public List<Regra> getRegras() {
        return getRegraRepository().getRegras(false);
    }

    public void addRecurso() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (usuario == null
                && perfil == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Recurso", "Para adicionar recurso é necessário selecionar um perfil ou um usuário"));
            return;
        }
        if (nrSeqRegra == 0l) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Recurso", "Favor selecionar uma regra!"));
            return;
        }
        for (Recurso rec : recursos) {
            if (rec.getRegra().getNrSequencia() == nrSeqRegra) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Recurso", "Este perfil/usuário já possui este recurso"));
                return;
            }
        }
        Recurso rec = new Recurso();
        Regra regra = getRegraRepository().getRegra(nrSeqRegra);
        rec.setRegra(regra);
        rec.setDtAtualizacao(new Date());
        rec.setNmUsuarioAtualizacao(context.getExternalContext().getRemoteUser());
        getRecursoRepository().insertRecurso(rec, perfil, usuario);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Recurso", "Novo recurso adicionado ao perfil/usuário"));
        recursos = null;
        nrSeqRegra = 0;
    }

    public void excluirRecurso() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (usuario != null) {
            usuario.setDtAtualizacao(new Date());
            usuario.setNmUsuarioAtualizacao(context.getExternalContext().getRemoteUser());
        } else if (perfil != null) {
            perfil.setDtAtualizacao(new Date());
            perfil.setNmUsuarioAtualizacao(context.getExternalContext().getRemoteUser());
        }
        getRecursoRepository().deleteRecurso(recurso, perfil, usuario);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil", "Recurso removido"));
        recursos = null;
        recurso = null;
        recursoPermissoes = null;
    }

    public List<Recurso> getRecursos() {
        if (recursos == null) {
            if (nrSeqPerfil != 0) {
                perfil = getPerfilRepository().getPerfil(nrSeqPerfil);
                recursos = perfil.getRecursos();
            } else if (nrSeqUsuario != 0) {
                usuario = getUsuarioRepository().getUsuario(nrSeqUsuario);
                recursos = usuario.getRecursos();
            } else {
                recursos = null;
            }
        }
        return recursos;
    }

    public List<Permissao> getPermissoes() {
        return getPermissaoRepository().getPermissoes();
    }

    public void addPermissao() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (recurso == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Recurso", "Para adicionar uma permissão ao recurso é necessário selecionar um recurso"));
            return;
        }
        if (nrSeqPermissao == 0l) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Recurso", "Favor selecionar uma permissão!"));
            return;
        }
        RecursoRepository repository = getRecursoRepository();
        recurso = repository.getRecurso(recurso.getNrSequencia());
        for (RecursoPermissao recPermissoes : recurso.getRecursoPermissoes()) {
            if (recPermissoes.getPermissao().getNrSequencia() == nrSeqPermissao) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Recurso", "Este recurso já possui esta permissao"));
                return;
            }
        }
        RecursoPermissao recPermissao = new RecursoPermissao();
        Permissao permissao = getPermissaoRepository().getPermissao(nrSeqPermissao);
        recPermissao.setPermissao(permissao);
        recPermissao.setDtAtualizacao(new Date());
        recPermissao.setNmUsuarioAtualizacao(context.getExternalContext().getRemoteUser());
        recPermissao.setIePermissao(true);
        repository.insertRecursoPermissao(recurso, recPermissao);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Recurso", "Nova permissão adicionada ao recurso"));
        nrSeqPermissao = 0;
        onRowSelectRecurso();
    }

    public void excluirRecursoPermissao() {
        FacesContext context = FacesContext.getCurrentInstance();
        RecursoRepository repository = getRecursoRepository();
        recurso = repository.getRecurso(recurso.getNrSequencia());
        recurso.setDtAtualizacao(new Date());
        recurso.setNmUsuarioAtualizacao(context.getExternalContext().getRemoteUser());
        repository.deleteRecursoPermissao(recurso, recursoPermissao);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Recurso", "Permissão removida do recurso"));
        recursoPermissao = null;
        onRowSelectRecurso();
    }

    public void onRowSelectRecurso() {
        recursoPermissoes = null;
        getRecursoPermissoes();
    }

    public List<RecursoPermissao> getRecursoPermissoes() {
        if (recursoPermissoes == null
                && recurso != null) {
            recurso = getRecursoRepository().getRecurso(recurso.getNrSequencia());
            recursoPermissoes = recurso.getRecursoPermissoes();
        }
        return recursoPermissoes;
    }

    public void setPermitir() {
        FacesContext context = FacesContext.getCurrentInstance();
        recurso.setDtAtualizacao(new Date());
        recurso.setNmUsuarioAtualizacao(context.getExternalContext().getRemoteUser());
        getRecursoRepository().updateRecurso(recurso);
    }

    public long getNrSeqUsuario() {
        return nrSeqUsuario;
    }

    public void setNrSeqUsuario(long nrSeqUsuario) {
        this.nrSeqUsuario = nrSeqUsuario;
    }

    public long getNrSeqPerfil() {
        return nrSeqPerfil;
    }

    public void setNrSeqPerfil(long nrSeqPerfil) {
        this.nrSeqPerfil = nrSeqPerfil;
    }

    public long getNrSeqRegra() {
        return nrSeqRegra;
    }

    public void setNrSeqRegra(long nrSeqRegra) {
        this.nrSeqRegra = nrSeqRegra;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public long getNrSeqPermissao() {
        return nrSeqPermissao;
    }

    public void setNrSeqPermissao(long nrSeqPermissao) {
        this.nrSeqPermissao = nrSeqPermissao;
    }

    public RecursoPermissao getRecursoPermissao() {
        return recursoPermissao;
    }

    public void setRecursoPermissao(RecursoPermissao recursoPermissao) {
        this.recursoPermissao = recursoPermissao;
    }

    private UsuarioRepository getUsuarioRepository() {
        return new UsuarioRepository(new ConexaoUtil());
    }

    private PerfilRepository getPerfilRepository() {
        return new PerfilRepository(new ConexaoUtil());
    }

    private RegraRepository getRegraRepository() {
        return new RegraRepository(new ConexaoUtil());
    }

    private PermissaoRepository getPermissaoRepository() {
        return new PermissaoRepository(new ConexaoUtil());
    }

    private RecursoRepository getRecursoRepository() {
        return new RecursoRepository(new ConexaoUtil());
    }
}