package edu.furb.easyboleto.persistence.dao;

import edu.furb.easyboleto.model.ConvenioBeneficiario;
import edu.furb.easyboleto.persistence.Transaction;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ConvenioBeneficiarioDAO {

    @Inject
    private EntityManager manager;

    @Transaction
    public void insert(ConvenioBeneficiario convenioBeneficiario) {
        manager.persist(convenioBeneficiario);
    }

    @Transaction
    public void update(ConvenioBeneficiario convenioBeneficiario) {
        manager.merge(convenioBeneficiario);
    }

    @Transaction
    public void delete(ConvenioBeneficiario convenioBeneficiario) throws Exception {
        ConvenioBeneficiario c = getConvenioBeneficiario(convenioBeneficiario.getNrSequencia());
        manager.remove(c);
    }

    public List<ConvenioBeneficiario> getConvenioBeneficiarios() {
        TypedQuery<ConvenioBeneficiario> query = manager.createQuery("select o from ConvenioBeneficiario as o order by o.beneficiario.pessoa.nome", ConvenioBeneficiario.class);
        return query.getResultList();
    }

    public List<ConvenioBeneficiario> getConvenioBeneficiarios(String descricao) {
        TypedQuery<ConvenioBeneficiario> query = manager.createQuery("select o from ConvenioBeneficiario o"
                + " join o.convenio c"
                + " where upper(c.descricao) like upper(:descricao)"
                + " order by c.descricao", ConvenioBeneficiario.class);
        query.setParameter("descricao", "%" + descricao + "%");
        return query.getResultList();
    }

    public ConvenioBeneficiario getConvenioBeneficiario(long nrSequencia) {
        return manager.find(ConvenioBeneficiario.class, nrSequencia);
    }
}
