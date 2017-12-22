package dbOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import model.Chamada;
import model.ItemChamada;

public class ItemChamadaDAO extends DBDAO {

    private Connection con;
    private Map<Integer, ItemChamada> itensChamada;
    private SimpleDateFormat formatDate;
    private SimpleDateFormat formatHora;

    public ItemChamadaDAO(Connection con) {
        super(con);
        this.con = con;
        itensChamada = new HashMap<Integer, ItemChamada>();
        formatDate = new SimpleDateFormat("dd/MM/yyyy");
        formatHora = new SimpleDateFormat("HH:mm");
    }

    public boolean insertItemChamada(ItemChamada itemChamada) throws Exception {
        PreparedStatement stm = null;
        try {
            int codItem = getProxCod("SELECT MAX(CODITEMCHAM) FROM TBITEMCHAMADA",
                    "Erro na leitura do item no sistema");
            stm = con.prepareStatement("INSERT INTO TBITEMCHAMADA (CODITEMCHAM, DESCRICAO, QTDADE, VALOR, "
                    + "DATAITEM, NUMCHAMADA) VALUES (?, ?, ?, ?, ?, ?)");
            stm.setInt(1, codItem);
            stm.setString(2, itemChamada.getDescricao());
            stm.setInt(3, itemChamada.getQtdade());
            stm.setDouble(4, itemChamada.getValor());
            stm.setTimestamp(5, new Timestamp(itemChamada.getDataItem().getTime()));
            stm.setInt(6, itemChamada.getChamada().getNumeroCha());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o item no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean deleteItem(int codItem) throws SQLException {
        return delete("DELETE FROM TBITEMCHAMADA WHERE CODITEMCHAM = " + itensChamada.get(codItem).getCodigo(),
                "Não foi possível excluir o item do sistema");
    }

    public void listItens(int numChamada) throws SQLException {
        itensChamada.clear();
        ResultSet rs = null;
        try {
            rs = con.prepareStatement("SELECT * FROM TBITEMCHAMADA WHERE NUMCHAMADA = "
                    + numChamada + " ORDER BY CODITEMCHAM").executeQuery();
            int linha = 0;
            while (rs.next()) {
                itensChamada.put(linha, getItensAux(rs));
                linha++;
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de itens no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    public String getItensChamada(int numChamada) {
        String itens = "";
        for (int i = 0; i < itensChamada.size(); i++) {
            ItemChamada item = itensChamada.get(i);
            itens += "\n" + (i + 1) + " - " + item.getDescricao();
        }
        return itens;
    }

    private ItemChamada getItensAux(ResultSet rs) throws SQLException {
        try {
            ItemChamada item = new ItemChamada();
            item.setCodigo(rs.getInt(1));
            item.setDescricao(rs.getString(2));
            item.setQtdade(rs.getInt(3));
            item.setValor(rs.getDouble(4));
            item.setDataItem(rs.getTimestamp(5));
            Chamada chamada = new Chamada();
            chamada.setNumeroCha(rs.getInt(6));
            item.setChamada(chamada);
            return item;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do item no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        }
    }

    public void limparMapa() {
        itensChamada.clear();
    }

    public boolean isMapaVazio() {
        return itensChamada.isEmpty();
    }

    public Object conteudoTableModel(int linha, int coluna) throws SQLException {
        ItemChamada item = itensChamada.get(linha);
        switch (coluna) {
            case 0:
                return item.getQtdade();
            case 1:
                return item.getDescricao();
            case 2:
                return item.getValor();
            default:
                return formatDate.format(item.getDataItem()) + " as " + formatHora.format(item.getDataItem());
        }
    }

    public int getQtdadeItensCadas() {
        return itensChamada.size();
    }
}
