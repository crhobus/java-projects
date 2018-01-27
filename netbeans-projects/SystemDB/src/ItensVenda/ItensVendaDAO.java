package ItensVenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.ItensVenda;
import Modelo.Produto;
import Principal.DBDAO;

public class ItensVendaDAO extends DBDAO {

    private Connection con;

    public ItensVendaDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertItemVenda(ItensVenda itensVenda) throws SQLException {
        PreparedStatement stm = null;
        try {
            int codItem = getProxCod("SELECT MAX(CODITEM) FROM TBITENSVENDA",
                    "Erro na leitura do item no sistema");
            stm = con.prepareStatement("INSERT INTO TBITENSVENDA (CODITEM, NUMVENDA, "
                    + "CODPRODUTO, QTDADE) VALUES (?, ?, ?, ?)");
            stm.setInt(1, codItem);
            stm.setInt(2, itensVenda.getVendas().getNumVenda());
            stm.setInt(3, itensVenda.getProduto().getCodProduto());
            stm.setInt(4, itensVenda.getQtdade());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o item no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public List<ItensVenda> listItens(int codVenda) throws SQLException {
        return getLista("SELECT NP.NOMEPROD, MA.MARCA, MO.MODELO, P.DESCRICAO, I.QTDADE, "
                + "P.PRECOCOMPRA, P.PERCENTUALLUCRO, P.IPI, P.DESCONTOS FROM TBITENSVENDA I "
                + "INNER JOIN TBPRODUTO P ON I.CODPRODUTO = P.CODPRODUTO "
                + "INNER JOIN TBNOMEPROD NP ON P.CODNOMEPROD = NP.CODNOMEPROD "
                + "INNER JOIN TBMARCA MA ON P.CODMARCA = MA.CODMARCA "
                + "INNER JOIN TBMODELO MO ON P.CODMODELO = MO.CODMODELO "
                + "WHERE I.NUMVENDA = " + codVenda + " ORDER BY I.CODITEM");
    }

    private List<ItensVenda> getLista(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            List<ItensVenda> lista = new ArrayList<ItensVenda>();
            rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getItensAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de itens no sistema");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private ItensVenda getItensAux(ResultSet rs) throws SQLException {
        try {
            ItensVenda item = new ItensVenda();
            Produto produto = new Produto();
            produto.setProduto(rs.getString(1));
            produto.setMarca(rs.getString(2));
            produto.setModelo(rs.getString(3));
            produto.setDescricao(rs.getString(4));
            item.setQtdade(rs.getInt(5));
            produto.setPrecoCompra(rs.getDouble(6));
            produto.setPercentualLucro(rs.getDouble(7));
            produto.setIpi(rs.getDouble(8));
            produto.setDescontos(rs.getDouble(9));
            item.setProduto(produto);
            return item;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de itens no sistema");
        }
    }
}
