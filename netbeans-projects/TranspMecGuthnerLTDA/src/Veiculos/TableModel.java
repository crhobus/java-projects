package Veiculos;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private VeiculoControl controle;

    public TableModel(VeiculoControl controle) {
        this.controle = controle;
        try {
            this.controle.listaTodos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 14;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeVeiCadas();
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
                return "Nome";
            case 2:
                return "Ano";
            case 3:
                return "Modelo";
            case 4:
                return "Cor";
            case 5:
                return "Combustivel";
            case 6:
                return "Potência";
            case 7:
                return "Vávulas";
            case 8:
                return "Cilíndros";
            case 9:
                return "Placa";
            case 10:
                return "Chassi";
            case 11:
                return "RENAVAM";
            case 12:
                return "Tipo";
            default:
                return "CPF / CNPJ Cliente";
        }
    }
}
