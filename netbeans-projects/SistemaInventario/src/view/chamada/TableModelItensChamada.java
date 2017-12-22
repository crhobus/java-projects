package view.chamada;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import dbOracle.ItemChamadaDAO;

public class TableModelItensChamada extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private ItemChamadaDAO itemChamadaDAO;

    public TableModelItensChamada(ItemChamadaDAO itemChamadaDAO) {
        this.itemChamadaDAO = itemChamadaDAO;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return itemChamadaDAO.getQtdadeItensCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        try {
            return itemChamadaDAO.conteudoTableModel(linha, coluna);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Quantidade";
            case 1:
                return "Descrição";
            case 2:
                return "Valor";
            default:
                return "Data";
        }
    }
}
