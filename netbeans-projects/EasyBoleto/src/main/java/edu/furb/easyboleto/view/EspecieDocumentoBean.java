package edu.furb.easyboleto.view;

import edu.furb.easyboleto.model.EspecieDocumento;
import edu.furb.easyboleto.persistence.dao.EspecieDocumentoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EspecieDocumentoBean {

    @Inject
    private EspecieDocumento especieDocumento;
    @Inject
    private EspecieDocumentoDAO especieDocumentoDAO;
    private List<EspecieDocumento> especieDocumentos;
    private UIData selecionado;

    public void salvar() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (especieDocumentoDAO.existCodigo(especieDocumento)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Espécie Documento", "Este código já está cadastrado para outra espécie documento"));
                return;
            }
            if (especieDocumento.getNrSequencia() > 0l) {
                especieDocumentoDAO.update(especieDocumento);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Espécie Documento", "Espécie documento atualizado com sucesso"));
            } else {
                especieDocumentoDAO.insert(especieDocumento);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Espécie Documento", "Espécie documento cadastrado com sucesso"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String alterar() {
        especieDocumento = (EspecieDocumento) selecionado.getRowData();
        return "/mainPages/especieDocumento/formEspecieDocumento";
    }

    public void excluir() {
        try {
            especieDocumento = (EspecieDocumento) selecionado.getRowData();
            especieDocumentoDAO.delete(especieDocumento);
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Espécie Documento", "Este espécie documento não pode ser excluído pois existem registros dependentes"));
        } finally {
            especieDocumentos = null;
        }
    }

    public List<EspecieDocumento> getEspecieDocumentos() {
        try {
            if (especieDocumentos == null) {
                especieDocumentos = especieDocumentoDAO.getEspecieDocumentos();
            }
            return especieDocumentos;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<EspecieDocumento> getFiltroEspecieDocumentos(String descricao) {
        try {
            return especieDocumentoDAO.getEspecieDocumentos(descricao);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public EspecieDocumento getEspecieDocumento() {
        return especieDocumento;
    }

    public void setEspecieDocumento(EspecieDocumento especieDocumento) {
        this.especieDocumento = especieDocumento;
    }

    public UIData getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(UIData selecionado) {
        this.selecionado = selecionado;
    }
}
