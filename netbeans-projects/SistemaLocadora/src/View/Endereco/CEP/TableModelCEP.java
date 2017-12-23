package View.Endereco.CEP;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import Control.Endereco.CEP.CEPControl;

public class TableModelCEP extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private CEPControl controle;

    public TableModelCEP(CEPControl controle) {
        this.controle = controle;
        try {
            controle.listarCeps();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeCepCadas();
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
            case 1:
                return "CEP";
            case 2:
                return "Cidade";
            default:
                return "Estado";
        }
    }
}
