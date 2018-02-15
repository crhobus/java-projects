package br.server.control.action;

/*
 * Document   : RegraAction.java
 * Created on : 28/08/2013, 20:08:42
 * Author     : Caio
 */
import br.server.control.action.session.SessionUsuario;
import br.server.model.entities.Regra;
import br.server.model.entities.Sistema;
import br.server.model.util.ConexaoUtil;
import br.server.model.util.repository.RegraRepository;
import br.server.model.util.repository.SistemaRepository;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "regraBean")
@ViewScoped
public class RegraAction implements Serializable {

    private Regra regra;
    private List<Regra> regras;
    private String dsPesquisa;

    public RegraAction() {
        this.regra = new Regra();
        this.regra.setSistema(new Sistema());
        obterRegraEdicao();
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        RegraRepository repository = getRegraRepository();
        if (regra.getSistema().getNrSequencia() == 0l) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Regra", "É necessário informar um sistema na qual esta regra pertence"));
            return;
        }
        if (regra.getCdRegraComunic().length() < 8) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Regra", "O código de comunicação deve conter no mínimo 8 caracteres"));
            return;
        }
        regra.setDtAtualizacao(new Date());
        regra.setNmUsuarioAtualizacao(context.getExternalContext().getRemoteUser());
        Sistema sistema = getSistemaRepository().getSistema(regra.getSistema().getNrSequencia());
        if (regra.getNrSequencia() != 0) {
            if (repository.getRegra(regra.getNrSequencia(), regra.getNmRegra(), null) != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Regra", "Este nome de regra já existe.\n"
                        + "Entre com outro nome de regra!"));
                return;
            }
            if (repository.getRegra(regra.getNrSequencia(), null, regra.getCdRegraComunic()) != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Regra", "Este código de comunicação já existe.\n"
                        + "Entre com outro código de comunicação!"));
                return;
            }
            regra.setSistema(sistema);
            repository.updateRegra(regra);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Regra", "Regra atualizada com sucesso"));
        } else {
            if (repository.getRegra(regra.getNmRegra(), null) != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Regra", "Este nome de regra já existe.\n"
                        + "Entre com outro nome de regra!"));
                return;
            }
            if (repository.getRegra(null, regra.getCdRegraComunic()) != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Regra", "Este código de comunicação já existe.\n"
                        + "Entre com outro código de comunicação!"));
                return;
            }
            regra.setSistema(sistema);
            repository.insertRegra(regra);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Regra", "Regra cadastrada com sucesso"));
        }
    }

    public String alterar() {
        return "/pages/admin/cadasRegra";
    }

    public void excluir() {
        try {
            getRegraRepository().deleteRegra(regra.getNrSequencia());
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Regra", ex.getMessage()));
        }
        regras = null;
    }

    public String pesquisar() {
        regras = getRegraRepository().getRegras(getDsPesquisa(), true);
        return null;
    }

    public List<Regra> getRegras() {
        if (regras == null) {
            regras = getRegraRepository().getRegras(true);
        }
        return regras;
    }

    public List<Sistema> getSistemas() {
        return getSistemaRepository().getSistemas();
    }

    private void obterRegraEdicao() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        if (sessionMap != null
                && !sessionMap.isEmpty()
                && sessionMap.containsKey("sessionBean")) {
            SessionUsuario sessionUsuario = (SessionUsuario) sessionMap.get("sessionBean");
            if (sessionUsuario != null
                    && sessionUsuario.getNrSeqRegraEdicao() != 0) {
                regra = getRegraRepository().getRegra(sessionUsuario.getNrSeqRegraEdicao());
                sessionUsuario.setNrSeqRegraEdicao(0);
                context.getExternalContext().getSessionMap().put("sessionBean", sessionUsuario);
            }
        }
    }

    public Regra getRegra() {
        return regra;
    }

    public void setRegra(Regra regra) {
        this.regra = regra;
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

    private RegraRepository getRegraRepository() {
        return new RegraRepository(new ConexaoUtil());
    }
}