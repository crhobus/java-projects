package view.ambiente;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import dbOracle.AmbienteDAO;

public class TableModelAmbiente extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private AmbienteDAO ambienteDAO;

    public TableModelAmbiente(AmbienteDAO ambienteDAO) {
        this.ambienteDAO = ambienteDAO;
        try {
            ambienteDAO.listAmbientes();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return ambienteDAO.getQtdadeAmbientesCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        try {
            return ambienteDAO.conteudoTableModel(linha, coluna);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Codigo";
            default:
                return "Ambiente";
        }
    }
}
