package edu.furb.easyboleto.view;

import edu.furb.easyboleto.model.ConvenioBeneficiario;
import edu.furb.easyboleto.persistence.dao.ConvenioBeneficiarioDAO;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ConvenioBeneficiarioBean {

    @Inject
    private ConvenioBeneficiario convenioBeneficiario;
    @Inject
    private ConvenioBeneficiarioDAO convenioBeneficiarioDAO;
    private List<ConvenioBeneficiario> convenioBeneficiarios;
    private UIData selecionado;

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (convenioBeneficiario.getNrSequencia() > 0l) {
            convenioBeneficiarioDAO.update(convenioBeneficiario);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Convênio beneficiario", "Convênio beneficiario atualizado com sucesso"));
        } else {
            convenioBeneficiarioDAO.insert(convenioBeneficiario);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Convênio beneficiario", "Convênio beneficiario cadastrado com sucesso"));
        }
    }

    public String alterar() {
        convenioBeneficiario = (ConvenioBeneficiario) selecionado.getRowData();
        return "/mainPages/convenioBeneficiario/formConvenioBeneficiario";
    }

    public void excluir() {
        try {
            convenioBeneficiario = (ConvenioBeneficiario) selecionado.getRowData();
            convenioBeneficiarioDAO.delete(convenioBeneficiario);
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Convênio beneficiario", "Este convênio beneficiario não pode ser excluído pois existem registros dependentes"));
        } finally {
            convenioBeneficiarios = null;
        }
    }

    public List<ConvenioBeneficiario> getConvenioBeneficiarios() {
        if (convenioBeneficiarios == null) {
            convenioBeneficiarios = convenioBeneficiarioDAO.getConvenioBeneficiarios();
        }
        return convenioBeneficiarios;
    }

    public List<ConvenioBeneficiario> getFiltroConvenioBeneficiarios(String descricao) {
        return convenioBeneficiarioDAO.getConvenioBeneficiarios(descricao);
    }

    public ConvenioBeneficiario getConvenioBeneficiario() {
        return convenioBeneficiario;
    }

    public void setConvenioBeneficiario(ConvenioBeneficiario convenioBeneficiario) {
        this.convenioBeneficiario = convenioBeneficiario;
    }

    public UIData getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(UIData selecionado) {
        this.selecionado = selecionado;
    }
}
