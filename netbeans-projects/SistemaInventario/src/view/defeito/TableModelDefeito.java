package view.defeito;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import dbOracle.DefeitoDAO;

public class TableModelDefeito extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private DefeitoDAO defeitoDAO;

    public TableModelDefeito(DefeitoDAO defeitoDAO) {
        this.defeitoDAO = defeitoDAO;
        try {
            defeitoDAO.listDefeitos();
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
        return defeitoDAO.getQtdadeDefeitosCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        try {
            return defeitoDAO.conteudoTableModel(linha, coluna);
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
                return "Defeito";
        }
    }
}
