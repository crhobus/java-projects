package View;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private String[][] linhas;
    private String[] colunas;
    private int qtdadeLinhas, qtdadeColunas;

    public TableModel(String[][] linhas, String[] colunas, int qtdadeLinhas, int qtdadeColunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.qtdadeLinhas = qtdadeLinhas;
        this.qtdadeColunas = qtdadeColunas;
    }

    @Override
    public int getColumnCount() {
        return qtdadeColunas;
    }

    @Override
    public int getRowCount() {
        return qtdadeLinhas;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return linhas[linha][coluna];
    }

    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna];
    }
}
