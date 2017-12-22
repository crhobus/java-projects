package Visao.Cidade;

import Controle.CidadeControl;
import javax.swing.table.*;

public class TableModel extends AbstractTableModel {

    private CidadeControl controle;

    public TableModel(CidadeControl controle) {
        this.controle = controle;
    }

    public int getRowCount() {
        return controle.tamanho();
    }

    public int getColumnCount() {
        return 3;
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
                return "Cidade";
            default:
                return "Estado";
        }
    }
}
