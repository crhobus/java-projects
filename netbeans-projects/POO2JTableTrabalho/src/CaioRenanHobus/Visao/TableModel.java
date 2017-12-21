package CaioRenanHobus.Visao;

import CaioRenanHobus.Controle.*;
import javax.swing.*;
import javax.swing.table.*;

public class TableModel extends AbstractTableModel {

    private ProdutoControl controle;

    public TableModel(ProdutoControl controle) {
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
                return "Nome";
            case 1:
                return "Quantidade";
            case 2:
                return "Unidade";
            case 3:
                return "Adicionar";
            default:
                return "Remover";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return String.class;
            case 1:
                return Integer.class;
            case 2:
                return String.class;
            case 3:
                return JButton.class;
            default:
                return JButton.class;
        }
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        return coluna == 0 || coluna == 1 || coluna == 2 || coluna == 3 || coluna == 4;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        controle.alteraCelula(valor, linha, coluna);
        fireTableDataChanged();
    }
}
