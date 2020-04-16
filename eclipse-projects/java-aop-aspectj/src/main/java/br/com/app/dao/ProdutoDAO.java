package br.com.app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.app.dto.Produto;

public class ProdutoDAO {

    private ProdutoDAO() {}

    private static final List<Produto> produtos = new ArrayList<>();

    public static void save(Produto produto) {
        Produto p = getProduto(produto.getCodigo());
        if (p != null) {
            p.setNome(produto.getNome());
            p.setPreco(produto.getPreco());
        } else {
            produtos.add(produto);
        }
    }

    public static List<Produto> list() {
        return produtos;
    }

    public static Produto getProduto(int codigo) {
        Optional<Produto> produtoOpt = produtos.stream().filter(p -> p.getCodigo() == codigo).findFirst();
        if (produtoOpt.isPresent()) {
            return produtoOpt.get();
        }
        return null;
    }
}
