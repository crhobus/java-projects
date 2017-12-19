package Vendedor;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private VendedorControl controle;

    public TableModel(VendedorControl controle) {
        this.controle = controle;
        try {
            this.controle.listaTodos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeVenCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return controle.conteudoTableModel(linha, coluna);
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Codigo";
            case 1:
                return "CPF";
            case 2:
                return "E-Mail Vendedor";
            case 3:
                return "Home Page";
            case 4:
                return "Comissão";
            default:
                return "Salário";
        }
    }
}