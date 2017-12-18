package edu.furb.easyboleto.persistence.dao;

import edu.furb.easyboleto.model.Banco;
import edu.furb.easyboleto.persistence.Transaction;
import edu.furb.easyboleto.security.Seguranca;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class BancoDAO {

    private Seguranca seguranca;

    @Inject
    private EntityManager manager;

    public BancoDAO() {
        seguranca = new Seguranca();
    }

    @Transaction
    public void insert(Banco banco) throws Exception {
        banco.setNome(seguranca.encriptarSimetrico("key", banco.getNome()));
        manager.persist(banco);
    }

    @Transaction
    public void update(Banco banco) throws Exception {
        banco.setNome(seguranca.encriptarSimetrico("key", banco.getNome()));
        manager.merge(banco);
    }

    @Transaction
    public void delete(Banco banco) throws Exception {
        Banco b = getBanco(banco.getCdBanco());
        manager.remove(b);
    }

    public List<Banco> getBancos() throws Exception {
        TypedQuery<Banco> query = manager.createQuery("select o from Banco as o order by o.nome", Banco.class);
        return getBancoDecriptado(query.getResultList());
    }

    public List<Banco> getBancos(String nome) throws Exception {
        TypedQuery<Banco> query = manager.createQuery("select o from Banco as o"
                + " where upper(o.nome) like upper(:nome)"
                + " order by o.nome", Banco.class);
        query.setParameter("nome", "%" + nome + "%");
        return getBancoDecriptado(query.getResultList());
    }

    private List<Banco> getBancoDecriptado(List<Banco> bancos) throws Exception {
        for (Banco banco : bancos) {
            banco.setNome(seguranca.decriptarSimetrico(banco.getNome()));
        }
        return bancos;
    }

    public boolean existID(Banco banco) {
        TypedQuery<Long> query = manager.createQuery("select count(o) from Banco as o"
                + " where o.id = :id "
                + " and   o.cdBanco <> :cdBanco", Long.class);
        query.setParameter("id", banco.getId());
        query.setParameter("cdBanco", banco.getCdBanco());
        return query.getSingleResult().intValue() > 0;
    }

    public Banco getBanco(long cdBanco) throws Exception {
        Banco banco = manager.find(Banco.class, cdBanco);
        banco.setNome(seguranca.decriptarSimetrico(banco.getNome()));
        return banco;
    }
}
