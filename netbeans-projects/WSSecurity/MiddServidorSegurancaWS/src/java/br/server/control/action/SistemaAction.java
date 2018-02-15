package br.server.control.action;

/*
 * Document   : SistemaAction.java
 * Created on : 22/08/2013, 19:40:35
 * Author     : Caio
 */
import br.server.control.action.session.SessionUsuario;
import br.server.model.entities.Sistema;
import br.server.model.util.ConexaoUtil;
import br.server.model.util.repository.SistemaRepository;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "sistemaBean")
@ViewScoped
public class SistemaAction implements Serializable {

    private Sistema sistema;
    private List<Sistema> sistemas;
    private String dsPesquisa;

    public SistemaAction() {
        this.sistema = new Sistema();
        obterSistemaEdicao();
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        SistemaRepository repository = getSistemaRepository();
        sistema.setDtAtualizacao(new Date());
        sistema.setNmUsuarioAtualizacao(context.getExternalContext().getRemoteUser());
        if (sistema.getNrSequencia() != 0) {
            if (repository.getSistema(sistema.getNrSequencia(), sistema.getNmSistema()) != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema", "Este nome de sistema já existe.\n"
                        + "Entre com outro nome de sistema!"));
                return;
            }
            repository.updateSistema(sistema);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema", "Sistema atualizado com sucesso"));
        } else {
            if (repository.getSistema(sistema.getNmSistema()) != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema", "Este nome de sistema já existe.\n"
                        + "Entre com outro nome de sistema!"));
                return;
            }
            repository.insertSistema(sistema);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema", "Sistema cadastrado com sucesso"));
        }
    }

    public String alterar() {
        return "/pages/admin/cadasSistema";
    }

    public void excluir() {
        try {
            getSistemaRepository().deleteSistema(sistema.getNrSequencia());
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Sistema", ex.getMessage()));
        }
        sistemas = null;
    }

    public String pesquisar() {
        sistemas = getSistemaRepository().getSistemas(getDsPesquisa());
        return null;
    }

    public List<Sistema> getSistemas() {
        if (sistemas == null) {
            sistemas = getSistemaRepository().getSistemas();
        }
        return sistemas;
    }

    private void obterSistemaEdicao() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        if (sessionMap != null
                && !sessionMap.isEmpty()
                && sessionMap.containsKey("sessionBean")) {
            SessionUsuario sessionUsuario = (SessionUsuario) sessionMap.get("sessionBean");
            if (sessionUsuario != null
                    && sessionUsuario.getNrSeqSistemaEdicao() != 0) {
                sistema = getSistemaRepository().getSistema(sessionUsuario.getNrSeqSistemaEdicao());
                sessionUsuario.setNrSeqSistemaEdicao(0);
                context.getExternalContext().getSessionMap().put("sessionBean", sessionUsuario);
            }
        }
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public String getDsPesquisa() {
        return dsPesquisa;
    }

    public void setDsPesquisa(String dsPesquisa) {
        this.dsPesquisa = dsPesquisa;
    }

    private SistemaRepository getSistemaRepository() {
        return new SistemaRepository(new ConexaoUtil());
    }
}