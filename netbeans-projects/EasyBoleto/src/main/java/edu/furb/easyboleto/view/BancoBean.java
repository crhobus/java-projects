package edu.furb.easyboleto.view;

import edu.furb.easyboleto.model.Banco;
import edu.furb.easyboleto.persistence.dao.BancoDAO;
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
public class BancoBean {

    @Inject
    private Banco banco;
    @Inject
    private BancoDAO bancoDAO;
    private List<Banco> bancos;
    private UIData selecionado;

    public void salvar() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (banco.getId() < 1) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Banco", "O código do banco deve ser maior que zero"));
                return;
            }
            if (bancoDAO.existID(banco)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Banco", "Este ID já está cadastrado para outro banco"));
                return;
            }
            if (banco.getCdBanco() > 0l) {
                bancoDAO.update(banco);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Banco", "Banco atualizado com sucesso"));
            } else {
                bancoDAO.insert(banco);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Banco", "Banco cadastrado com sucesso"));
            }
            banco.setNome(new Seguranca().decriptarSimetrico(banco.getNome()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String alterar() {
        banco = (Banco) selecionado.getRowData();
        return "/mainPages/banco/formBanco";
    }

    public void excluir() {
        try {
            banco = (Banco) selecionado.getRowData();
            bancoDAO.delete(banco);
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Banco", "Este banco não pode ser excluído pois existem registros dependentes"));
        } finally {
            bancos = null;
        }
    }

    public List<Banco> getBancos() {
        try {
            if (bancos == null) {
                bancos = bancoDAO.getBancos();
            }
            return bancos;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Banco> getFiltroBancos(String nome) {
        try {
            return bancoDAO.getBancos(nome);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public UIData getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(UIData selecionado) {
        this.selecionado = selecionado;
    }
}
