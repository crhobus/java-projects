package Endereco.Pais;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelPais extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private PaisControl controle;

    public TableModelPais(PaisControl controle) {
        this.controle = controle;
        try {
            controle.listarPaises();
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
        return controle.getQtdadePaisCadas();
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
                return "País";
        }
    }
}
