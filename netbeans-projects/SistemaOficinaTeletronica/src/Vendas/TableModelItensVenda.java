package Vendas;

import javax.swing.table.AbstractTableModel;

public class TableModelItensVenda extends AbstractTableModel {

    private static final long serialVersionUID = -8218199244471520822L;
    private ItensVendaControl controle;

    public TableModelItensVenda(ItensVendaControl controle) {
        this.controle = controle;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeItensCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return controle.conteudoTableModel(linha, coluna);
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Unidade";
            case 1:
                return "Nome Item";
            case 2:
                return "Descrição";
            case 3:
                return "Valor Unit.";
            default:
                return "Valor Total";
        }
    }
}
