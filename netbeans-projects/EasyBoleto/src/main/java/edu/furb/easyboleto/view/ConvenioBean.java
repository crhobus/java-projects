package edu.furb.easyboleto.view;

import edu.furb.easyboleto.model.Convenio;
import edu.furb.easyboleto.persistence.dao.ConvenioDAO;
import edu.furb.easyboleto.security.Seguranca;
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
public class ConvenioBean {

    @Inject
    private Convenio convenio;
    @Inject
    private ConvenioDAO convenioDAO;
    private List<Convenio> convenios;
    private UIData selecionado;

    public void salvar() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (convenio.getCarteira() < 1) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Convênio", "A carteira do convênio deve ser maior que zero"));
                return;
            }
            if (convenio.getCdConvenio() > 0l) {
                convenioDAO.update(convenio);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Convênio", "Convênio atualizado com sucesso"));
            } else {
                convenioDAO.insert(convenio);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Convênio", "Convênio cadastrado com sucesso"));
            }
            convenio.setDescricao(new Seguranca().decriptarCertificado(convenio.getDescricao()));
//            convenio.getBanco().setNome(new Seguranca().decriptarSimetrico(convenio.getBanco().getNome()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String alterar() {
        convenio = (Convenio) selecionado.getRowData();
        return "/mainPages/convenio/formConvenio";
    }

    public void excluir() {
        try {
            convenio = (Convenio) selecionado.getRowData();
            convenioDAO.delete(convenio);
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Convênio", "Este convênio não pode ser excluído pois existem registros dependentes"));
        } finally {
            convenios = null;
        }
    }

    public List<Convenio> getConvenios() {
        try {
            if (convenios == null) {
                convenios = convenioDAO.getConvenios();
            }
            return convenios;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Convenio> getFiltroConvenios(String descricao) {
        try {
            return convenioDAO.getConvenios(descricao);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public UIData getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(UIData selecionado) {
        this.selecionado = selecionado;
    }
}
