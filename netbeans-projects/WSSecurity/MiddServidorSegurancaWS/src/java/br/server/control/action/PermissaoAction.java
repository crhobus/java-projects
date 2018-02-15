package br.server.control.action;

/*
 * Document   : PermissaoAction.java
 * Created on : 26/08/2013, 21:05:12
 * Author     : Caio
 */
import br.server.control.action.session.SessionUsuario;
import br.server.model.entities.Permissao;
import br.server.model.util.ConexaoUtil;
import br.server.model.util.repository.PermissaoRepository;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "permissaoBean")
@ViewScoped
public class PermissaoAction implements Serializable {
    
    private Permissao permissao;
    private List<Permissao> permissoes;
    private String dsPesquisa;
    
    public PermissaoAction() {
        this.permissao = new Permissao();
        obterPermissaoEdicao();
    }
    
    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        PermissaoRepository repository = getPermissaoRepository();
        if (permissao.getCdPermissaoComunic().length() < 8) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Permissão", "O código de comunicação deve conter no mínimo 8 caracteres"));
            return;
        }
        permissao.setDtAtualizacao(new Date());
        permissao.setNmUsuarioAtualizacao(context.getExternalContext().getRemoteUser());
        if (permissao.getNrSequencia() != 0) {
            if (repository.getPermissao(permissao.getNrSequencia(), permissao.getNmPermissao(), null) != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Permissão", "Este nome de permissão já existe.\n"
                        + "Entre com outro nome de permissão!"));
                return;
            }
            if (repository.getPermissao(permissao.getNrSequencia(), null, permissao.getCdPermissaoComunic()) != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Permissão", "Este código de comunicação já existe.\n"
                        + "Entre com outro código de comunicação!"));
                return;
            }
            repository.updatePermissao(permissao);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Permissão", "Permissão atualizado com sucesso"));
        } else {
            if (repository.getPermissao(permissao.getNmPermissao(), null) != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Permissão", "Este nome de permissão já existe.\n"
                        + "Entre com outro nome de permissão!"));
                return;
            }
            if (repository.getPermissao(null, permissao.getCdPermissaoComunic()) != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Permissão", "Este código de comunicação já existe.\n"
                        + "Entre com outro código de comunicação!"));
                return;
            }
            repository.insertPermissao(permissao);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Permissão", "Permissão cadastrado com sucesso"));
        }
    }
    
    public String alterar() {
        return "/pages/admin/cadasPermissao";
    }
    
    public void excluir() {
        try {
            getPermissaoRepository().deletePermissao(permissao.getNrSequencia());
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Permissão", ex.getMessage()));
        }
        permissoes = null;
    }
    
    public String pesquisar() {
        permissoes = getPermissaoRepository().getPermissoes(getDsPesquisa());
        return null;
    }
    
    public List<Permissao> getPermissoes() {
        if (permissoes == null) {
            permissoes = getPermissaoRepository().getPermissoes();
        }
        return permissoes;
    }
    
    private void obterPermissaoEdicao() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        if (sessionMap != null
                && !sessionMap.isEmpty()
                && sessionMap.containsKey("sessionBean")) {
            SessionUsuario sessionUsuario = (SessionUsuario) sessionMap.get("sessionBean");
            if (sessionUsuario != null
                    && sessionUsuario.getNrSeqPermissaoEdicao() != 0) {
                permissao = getPermissaoRepository().getPermissao(sessionUsuario.getNrSeqPermissaoEdicao());
                sessionUsuario.setNrSeqPermissaoEdicao(0);
                context.getExternalContext().getSessionMap().put("sessionBean", sessionUsuario);
            }
        }
    }
    
    public Permissao getPermissao() {
        return permissao;
    }
    
    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }
    
    public String getDsPesquisa() {
        return dsPesquisa;
    }
    
    public void setDsPesquisa(String dsPesquisa) {
        this.dsPesquisa = dsPesquisa;
    }
    
    private PermissaoRepository getPermissaoRepository() {
        return new PermissaoRepository(new ConexaoUtil());
    }
}
