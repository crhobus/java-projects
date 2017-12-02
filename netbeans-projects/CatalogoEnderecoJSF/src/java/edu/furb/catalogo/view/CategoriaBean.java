package edu.furb.catalogo.view;

import edu.furb.catalogo.model.Categoria;
import edu.furb.catalogo.persistence.CategoriaDAO;
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
public class CategoriaBean {

    @Inject
    private Categoria categoria;
    @Inject
    private CategoriaDAO categoriaDAO;

    private UIData selecionado;
    private boolean update = false;

    public List<Categoria> getCategorias() {
        return categoriaDAO.listarCategorias();
    }

    @Transaction
    public String salvar() {
        if (update) {
            categoriaDAO.atualizar(categoria);
        } else {
            categoriaDAO.inserir(categoria);
        }
        return "categoria?faces-redirect=true";
    }

    public String editar() {
        categoria = (Categoria) selecionado.getRowData();
        update = true;
        return "categoria";
    }

    @Transaction
    public String excluir() {
        categoria = (Categoria) selecionado.getRowData();
        categoriaDAO.excluir(categoria);
        return "categoria?faces-redirect=true";
    }

    public String limpar() {
        return "categoria?faces-redirect=true";
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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
