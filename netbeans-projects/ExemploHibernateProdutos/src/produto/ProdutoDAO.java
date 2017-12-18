package produto;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProdutoDAO {

    private Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    public void close() {
        HibernateUtil.getSessionFactory().close();
    }

    public void salva(Produto p) throws Exception {
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(p);
            session.flush();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception(ex);
        } finally {
            session.close();
        }
    }

    public void remove(Produto p) throws Exception {
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(p);
            session.flush();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception(ex);
        } finally {
            session.close();
        }
    }

    public Produto procura(int codigo) {
        Session session = getSession();
        try {
            return (Produto) session.get(Produto.class, codigo);
        } finally {
            session.close();
        }
    }

    public void atualiza(Produto p) throws Exception {
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(p);
            session.flush();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception(ex);
        } finally {
            session.close();
        }
    }

    public List<Produto> listaProdutosComClausulaWhere() {
        Session session = getSession();
        try {
            return session.createQuery("from produto.Produto where codigo <= 20").list();
        } finally {
            session.close();
        }
    }

    public List<Produto> listaProdutos() {
        Session session = getSession();
        try {
            return session.createCriteria(Produto.class).list();
        } finally {
            session.close();
        }
    }

//    utiliza paginação
    public List<Produto> listaProdutosPaginacao(int inicio, int quantia) {
        Session session = getSession();
        try {
            return session.createCriteria(Produto.class).setMaxResults(quantia).setFirstResult(inicio).list();
        } finally {
            session.close();
        }
    }
}
