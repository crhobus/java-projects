package Vendas;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelVendas extends AbstractTableModel {

    private static final long serialVersionUID = -353535813385447444L;
    private NovaVendaControl controle;

    public TableModelVendas(NovaVendaControl controle) {
        this.controle = controle;
        try {
            this.controle.limparLista();
            this.controle.listaTodos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 21;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeVendasCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return controle.conteudoTableModel(linha, coluna);
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Número Venda";
            case 1:
                return "Situação";
            case 2:
                return "Tipo";
            case 3:
                return "Vendedor";
            case 4:
                return "Data Emissão";
            case 5:
                return "Cliente";
            case 6:
                return "CPF / CNPJ";
            case 7:
                return "Endereço";
            case 8:
                return "Bairro";
            case 9:
                return "Número";
            case 10:
                return "Cidade";
            case 11:
                return "UF";
            case 12:
                return "Fone";
            case 13:
                return "Celular";
            case 14:
                return "Total";
            case 15:
                return "Cond. Pagto";
            case 16:
                return "Forma Pagto";
            case 17:
                return "Parcelas Mês";
            case 18:
                return "Juros";
            case 19:
                return "Parcelas Restantes";
            default:
                return "Valor Restante";
        }
    }
}
