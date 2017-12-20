package Tabela;

import Controle.Controle;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private Controle controle;

    public TableModel(Controle controle) {
        this.controle = controle;
    }

    public int getRowCount() {
        return controle.tamanho();
    }

    public int getColumnCount() {
        return 9;
    }

    public Object getValueAt(int linha, int coluna) {
        return controle.conteudoLinha(linha, coluna);
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        return coluna == 0 || coluna == 8;
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Arquivo";
            case 1:
                return "Nome";
            case 2:
                return "Duração";
            case 3:
                return "Tamanho";
            case 4:
                return "Audio";
            case 5:
                return "Vídeo";
            case 6:
                return "Imagem";
            case 7:
                return "Ult. Modif.";
            default:
                return "Remover";
        }
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        fireTableDataChanged();
    }
}
