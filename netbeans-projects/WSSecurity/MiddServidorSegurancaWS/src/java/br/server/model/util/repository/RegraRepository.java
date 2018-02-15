package br.server.model.util.repository;

/*
 * Document   : RegraRepository.java
 * Created on : 28/08/2013, 19:50:28
 * Author     : Caio
 */
import br.server.model.entities.Regra;
import br.server.model.util.ConexaoUtil;
import java.util.List;
import javax.persistence.Query;

public class RegraRepository {

    private ConexaoUtil con;

    public RegraRepository(ConexaoUtil con) {
        this.con = con;
    }

    public void insertRegra(Regra regra) {
        con.getEntityManager().persist(regra);
    }

    public void updateRegra(Regra regra) {
        con.getEntityManager().merge(regra);
    }

    public Regra getRegra(long nrSequencia) {
        return con.getEntityManager().find(Regra.class, nrSequencia);
    }

    public void deleteRegra(long nrSequencia) throws Exception {
        Query query = con.getEntityManager().createQuery("select count(rec) "
                + "from  Recurso rec "
                + "join  rec.regra r "
                + "where r.nrSequencia = :nrSequencia");
        query.setParameter("nrSequencia", nrSequencia);
        Long qtRecursos = (Long) query.getSingleResult();
        if (qtRecursos > 0) {
            throw new Exception("Esta regra não pode ser excluída pois existem registros dependentes");
        }
        Regra regra = getRegra(nrSequencia);
        con.getEntityManager().remove(regra);
    }

    public Regra getRegra(String nmRegra, String cdRegraComunic) {
        Query query;
        List<Regra> regras;
        if (cdRegraComunic != null) {
            query = con.getEntityManager().createQuery("select r "
                    + "from  Regra r "
                    + "where r.cdRegraComunic = :cdRegraComunic");
            query.setParameter("cdRegraComunic", cdRegraComunic);
            regras = query.getResultList();
            if (!regras.isEmpty()) {
                return regras.get(0);
            }
        } else {
            query = con.getEntityManager().createQuery("select r "
                    + "from  Regra r "
                    + "where upper(r.nmRegra) = upper(:nmRegra)");
            query.setParameter("nmRegra", nmRegra);
            regras = query.getResultList();
            if (!regras.isEmpty()) {
                return regras.get(0);
            }
        }
        return null;
    }

    public Regra getRegra(long nrSequencia, String nmRegra, String cdRegraComunic) {
        Query query;
        List<Regra> regras;
        if (cdRegraComunic != null) {
            query = con.getEntityManager().createQuery("select r "
                    + "from  Regra r "
                    + "where r.cdRegraComunic = :cdRegraComunic "
                    + "and   r.nrSequencia <> :nrSequencia");
            query.setParameter("cdRegraComunic", cdRegraComunic);
            query.setParameter("nrSequencia", nrSequencia);
            regras = query.getResultList();
            if (!regras.isEmpty()) {
                return regras.get(0);
            }
        } else {
            query = con.getEntityManager().createQuery("select r "
                    + "from  Regra r "
                    + "where upper(r.nmRegra) = upper(:nmRegra) "
                    + "and   r.nrSequencia <> :nrSequencia");
            query.setParameter("nmRegra", nmRegra);
            query.setParameter("nrSequencia", nrSequencia);
            regras = query.getResultList();
            if (!regras.isEmpty()) {
                return regras.get(0);
            }
        }
        return null;
    }

    public List<Regra> getRegras(boolean consRegra) {
        Query query = con.getEntityManager().createQuery("select r "
                + "from Regra as r "
                + (consRegra ? "order by r.sistema.nmSistema, r.nmRegra " : "order by r.nmRegra "));

        return query.getResultList();
    }

    public List<Regra> getRegras(String nmRegra, boolean consRegra) {
        Query query = con.getEntityManager().createQuery("select r "
                + "from Regra as r "
                + "where upper(r.nmRegra) like upper(:nmRegra) "
                + (consRegra ? "order by r.sistema.nmSistema, r.nmRegra " : "order by r.nmRegra "));
        query.setParameter("nmRegra", "%" + nmRegra + "%");
        return query.getResultList();
    }
}