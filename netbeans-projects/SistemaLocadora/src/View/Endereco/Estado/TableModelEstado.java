package View.Endereco.Estado;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import Control.Endereco.Estado.EstadoControl;

public class TableModelEstado extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private EstadoControl controle;

    public TableModelEstado(EstadoControl controle) {
        this.controle = controle;
        try {
            controle.listarEstados();
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
        return controle.getQtdadeEstadoCadas();
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
                return "Estado";
        }
    }
}
