package edu.furb.produtos.persistence;

import edu.furb.produtos.model.Produto;
import java.sql.Connection;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ProdutoDAO {

    @Inject
    private EntityManager manager;
    @Inject
    private Connection connection;

    public void inserir(Produto produto) {
        manager.persist(produto);
    }

    public void atualizar(Produto produto) {
        manager.merge(produto);
    }

    public void excluir(Produto produto) {
        Produto p = manager.find(Produto.class, produto.getId());
        manager.remove(p);
    }

    public List<Produto> listarProdutos() {
        Query q = manager.createQuery("select o from Produto as o", Produto.class);
        return q.getResultList();
    }

    public Connection getConnection() {
        return connection;
    }
}
