package br.com.model.dao;

import br.com.model.beans.ItemPedido;
import br.com.model.beans.Opcional;
import br.com.model.dao.util.DAO;
import br.com.model.dao.util.DAOAdapter;
import br.com.model.dao.util.Operacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemPedidoDAO extends DAOAdapter<ItemPedido> {

    private final DAO dao = new DAO();

    @Override
    public void init() throws SQLException, ClassNotFoundException {
        dao.conectar();
    }

    @Override
    public ItemPedido insert(ItemPedido itemPedido) throws SQLException {
        itemPedido.setNrSequencia(dao.getSequence("item_pedido_seq.nextval"));
        PreparedStatement ps = null;
        try {
            StringBuilder insert = new StringBuilder();
            insert.append(" insert into item_pedido ( ");
            insert.append("                         nr_sequencia, ");
            insert.append("                         nr_seq_opcional, ");
            insert.append("                         nr_seq_pedido, ");
            insert.append("                         valor_opcional ");
            insert.append("                ) values ( ");
            insert.append("                         :nr_sequencia, ");
            insert.append("                         :nr_seq_opcional, ");
            insert.append("                         :nr_seq_pedido, ");
            insert.append("                         :valor_opcional) ");

            ps = dao.getPreparedStatement(insert.toString());

            ps.setLong(1, itemPedido.getNrSequencia());
            ps.setLong(2, itemPedido.getOpcional().getNrSequencia());
            ps.setLong(3, getNrSequencia());
            ps.setBigDecimal(4, itemPedido.getValorOpcional());

            ps.executeUpdate();

            return itemPedido;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public ItemPedido update(ItemPedido itemPedido) throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder update = new StringBuilder();
            update.append(" update item_pedido ");
            update.append(" set    nr_seq_opcional  = :nr_seq_opcional, ");
            update.append("        valor_opcional   = :valor_opcional ");
            update.append(" where  nr_sequencia     = :nr_sequencia ");

            ps = dao.getPreparedStatement(update.toString());

            ps.setLong(1, itemPedido.getOpcional().getNrSequencia());
            ps.setBigDecimal(2, itemPedido.getValorOpcional());
            ps.setLong(3, itemPedido.getNrSequencia());

            ps.executeUpdate();

            return itemPedido;
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
            delete.append(" from    item_pedido ");
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
    public ItemPedido get(long nrSequencia) throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder select = new StringBuilder();
            select.append(" select  * ");
            select.append(" from    item_pedido ");
            select.append(" where   nr_sequencia = :nr_sequencia ");

            ps = dao.getPreparedStatement(select.toString());
            ps.setLong(1, nrSequencia);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setNrSequencia(rs.getLong("NR_SEQUENCIA"));
                Opcional opcional = new Opcional();
                opcional.setNrSequencia(rs.getLong("NR_SEQ_OPCIONAL"));
                itemPedido.setOpcional(opcional);
                itemPedido.setValorOpcional(rs.getBigDecimal("VALOR_OPCIONAL"));
                return itemPedido;
            }
            return null;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public List<ItemPedido> list(Operacao operacao) throws SQLException {
        throw new UnsupportedOperationException("Listagem dos itens direto no pedido");
    }

    @Override
    public void destroy() throws SQLException {
        dao.desconectar();
    }
}
