package edu.furb.easyboleto.view;

import edu.furb.easyboleto.model.Beneficiario;
import edu.furb.easyboleto.persistence.dao.BeneficiarioDAO;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class BeneficiarioBean {

    @Inject
    private Beneficiario beneficiario;
    @Inject
    private BeneficiarioDAO beneficiarioDAO;
    private List<Beneficiario> beneficiarios;
    private UIData selecionado;

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (beneficiario.getAgencia() < 1) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Beneficiário", "A agência deve ser maior que zero"));
            return;
        }
        switch (beneficiario.getPessoa().getTipoPessoa()) {
            case "PF":
                beneficiario.getPessoa().setCnpj("");
                if ("".equals(beneficiario.getPessoa().getCpf())) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Beneficiário", "O CPF deve ser informado!"));
                    return;
                }
                break;
            case "PJ":
                beneficiario.getPessoa().setCpf("");
                if ("".equals(beneficiario.getPessoa().getCnpj())) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Beneficiário", "O CNPJ deve ser informado!"));
                    return;
                }
                break;
        }
        if (!"".equals(beneficiario.getPessoa().getEmail())
                && !beneficiario.getPessoa().getEmail().contains("@")) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Beneficiário", "Informe um e-mail válido"));
            return;
        }
        if (beneficiario.getPessoa().getNumero() < 1) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Beneficiário", "O número deve ser maior que zero"));
            return;
        }
        if (!"".equals(beneficiario.getPessoa().getCpf())
                && beneficiarioDAO.existCPF(beneficiario)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Beneficiário", "Este CPF já está cadastrado para outro beneficiário"));
            return;
        }
        if (!"".equals(beneficiario.getPessoa().getCnpj())
                && beneficiarioDAO.existCNPJ(beneficiario)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Beneficiário", "Este CNPJ já está cadastrado para outro beneficiário"));
            return;
        }
        if (beneficiario.getNrConta() > 0l) {
            beneficiarioDAO.update(beneficiario);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Beneficiário", "Beneficiário atualizado com sucesso"));
        } else {
            beneficiarioDAO.insert(beneficiario);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Beneficiário", "Beneficiário cadastrado com sucesso"));
        }
    }

    public String alterar() {
        beneficiario = (Beneficiario) selecionado.getRowData();
        return "/mainPages/beneficiario/formBeneficiario";
    }

    public void excluir() {
        try {
            beneficiario = (Beneficiario) selecionado.getRowData();
            beneficiarioDAO.delete(beneficiario);
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Beneficiário", "Este beneficiário não pode ser excluído pois existem registros dependentes"));
        } finally {
            beneficiarios = null;
        }
    }

    public List<Beneficiario> getBeneficiarios() {
        if (beneficiarios == null) {
            beneficiarios = beneficiarioDAO.getBeneficiarios();
        }
        return beneficiarios;
    }

    public List<Beneficiario> getFiltroBeneficiarios(String nome) {
        return beneficiarioDAO.getBeneficiarios(nome);
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public UIData getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(UIData selecionado) {
        this.selecionado = selecionado;
    }
}
