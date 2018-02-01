package edu.furb.web.view;

import edu.furb.web.model.Produto;
import edu.furb.web.persistence.ProdutoDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Caio
 */
@ManagedBean(name = "prodBean")
public class ProdutoBean {

    private Produto produtoItem = new Produto();
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public Produto getProdutoItem() {
        return produtoItem;
    }

    public void setProdutoItem(Produto produtoItem) {
        this.produtoItem = produtoItem;
    }

    public void salvar() {
        produtoDAO.inserir(produtoItem);
    }

    public List<Produto> getProdutos() {
        return produtoDAO.listarTodos();
    }
}
