package edu.furb.easyboleto.persistence.dao;

import edu.furb.easyboleto.model.Beneficiario;
import edu.furb.easyboleto.persistence.Transaction;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class BeneficiarioDAO {

    @Inject
    private EntityManager manager;

    @Transaction
    public void insert(Beneficiario beneficiario) {
        manager.persist(beneficiario);
    }

    @Transaction
    public void update(Beneficiario beneficiario) {
        manager.merge(beneficiario);
    }

    @Transaction
    public void delete(Beneficiario beneficiario) throws Exception {
        Beneficiario b = getBeneficiario(beneficiario.getNrConta());
        manager.remove(b);
    }

    public List<Beneficiario> getBeneficiarios() {
        TypedQuery<Beneficiario> query = manager.createQuery("select o from Beneficiario as o order by o.pessoa.nome", Beneficiario.class);
        return query.getResultList();
    }

    public List<Beneficiario> getBeneficiarios(String nome) {
        TypedQuery<Beneficiario> query = manager.createQuery("select b from Beneficiario b"
                + " join b.pessoa p"
                + " where upper(p.nome) like upper(:nome)"
                + " order by p.nome", Beneficiario.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

    public boolean existCPF(Beneficiario beneficiario) {
        TypedQuery<Long> query = manager.createQuery("select count(b) from Beneficiario b"
                + " join b.pessoa p"
                + " where p.cpf = :cpf "
                + " and   p.nrSequencia <> :nrSequencia", Long.class);
        query.setParameter("cpf", beneficiario.getPessoa().getCpf());
        query.setParameter("nrSequencia", beneficiario.getPessoa().getNrSequencia());
        return query.getSingleResult().intValue() > 0;
    }

    public boolean existCNPJ(Beneficiario beneficiario) {
        TypedQuery<Long> query = manager.createQuery("select count(b) from Beneficiario b"
                + " join b.pessoa p"
                + " where p.cnpj = :cnpj "
                + " and   p.nrSequencia <> :nrSequencia", Long.class);
        query.setParameter("cnpj", beneficiario.getPessoa().getCnpj());
        query.setParameter("nrSequencia", beneficiario.getPessoa().getNrSequencia());
        return query.getSingleResult().intValue() > 0;
    }

    public Beneficiario getBeneficiario(long nrConta) {
        return manager.find(Beneficiario.class, nrConta);
    }
}
