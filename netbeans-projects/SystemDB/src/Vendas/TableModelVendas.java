package Vendas;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelVendas extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private VendasControl controle;

    public TableModelVendas(VendasControl controle) {
        this.controle = controle;
        try {
            controle.listarVendas();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeClieCadas();
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
                return "Data Emissão";
            case 2:
                return "Situação";
            case 3:
                return "Vendedor";
            case 4:
                return "Tipo";
            case 5:
                return "Cliente";
            default:
                return "CPF / CNPJ";
        }
    }
}
