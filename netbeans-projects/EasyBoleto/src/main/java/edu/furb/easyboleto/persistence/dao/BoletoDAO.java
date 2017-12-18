package edu.furb.easyboleto.persistence.dao;

import edu.furb.easyboleto.model.Boleto;
import edu.furb.easyboleto.persistence.Transaction;
import java.sql.Connection;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class BoletoDAO {

    @Inject
    private EntityManager manager;
    @Inject
    private Connection connection;

    @Transaction
    public void insert(Boleto boleto) {
        manager.persist(boleto);
    }

    @Transaction
    public void update(Boleto boleto) {
        manager.merge(boleto);
    }

    @Transaction
    public void delete(Boleto boleto) throws Exception {
        Boleto b = manager.find(Boleto.class, boleto.getNrBoleto());
        manager.remove(b);
    }

    public List<Boleto> getBoletos() {
        TypedQuery<Boleto> query = manager.createQuery("select o from Boleto as o order by o.pagador.pessoa.nome", Boleto.class);
        return query.getResultList();
    }

    public Connection getConnection() {
        return connection;
    }
}
