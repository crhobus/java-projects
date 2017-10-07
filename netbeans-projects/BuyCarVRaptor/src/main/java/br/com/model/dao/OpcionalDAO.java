package br.com.model.dao;

import br.com.model.beans.Opcional;
import br.com.model.dao.util.DAO;
import br.com.model.dao.util.DAOAdapter;
import br.com.model.dao.util.Operacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OpcionalDAO extends DAOAdapter<Opcional> {

    private final DAO dao = new DAO();

    @Override
    public void init() throws SQLException, ClassNotFoundException {
        dao.conectar();
    }

    @Override
    public Opcional insert(Opcional opcional) throws SQLException {
        opcional.setNrSequencia(dao.getSequence("opcional_seq.nextval"));
        PreparedStatement ps = null;
        try {
            StringBuilder insert = new StringBuilder();
            insert.append(" insert into opcional ( ");
            insert.append("                      nr_sequencia, ");
            insert.append("                      nome, ");
            insert.append("                      descricao, ");
            insert.append("                      valor ");
            insert.append("             ) values ( ");
            insert.append("                      :nr_sequencia, ");
            insert.append("                      :nome, ");
            insert.append("                      :descricao, ");
            insert.append("                      :valor) ");

            ps = dao.getPreparedStatement(insert.toString());

            ps.setLong(1, opcional.getNrSequencia());
            ps.setString(2, opcional.getNome());
            ps.setString(3, opcional.getDescricao());
            ps.setBigDecimal(4, opcional.getValor());

            ps.executeUpdate();

            return opcional;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public Opcional update(Opcional opcional) throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder update = new StringBuilder();
            update.append(" update opcional ");
            update.append(" set    nome         = :nome, ");
            update.append("        descricao    = :descricao, ");
            update.append("        valor        = :valor ");
            update.append(" where  nr_sequencia = :nr_sequencia ");

            ps = dao.getPreparedStatement(update.toString());

            ps.setString(1, opcional.getNome());
            ps.setString(2, opcional.getDescricao());
            ps.setBigDecimal(3, opcional.getValor());
            ps.setLong(4, opcional.getNrSequencia());

            ps.executeUpdate();

            return opcional;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public void delete(long nrSequencia) throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder delete = new StringBuilder();
            delete.append(" delete ");
            delete.append(" from    opcional ");
            delete.append(" where   nr_sequencia = :nr_sequencia ");

            ps = dao.getPreparedStatement(delete.toString());

            ps.setLong(1, nrSequencia);

            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public Opcional get(long nrSequencia) throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder select = new StringBuilder();
            select.append(" select  * ");
            select.append(" from    opcional ");
            select.append(" where   nr_sequencia = :nr_sequencia ");

            ps = dao.getPreparedStatement(select.toString());
            ps.setLong(1, nrSequencia);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Opcional opcional = new Opcional();
                opcional.setNrSequencia(rs.getLong("NR_SEQUENCIA"));
                opcional.setNome(rs.getString("NOME"));
                opcional.setDescricao(rs.getString("DESCRICAO"));
                opcional.setValor(rs.getBigDecimal("VALOR"));
                return opcional;
            }
            return null;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public List<Opcional> list(Operacao operacao) throws SQLException {
        switch (operacao) {
            case LISTAR:
                return listAll();
            default:
                return null;
        }
    }

    private List<Opcional> listAll() throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder select = new StringBuilder();
            select.append(" select   * ");
            select.append(" from     opcional ");
            select.append(" order by nome ");

            ps = dao.getPreparedStatement(select.toString());
            ResultSet rs = ps.executeQuery();

            List<Opcional> opcionais = new ArrayList<>();
            Opcional opcional;
            while (rs.next()) {
                opcional = new Opcional();
                opcional.setNrSequencia(rs.getLong("NR_SEQUENCIA"));
                opcional.setNome(rs.getString("NOME"));
                opcional.setDescricao(rs.getString("DESCRICAO"));
                opcional.setValor(rs.getBigDecimal("VALOR"));
                opcionais.add(opcional);
            }
            return opcionais;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public void destroy() throws SQLException {
        dao.desconectar();
    }
}
