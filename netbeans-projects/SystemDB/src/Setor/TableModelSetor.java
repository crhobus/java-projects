package Setor;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelSetor extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private SetorControl controle;

    public TableModelSetor(SetorControl controle) {
        this.controle = controle;
        try {
            controle.listarSetores();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeSetorCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return controle.conteudoTableModel(linha, coluna);
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Código";
            default:
                return "Setor";
        }
    }
}
