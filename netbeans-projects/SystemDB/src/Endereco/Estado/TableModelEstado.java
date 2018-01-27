package Endereco.Estado;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

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
        return 4;
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
            case 1:
                return "Estado";
            case 2:
                return "Região";
            default:
                return "País";
        }
    }
}
