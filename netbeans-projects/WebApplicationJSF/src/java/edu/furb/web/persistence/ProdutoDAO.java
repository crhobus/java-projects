package edu.furb.web.persistence;

import edu.furb.web.model.Produto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Caio
 */
public class ProdutoDAO {

    private static final List<Produto> produtos = new ArrayList<>();

    public void inserir(Produto produto) {
        produtos.add(produto);
    }

    public void alterar(Produto produto) {
        Produto p;
        int selecionado = -1;
        for (int i = 0; i < produtos.size(); i++) {
            p = produtos.get(i);
            if (p.getCodigo().equals(p.getCodigo())) {
                selecionado = i;
                break;
            }
        }
        if (selecionado != -1) {
            produtos.set(selecionado, produto);
        }
    }

    public void excluir(Produto produto) {
        produtos.remove(produto);
    }

    public List<Produto> listarTodos() {
        return produtos;
    }
}
