package br.com.model.dao;

import br.com.model.beans.Cliente;
import br.com.model.beans.ItemPedido;
import br.com.model.beans.Opcional;
import br.com.model.beans.Pedido;
import br.com.model.beans.Veiculo;
import br.com.model.dao.util.DAO;
import br.com.model.dao.util.DAOAdapter;
import br.com.model.dao.util.Operacao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO extends DAOAdapter<Pedido> {

    private final DAO dao = new DAO();

    @Override
    public void init() throws SQLException, ClassNotFoundException {
        dao.conectar();
    }

    @Override
    public Pedido insert(Pedido pedido) throws SQLException {
        pedido.setNrSequencia(dao.getSequence("pedido_seq.nextval"));
        PreparedStatement ps = null;
        try {
            StringBuilder insert = new StringBuilder();
            insert.append(" insert into pedido ( ");
            insert.append("                    nr_sequencia, ");
            insert.append("                    nr_seq_cliente, ");
            insert.append("                    nr_seq_veiculo, ");
            insert.append("                    dt_emissao, ");
            insert.append("                    situacao, ");
            insert.append("                    valor_veiculo, ");
            insert.append("                    sub_total, ");
            insert.append("                    desconto, ");
            insert.append("                    valor_total, ");
            insert.append("                    condicao_pagto ");
            insert.append("           ) values ( ");
            insert.append("                    :nr_sequencia, ");
            insert.append("                    :nr_seq_cliente, ");
            insert.append("                    :nr_seq_veiculo, ");
            insert.append("                    :dt_emissao, ");
            insert.append("                    :situacao, ");
            insert.append("                    :valor_veiculo, ");
            insert.append("                    :sub_total, ");
            insert.append("                    :desconto, ");
            insert.append("                    :valor_total, ");
            insert.append("                    :condicao_pagto) ");

            ps = dao.getPreparedStatement(insert.toString());

            ps.setLong(1, pedido.getNrSequencia());
            ps.setLong(2, pedido.getCliente().getNrSequencia());
            ps.setLong(3, pedido.getVeiculo().getNrSequencia());
            ps.setDate(4, new Date(pedido.getDtEmissao().getTime()));
            ps.setString(5, pedido.getSituacao());
            ps.setBigDecimal(6, pedido.getValorVeiculo());
            ps.setBigDecimal(7, pedido.getSubTotal());
            ps.setBigDecimal(8, pedido.getDesconto());
            ps.setBigDecimal(9, pedido.getValorTotal());
            ps.setString(10, pedido.getCondicaoPagto());

            ps.executeUpdate();

            pedido.setItensPedido(listAllItensPedido(pedido.getNrSequencia()));

            return pedido;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public Pedido update(Pedido pedido) throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder update = new StringBuilder();
            update.append(" update pedido ");
            update.append(" set    nr_seq_cliente   = :nr_seq_cliente, ");
            update.append("        nr_seq_veiculo   = :nr_seq_veiculo, ");
            update.append("        dt_emissao       = :dt_emissao, ");
            update.append("        situacao         = :situacao, ");
            update.append("        valor_veiculo    = :valor_veiculo, ");
            update.append("        sub_total        = :sub_total, ");
            update.append("        desconto         = :desconto, ");
            update.append("        valor_total      = :valor_total, ");
            update.append("        condicao_pagto   = :condicao_pagto ");
            update.append(" where  nr_sequencia     = :nr_sequencia ");

            ps = dao.getPreparedStatement(update.toString());

            ps.setLong(1, pedido.getCliente().getNrSequencia());
            ps.setLong(2, pedido.getVeiculo().getNrSequencia());
            ps.setDate(3, new Date(pedido.getDtEmissao().getTime()));
            ps.setString(4, pedido.getSituacao());
            ps.setBigDecimal(5, pedido.getValorVeiculo());
            ps.setBigDecimal(6, pedido.getSubTotal());
            ps.setBigDecimal(7, pedido.getDesconto());
            ps.setBigDecimal(8, pedido.getValorTotal());
            ps.setString(9, pedido.getCondicaoPagto());
            ps.setLong(10, pedido.getNrSequencia());

            ps.executeUpdate();

            pedido.setItensPedido(listAllItensPedido(pedido.getNrSequencia()));

            return pedido;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public void delete(long nrSequencia) throws SQLException {
        throw new UnsupportedOperationException("Não é possível excluir pedidos, o mesmo deverá ser cancelado");
    }

    @Override
    public Pedido get(long nrSequencia) throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder select = new StringBuilder();
            select.append(" select  * ");
            select.append(" from    pedido ");
            select.append(" where   nr_sequencia = :nr_sequencia ");

            ps = dao.getPreparedStatement(select.toString());
            ps.setLong(1, nrSequencia);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setNrSequencia(rs.getLong("NR_SEQUENCIA"));
                Cliente cliente = new Cliente();
                cliente.setNrSequencia(rs.getLong("NR_SEQ_CLIENTE"));
                pedido.setCliente(cliente);
                Veiculo veiculo = new Veiculo();
                veiculo.setNrSequencia(rs.getLong("NR_SEQ_VEICULO"));
                pedido.setVeiculo(veiculo);
                pedido.setDtEmissao(rs.getDate("DT_EMISSAO"));
                pedido.setSituacao(rs.getString("SITUACAO"));
                pedido.setValorVeiculo(rs.getBigDecimal("VALOR_VEICULO"));
                pedido.setSubTotal(rs.getBigDecimal("SUB_TOTAL"));
                pedido.setDesconto(rs.getBigDecimal("DESCONTO"));
                pedido.setValorTotal(rs.getBigDecimal("VALOR_TOTAL"));
                pedido.setCondicaoPagto(rs.getString("CONDICAO_PAGTO"));
                pedido.setItensPedido(listAllItensPedido(pedido.getNrSequencia()));
                return pedido;
            }
            return null;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public List<Pedido> list(Operacao operacao) throws SQLException {
        switch (operacao) {
            case LISTAR:
                return listAll();
            default:
                return null;
        }
    }

    private List<Pedido> listAll() throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder select = new StringBuilder();
            select.append(" select   a.*, ");
            select.append("          c.nome, ");
            select.append("          c.tipo_pessoa, ");
            select.append("          c.cpf, ");
            select.append("          c.cnpj, ");
            select.append("          v.marca, ");
            select.append("          v.modelo, ");
            select.append("          v.cor, ");
            select.append("          v.litros, ");
            select.append("          v.potencia, ");
            select.append("          v.valvulas, ");
            select.append("          v.categoria ");
            select.append(" from     pedido a, ");
            select.append("          cliente c, ");
            select.append("          veiculo v ");
            select.append(" where    a.nr_seq_cliente = c.nr_sequencia ");
            select.append(" and      a.nr_seq_veiculo = v.nr_sequencia ");
            select.append(" order by c.nome ");

            ps = dao.getPreparedStatement(select.toString());
            ResultSet rs = ps.executeQuery();

            List<Pedido> pedidos = new ArrayList<>();
            Pedido pedido;
            while (rs.next()) {
                pedido = new Pedido();
                pedido.setNrSequencia(rs.getLong("NR_SEQUENCIA"));
                Cliente cliente = new Cliente();
                cliente.setNrSequencia(rs.getLong("NR_SEQ_CLIENTE"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setTipoPessoa(rs.getString("TIPO_PESSOA"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setCnpj(rs.getString("CNPJ"));
                pedido.setCliente(cliente);
                Veiculo veiculo = new Veiculo();
                veiculo.setNrSequencia(rs.getLong("NR_SEQ_VEICULO"));
                veiculo.setMarca(rs.getString("MARCA"));
                veiculo.setModelo(rs.getString("MODELO"));
                veiculo.setCor(rs.getString("COR"));
                veiculo.setLitros(rs.getBigDecimal("LITROS"));
                veiculo.setPotencia(rs.getInt("POTENCIA"));
                veiculo.setValvulas(rs.getInt("VALVULAS"));
                veiculo.setCategoria(rs.getString("CATEGORIA"));
                pedido.setVeiculo(veiculo);
                pedido.setDtEmissao(rs.getDate("DT_EMISSAO"));
                pedido.setSituacao(rs.getString("SITUACAO"));
                pedido.setValorVeiculo(rs.getBigDecimal("VALOR_VEICULO"));
                pedido.setSubTotal(rs.getBigDecimal("SUB_TOTAL"));
                pedido.setDesconto(rs.getBigDecimal("DESCONTO"));
                pedido.setValorTotal(rs.getBigDecimal("VALOR_TOTAL"));
                pedido.setCondicaoPagto(rs.getString("CONDICAO_PAGTO"));
                pedidos.add(pedido);
            }
            return pedidos;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    private List<ItemPedido> listAllItensPedido(Long nrSeqPedido) throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder select = new StringBuilder();
            select.append(" select  a.*, ");
            select.append("         o.nome ");
            select.append(" from    item_pedido a, ");
            select.append("         opcional o ");
            select.append(" where   a.nr_seq_pedido   = :nr_seq_pedido ");
            select.append(" and     a.nr_seq_opcional = o.nr_sequencia ");

            ps = dao.getPreparedStatement(select.toString());
            ps.setLong(1, nrSeqPedido);
            ResultSet rs = ps.executeQuery();

            List<ItemPedido> itensPedido = new ArrayList<>();
            ItemPedido itemPedido;
            while (rs.next()) {
                itemPedido = new ItemPedido();
                itemPedido.setNrSequencia(rs.getLong("NR_SEQUENCIA"));
                Opcional opcional = new Opcional();
                opcional.setNrSequencia(rs.getLong("NR_SEQ_OPCIONAL"));
                opcional.setNome(rs.getString("NOME"));
                itemPedido.setOpcional(opcional);
                itemPedido.setValorOpcional(rs.getBigDecimal("VALOR_OPCIONAL"));
                itensPedido.add(itemPedido);
            }
            return itensPedido;
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
