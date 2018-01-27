package ItensVenda;

import javax.swing.table.AbstractTableModel;

public class TableModelItensVenda extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
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
                return "Produto";
            case 1:
                return "Descrição";
            case 2:
                return "Quantidade";
            case 3:
                return "Valor Unitário";
            default:
                return "Valor Total";
        }
    }
}
