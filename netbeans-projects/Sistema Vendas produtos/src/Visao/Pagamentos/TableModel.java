package Visao.Pagamentos;

import Controle.PagamentosControl;
import javax.swing.table.*;

public class TableModel extends AbstractTableModel {

    private PagamentosControl controle;

    public TableModel(PagamentosControl controle) {
        this.controle = controle;
    }

    public int getRowCount() {
        return controle.tamanho();
    }

    public int getColumnCount() {
        return 11;
    }

    public Object getValueAt(int linha, int coluna) {
        return controle.conteudoLinha(linha, coluna);
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Codigo";
            case 1:
                return "Codigo Venda";
            case 2:
                return "Cliente";
            case 3:
                return "Data Emissão";
            case 4:
                return "Total";
            case 5:
                return "Descontos";
            case 6:
                return "Cond. Pagto";
            case 7:
                return "Valor Parcelas";
            case 8:
                return "Valor a ser Pago";
            case 9:
                return "Parcelas Restantes";
            default:
                return "Situação";
        }
    }
}
