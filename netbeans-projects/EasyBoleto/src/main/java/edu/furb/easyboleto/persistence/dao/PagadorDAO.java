package edu.furb.easyboleto.persistence.dao;

import edu.furb.easyboleto.model.Pagador;
import edu.furb.easyboleto.persistence.Transaction;
import edu.furb.easyboleto.security.Seguranca;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class PagadorDAO {

    private Seguranca seguranca;

    @Inject
    private EntityManager manager;

    public PagadorDAO() {
        seguranca = new Seguranca();
    }

    @Transaction
    public void insert(Pagador pagador) throws Exception {
//        pagador.getPessoa().setNome(seguranca.encriptarCertificado(pagador.getPessoa().getNome()));
        pagador.getBanco().setNome(seguranca.encriptarSimetrico("key", pagador.getBanco().getNome()));
        manager.persist(pagador);
    }

    @Transaction
    public void update(Pagador pagador) throws Exception {
//        pagador.getPessoa().setNome(seguranca.encriptarCertificado(pagador.getPessoa().getNome()));
        pagador.getBanco().setNome(seguranca.encriptarSimetrico("key", pagador.getBanco().getNome()));
        manager.merge(pagador);
    }

    @Transaction
    public void delete(Pagador pagador) throws Exception {
        Pagador p = getPagador(pagador.getNrSequencia());
        manager.remove(p);
    }

    public List<Pagador> getPagadores() throws Exception {
        TypedQuery<Pagador> query = manager.createQuery("select o from Pagador as o order by o.pessoa.nome", Pagador.class);
        return getPagadorDecriptado(query.getResultList());
    }

    public List<Pagador> getPagadores(String nome) throws Exception {
        TypedQuery<Pagador> query = manager.createQuery("select o from Pagador o"
                + " join o.pessoa p"
                + " where upper(p.nome) like upper(:nome)"
                + " order by p.nome", Pagador.class);
        query.setParameter("nome", "%" + nome + "%");
        return getPagadorDecriptado(query.getResultList());
    }

    private List<Pagador> getPagadorDecriptado(List<Pagador> pagadores) throws Exception {
        for (Pagador pagador : pagadores) {
//            pagador.getPessoa().setNome(seguranca.decriptarCertificado(pagador.getPessoa().getNome()));
            pagador.getBanco().setNome(seguranca.encriptarSimetrico("key", pagador.getBanco().getNome()));
        }
        return pagadores;
    }

    public boolean existCPF(Pagador pagador) {
        TypedQuery<Long> query = manager.createQuery("select count(o) from Pagador o"
                + " join o.pessoa p"
                + " where p.cpf = :cpf "
                + " and   p.nrSequencia <> :nrSequencia", Long.class);
        query.setParameter("cpf", pagador.getPessoa().getCpf());
        query.setParameter("nrSequencia", pagador.getPessoa().getNrSequencia());
        return query.getSingleResult().intValue() > 0;
    }

    public boolean existCNPJ(Pagador pagador) {
        TypedQuery<Long> query = manager.createQuery("select count(o) from Pagador o"
                + " join o.pessoa p"
                + " where p.cnpj = :cnpj "
                + " and   p.nrSequencia <> :nrSequencia", Long.class);
        query.setParameter("cnpj", pagador.getPessoa().getCnpj());
        query.setParameter("nrSequencia", pagador.getPessoa().getNrSequencia());
        return query.getSingleResult().intValue() > 0;
    }

    public Pagador getPagador(long nrSequencia) throws Exception {
        Pagador pagador = manager.find(Pagador.class, nrSequencia);
//        pagador.getPessoa().setNome(seguranca.decriptarCertificado(pagador.getPessoa().getNome()));
        pagador.getBanco().setNome(seguranca.encriptarSimetrico("key", pagador.getBanco().getNome()));
        return pagador;
    }
}
