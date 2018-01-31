package Visao.Vendas;

import Controle.ItemVendaControl;
import javax.swing.table.*;

public class TableModelItemsVendas extends AbstractTableModel {

    private ItemVendaControl controle;

    public TableModelItemsVendas(ItemVendaControl controle) {
        this.controle = controle;
    }

    public int getRowCount() {
        return controle.tamanho();
    }

    public int getColumnCount() {
        return 5;
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
                return "Produto";
            case 2:
                return "Descrição";
            case 3:
                return "Modelo";
            default:
                return "Total";
        }
    }

    public void limparTabela() {
        controle.limparLista();
        fireTableDataChanged();
    }
}
