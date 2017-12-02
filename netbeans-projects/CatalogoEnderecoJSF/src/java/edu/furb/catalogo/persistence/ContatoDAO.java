package edu.furb.catalogo.persistence;

import edu.furb.catalogo.model.Contato;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Caio
 */
public class ContatoDAO {

    @Inject
    private EntityManager manager;

    public void inserir(Contato contato) {
        manager.persist(contato);
    }

    public void atualizar(Contato contato) {
        manager.merge(contato);
    }

    public void excluir(Contato contato) {
        Contato c = manager.find(Contato.class, contato.getCodigo());
        manager.remove(c);
    }

    public List<Contato> listarContatos() {
        Query q = manager.createQuery("select o from Contato as o", Contato.class);
        return q.getResultList();
    }
}
