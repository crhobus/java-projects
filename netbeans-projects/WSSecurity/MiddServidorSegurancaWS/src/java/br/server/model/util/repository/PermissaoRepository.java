package br.server.model.util.repository;

/*
 * Document   : PermissaoRepository.java
 * Created on : 26/08/2013, 21:10:38
 * Author     : Caio
 */
import br.server.model.entities.Permissao;
import br.server.model.util.ConexaoUtil;
import java.util.List;
import javax.persistence.Query;

public class PermissaoRepository {

    private ConexaoUtil con;

    public PermissaoRepository(ConexaoUtil con) {
        this.con = con;
    }

    public void insertPermissao(Permissao permissao) {
        con.getEntityManager().persist(permissao);
    }

    public void updatePermissao(Permissao permissao) {
        con.getEntityManager().merge(permissao);
    }

    public Permissao getPermissao(long nrSequencia) {
        return con.getEntityManager().find(Permissao.class, nrSequencia);
    }

    public void deletePermissao(long nrSequencia) throws Exception {
        Query query = con.getEntityManager().createQuery("select count(rp) "
                + "from  RecursoPermissao rp "
                + "join  rp.permissao p "
                + "where p.nrSequencia = :nrSequencia");
        query.setParameter("nrSequencia", nrSequencia);
        Long qtRecursoPermissoes = (Long) query.getSingleResult();
        if (qtRecursoPermissoes > 0) {
            throw new Exception("Esta permissão não pode ser excluída pois existem registros dependentes");
        }
        Permissao permissao = getPermissao(nrSequencia);
        con.getEntityManager().remove(permissao);
    }

    public Permissao getPermissao(String nmPermissao, String cdPermissaoComunic) {
        Query query;
        List<Permissao> permissoes;
        if (cdPermissaoComunic != null) {
            query = con.getEntityManager().createQuery("select p "
                    + "from  Permissao p "
                    + "where p.cdPermissaoComunic = :cdPermissaoComunic");
            query.setParameter("cdPermissaoComunic", cdPermissaoComunic);
            permissoes = query.getResultList();
            if (!permissoes.isEmpty()) {
                return permissoes.get(0);
            }
        } else {
            query = con.getEntityManager().createQuery("select p "
                    + "from  Permissao p "
                    + "where upper(p.nmPermissao) = upper(:nmPermissao)");
            query.setParameter("nmPermissao", nmPermissao);
            permissoes = query.getResultList();
            if (!permissoes.isEmpty()) {
                return permissoes.get(0);
            }
        }
        return null;
    }

    public Permissao getPermissao(long nrSequencia, String nmPermissao, String cdPermissaoComunic) {
        Query query;
        List<Permissao> permissoes;
        if (cdPermissaoComunic != null) {
            query = con.getEntityManager().createQuery("select p "
                    + "from  Permissao p "
                    + "where p.cdPermissaoComunic = :cdPermissaoComunic "
                    + "and   p.nrSequencia <> :nrSequencia");
            query.setParameter("cdPermissaoComunic", cdPermissaoComunic);
            query.setParameter("nrSequencia", nrSequencia);
            permissoes = query.getResultList();
            if (!permissoes.isEmpty()) {
                return permissoes.get(0);
            }
        } else {
            query = con.getEntityManager().createQuery("select p "
                    + "from  Permissao p "
                    + "where upper(p.nmPermissao) = upper(:nmPermissao) "
                    + "and   p.nrSequencia <> :nrSequencia");
            query.setParameter("nmPermissao", nmPermissao);
            query.setParameter("nrSequencia", nrSequencia);
            permissoes = query.getResultList();
            if (!permissoes.isEmpty()) {
                return permissoes.get(0);
            }
        }
        return null;
    }

    public List<Permissao> getPermissoes() {
        Query query = con.getEntityManager().createQuery("select p "
                + "from Permissao as p "
                + "order by p.nmPermissao");
        return query.getResultList();
    }

    public List<Permissao> getPermissoes(String nmPermissao) {
        Query query = con.getEntityManager().createQuery("select p "
                + "from Permissao as p "
                + "where upper(p.nmPermissao) like upper(:nmPermissao) "
                + "order by p.nmPermissao");
        query.setParameter("nmPermissao", "%" + nmPermissao + "%");
        return query.getResultList();
    }
}