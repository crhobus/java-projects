package edu.furb.catalogo.persistence;

import edu.furb.catalogo.model.Categoria;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Caio
 */
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
        Categoria c = manager.find(Categoria.class, categoria.getCodigo());
        manager.remove(c);
    }

    public List<Categoria> listarCategorias() {
        Query q = manager.createQuery("select o from Categoria as o", Categoria.class);
        return q.getResultList();
    }

    public Categoria getCategoria(long codigo) {
        return manager.find(Categoria.class, codigo);
    }
}
