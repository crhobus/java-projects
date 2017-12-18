package edu.furb.easyboleto.persistence.dao;

import edu.furb.easyboleto.model.Convenio;
import edu.furb.easyboleto.persistence.Transaction;
import edu.furb.easyboleto.security.Seguranca;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ConvenioDAO {

    private Seguranca seguranca;

    @Inject
    private EntityManager manager;

    public ConvenioDAO() {
        seguranca = new Seguranca();
    }

    @Transaction
    public void insert(Convenio convenio) throws Exception {
        convenio.setDescricao(seguranca.encriptarCertificado(convenio.getDescricao()));
        convenio.getBanco().setNome(seguranca.encriptarSimetrico("key", convenio.getBanco().getNome()));
        manager.persist(convenio);
    }

    @Transaction
    public void update(Convenio convenio) throws Exception {
        convenio.setDescricao(seguranca.encriptarCertificado(convenio.getDescricao()));
        convenio.getBanco().setNome(seguranca.encriptarSimetrico("key", convenio.getBanco().getNome()));
        manager.merge(convenio);
    }

    @Transaction
    public void delete(Convenio convenio) throws Exception {
        Convenio c = getConvenio(convenio.getCdConvenio());
        manager.remove(c);
    }

    public List<Convenio> getConvenios() throws Exception {
        TypedQuery<Convenio> query = manager.createQuery("select o from Convenio as o order by o.descricao", Convenio.class);
        return getConvenioDecriptado(query.getResultList());
    }

    public List<Convenio> getConvenios(String descricao) throws Exception {
        TypedQuery<Convenio> query = manager.createQuery("select o from Convenio as o"
                + " where upper(o.descricao) like upper(:descricao)"
                + " order by o.descricao", Convenio.class);
        query.setParameter("descricao", "%" + descricao + "%");
        return getConvenioDecriptado(query.getResultList());
    }

    private List<Convenio> getConvenioDecriptado(List<Convenio> convenios) throws Exception {
        for (Convenio convenio : convenios) {
            convenio.setDescricao(seguranca.decriptarCertificado(convenio.getDescricao()));
//            convenio.getBanco().setNome(seguranca.decriptarSimetrico(convenio.getBanco().getNome()));
        }
        return convenios;
    }

    public Convenio getConvenio(long cdConvenio) throws Exception {
        Convenio convenio = manager.find(Convenio.class, cdConvenio);
        convenio.setDescricao(seguranca.decriptarCertificado(convenio.getDescricao()));
//        convenio.getBanco().setNome(seguranca.decriptarSimetrico(convenio.getBanco().getNome()));
        return convenio;
    }
}
