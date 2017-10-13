package edu.furb.produtos.persistence;

import edu.furb.produtos.model.Categoria;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CategoriaDAO {

    @Inject
    private EntityManager manager;

    public void inserir(Categoria categoria) {
        manager.persist(categoria);
    }

    public void atualizar(Categoria categoria) {
        manager.merge(categoria);
    }

    public void excluir(Categoria categoria) {
        Categoria c = manager.find(Categoria.class, categoria.getId());
        manager.remove(c);
    }

    public List<Categoria> listarCategorias() {
        Query q = manager.createQuery("select o from Categoria as o", Categoria.class);
        return q.getResultList();
    }

    public Categoria getCategoria(long id) {
        return manager.find(Categoria.class, id);
    }
}
