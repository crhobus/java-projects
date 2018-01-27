package Departamento;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelDepartamento extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private DepartamentoControl controle;

    public TableModelDepartamento(DepartamentoControl controle) {
        this.controle = controle;
        try {
            controle.listarDepartamentos();
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
        return controle.getQtdadeDepCadas();
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
                return "Departamento";
        }
    }
}
