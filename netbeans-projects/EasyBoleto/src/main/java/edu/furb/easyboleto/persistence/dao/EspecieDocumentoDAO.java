package edu.furb.easyboleto.persistence.dao;

import edu.furb.easyboleto.model.EspecieDocumento;
import edu.furb.easyboleto.persistence.Transaction;
import edu.furb.easyboleto.security.Seguranca;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class EspecieDocumentoDAO {

    private Seguranca seguranca;

    @Inject
    private EntityManager manager;
    
    public EspecieDocumentoDAO(){
        seguranca = new Seguranca();
    }

    @Transaction
    public void insert(EspecieDocumento especieDocumento) throws Exception {
        especieDocumento.setAssinatura(seguranca.assinarCampo(especieDocumento.getDescricao()));
        manager.persist(especieDocumento);
    }

    @Transaction
    public void update(EspecieDocumento especieDocumento) throws Exception {
        especieDocumento.setAssinatura(seguranca.assinarCampo(especieDocumento.getDescricao()));
        manager.merge(especieDocumento);
    }

    @Transaction
    public void delete(EspecieDocumento especieDocumento) throws Exception {
        EspecieDocumento ed = getEspecieDocumento(especieDocumento.getNrSequencia());
        manager.remove(ed);
    }

    public List<EspecieDocumento> getEspecieDocumentos() throws Exception {
        TypedQuery<EspecieDocumento> query = manager.createQuery("select o from EspecieDocumento as o order by o.descricao", EspecieDocumento.class);
        return getEspecieDocumentos(query.getResultList());
    }

    public List<EspecieDocumento> getEspecieDocumentos(String descricao) throws Exception {
        TypedQuery<EspecieDocumento> query = manager.createQuery("select o from EspecieDocumento as o"
                + " where upper(o.descricao) like upper(:descricao)"
                + " order by o.descricao", EspecieDocumento.class);
        query.setParameter("descricao", "%" + descricao + "%");
        return getEspecieDocumentos(query.getResultList());
    }

    private List<EspecieDocumento> getEspecieDocumentos(List<EspecieDocumento> especieDocumentos) throws Exception {
        for (EspecieDocumento especieDocumento : especieDocumentos) {
            if (!seguranca.verificaAssinatura(especieDocumento.getDescricao(), especieDocumento.getAssinatura())) {
                especieDocumento.setDescricao("Registro inválido!");
            }
        }
        return especieDocumentos;
    }

    public boolean existCodigo(EspecieDocumento especieDocumento) {
        TypedQuery<Long> query = manager.createQuery("select count(o) from EspecieDocumento as o"
                + " where o.codigo = :codigo "
                + " and   o.nrSequencia <> :nrSequencia", Long.class);
        query.setParameter("codigo", especieDocumento.getCodigo());
        query.setParameter("nrSequencia", especieDocumento.getNrSequencia());
        return query.getSingleResult().intValue() > 0;
    }

    public EspecieDocumento getEspecieDocumento(long nrSequencia) throws Exception {
        EspecieDocumento especieDocumento = manager.find(EspecieDocumento.class, nrSequencia);
        if (!seguranca.verificaAssinatura(especieDocumento.getDescricao(), especieDocumento.getAssinatura())) {
            especieDocumento.setDescricao("Registro inválido!");
        }
        return especieDocumento;
    }
}
