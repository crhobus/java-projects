package edu.furb.catalogo.view;

import edu.furb.catalogo.model.Contato;
import edu.furb.catalogo.persistence.ContatoDAO;
import edu.furb.catalogo.persistence.Transaction;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIData;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Caio
 */
@Named
@RequestScoped
public class ContatoBean {

    @Inject
    private Contato contato;
    @Inject
    private ContatoDAO contatoDAO;

    private UIData selecionado;
    private boolean update = false;

    public List<Contato> getContatos() {
        return contatoDAO.listarContatos();
    }

    @Transaction
    public String salvar() {
        if (update) {
            contatoDAO.atualizar(contato);
        } else {
            contatoDAO.inserir(contato);
        }
        return "contatoList?faces-redirect=true";
    }

    public String editar() {
        contato = (Contato) selecionado.getRowData();
        update = true;
        return "contatoForm";
    }

    @Transaction
    public String excluir() {
        contato = (Contato) selecionado.getRowData();
        contatoDAO.excluir(contato);
        return "contatoList?faces-redirect=true";
    }

    public String limpar() {
        return "contatoList?faces-redirect=true";
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public UIData getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(UIData selecionado) {
        this.selecionado = selecionado;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
}
