package edu.furb.easyboleto.view;

import edu.furb.easyboleto.model.Pagador;
import edu.furb.easyboleto.persistence.dao.PagadorDAO;
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
public class PagadorBean {

    @Inject
    private Pagador pagador;
    @Inject
    private PagadorDAO pagadorDAO;
    private List<Pagador> pagadores;
    private UIData selecionado;

    public void salvar() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            switch (pagador.getPessoa().getTipoPessoa()) {
                case "PF":
                    pagador.getPessoa().setCnpj("");
                    if ("".equals(pagador.getPessoa().getCpf())) {
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Pagador", "O CPF deve ser informado!"));
                        return;
                    }
                    break;
                case "PJ":
                    pagador.getPessoa().setCpf("");
                    if ("".equals(pagador.getPessoa().getCnpj())) {
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Pagador", "O CNPJ deve ser informado!"));
                        return;
                    }
                    break;
            }
            if (!"".equals(pagador.getPessoa().getEmail())
                    && !pagador.getPessoa().getEmail().contains("@")) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Pagador", "Informe um e-mail válido"));
                return;
            }
            if (pagador.getPessoa().getNumero() < 1) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Pagador", "O número deve ser maior que zero"));
                return;
            }
            if (!"".equals(pagador.getPessoa().getCpf())
                    && pagadorDAO.existCPF(pagador)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Pagador", "Este CPF já está cadastrado para outro pagador"));
                return;
            }
            if (!"".equals(pagador.getPessoa().getCnpj())
                    && pagadorDAO.existCNPJ(pagador)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Pagador", "Este CNPJ já está cadastrado para outro pagador"));
                return;
            }
            if (pagador.getNrSequencia() > 0l) {
                pagadorDAO.update(pagador);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pagador", "Pagador atualizado com sucesso"));
            } else {
                pagadorDAO.insert(pagador);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pagador", "Pagador cadastrado com sucesso"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String alterar() {
        pagador = (Pagador) selecionado.getRowData();
        return "/mainPages/pagador/formPagador";
    }

    public void excluir() {
        try {
            pagador = (Pagador) selecionado.getRowData();
            pagadorDAO.delete(pagador);
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Pagador", "Este pagador não pode ser excluído pois existem registros dependentes"));
        } finally {
            pagadores = null;
        }
    }

    public List<Pagador> getPagadores() {
        try {
            if (pagadores == null) {
                pagadores = pagadorDAO.getPagadores();
            }
            return pagadores;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Pagador> getFiltroPagadores(String nome) {
        try {
            return pagadorDAO.getPagadores(nome);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Pagador getPagador() {
        return pagador;
    }

    public void setPagador(Pagador pagador) {
        this.pagador = pagador;
    }

    public UIData getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(UIData selecionado) {
        this.selecionado = selecionado;
    }
}
