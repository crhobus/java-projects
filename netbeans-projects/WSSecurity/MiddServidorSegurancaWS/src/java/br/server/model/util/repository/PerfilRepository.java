package br.server.model.util.repository;

/*
 * Document   : PerfilRepository.java
 * Created on : 05/09/2013, 21:02:42
 * Author     : Caio
 */
import br.server.model.entities.Perfil;
import br.server.model.util.ConexaoUtil;
import java.util.List;
import javax.persistence.Query;

public class PerfilRepository {

    private ConexaoUtil con;

    public PerfilRepository(ConexaoUtil con) {
        this.con = con;
    }

    public void insertPerfil(Perfil perfil) {
        con.getEntityManager().persist(perfil);
    }

    public void updatePerfil(Perfil perfil) {
        con.getEntityManager().merge(perfil);
    }

    public Perfil getPerfil(long nrSequencia) {
        return con.getEntityManager().find(Perfil.class, nrSequencia);
    }

    public void deletePerfil(long nrSequencia) {
        Perfil perfil = getPerfil(nrSequencia);
        con.getEntityManager().remove(perfil);
    }

    public Perfil getPerfil(String nmPerfil) {
        Query query = con.getEntityManager().createQuery("select p "
                + "from  Perfil p "
                + "where upper(p.nmPerfil) = upper(:nmPerfil)");
        query.setParameter("nmPerfil", nmPerfil);
        List<Perfil> perfis = query.getResultList();
        if (!perfis.isEmpty()) {
            return perfis.get(0);
        }
        return null;
    }

    public Perfil getPerfil(long nrSequencia, String nmPerfil) {
        Query query = con.getEntityManager().createQuery("select p "
                + "from  Perfil p "
                + "where upper(p.nmPerfil) = upper(:nmPerfil) "
                + "and   p.nrSequencia <> :nrSequencia");
        query.setParameter("nmPerfil", nmPerfil);
        query.setParameter("nrSequencia", nrSequencia);
        List<Perfil> perfis = query.getResultList();
        if (!perfis.isEmpty()) {
            return perfis.get(0);
        }
        return null;
    }

    public List<Perfil> getPerfis() {
        Query query = con.getEntityManager().createQuery("select p "
                + "from Perfil as p "
                + "order by p.nmPerfil");
        return query.getResultList();
    }

    public List<Perfil> getPerfis(String nmPerfil) {
        Query query = con.getEntityManager().createQuery("select p "
                + "from Perfil as p "
                + "where upper(p.nmPerfil) like upper(:nmPerfil) "
                + "order by p.nmPerfil");
        query.setParameter("nmPerfil", "%" + nmPerfil + "%");
        return query.getResultList();
    }
}