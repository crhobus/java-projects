package br.server.model.util.repository;

/*
 * Document   : SistemaRepository.java
 * Created on : 22/08/2013, 19:50:26
 * Author     : Caio
 */
import br.server.model.entities.Sistema;
import br.server.model.util.ConexaoUtil;
import java.util.List;
import javax.persistence.Query;

public class SistemaRepository {

    private ConexaoUtil con;

    public SistemaRepository(ConexaoUtil con) {
        this.con = con;
    }

    public void insertSistema(Sistema sistema) {
        con.getEntityManager().persist(sistema);
    }

    public void updateSistema(Sistema sistema) {
        con.getEntityManager().merge(sistema);
    }

    public Sistema getSistema(long nrSequencia) {
        return con.getEntityManager().find(Sistema.class, nrSequencia);
    }

    public void deleteSistema(long nrSequencia) throws Exception {
        Query query = con.getEntityManager().createQuery("select count(r) "
                + "from  Regra r "
                + "join  r.sistema m "
                + "where m.nrSequencia = :nrSequencia");
        query.setParameter("nrSequencia", nrSequencia);
        Long qtRegras = (Long) query.getSingleResult();
        if (qtRegras > 0) {
            throw new Exception("Este sitema não pode ser excluído pois existem registros dependentes");
        }
        Sistema sistema = getSistema(nrSequencia);
        con.getEntityManager().remove(sistema);
    }

    public Sistema getSistema(String nmSistema) {
        Query query = con.getEntityManager().createQuery("select m "
                + "from  Sistema m "
                + "where upper(m.nmSistema) = upper(:nmSistema)");
        query.setParameter("nmSistema", nmSistema);
        List<Sistema> sistemas = query.getResultList();
        if (!sistemas.isEmpty()) {
            return sistemas.get(0);
        }
        return null;
    }

    public Sistema getSistema(long nrSequencia, String nmSistema) {
        Query query = con.getEntityManager().createQuery("select m "
                + "from  Sistema m "
                + "where upper(m.nmSistema) = upper(:nmSistema) "
                + "and   m.nrSequencia <> :nrSequencia");
        query.setParameter("nmSistema", nmSistema);
        query.setParameter("nrSequencia", nrSequencia);
        List<Sistema> sistemas = query.getResultList();
        if (!sistemas.isEmpty()) {
            return sistemas.get(0);
        }
        return null;
    }

    public List<Sistema> getSistemas() {
        Query query = con.getEntityManager().createQuery("select m "
                + "from Sistema as m "
                + "order by m.nmSistema");
        return query.getResultList();
    }

    public List<Sistema> getSistemas(String nmSistema) {
        Query query = con.getEntityManager().createQuery("select m "
                + "from Sistema as m "
                + "where upper(m.nmSistema) like upper(:nmSistema) "
                + "order by m.nmSistema");
        query.setParameter("nmSistema", "%" + nmSistema + "%");
        return query.getResultList();
    }
}
