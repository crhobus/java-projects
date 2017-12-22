package Visao.Setor;

import Controle.SetorControl;
import javax.swing.table.*;

public class TableModel extends AbstractTableModel {

    private SetorControl controle;

    public TableModel(SetorControl controle) {
        this.controle = controle;
    }

    public int getRowCount() {
        return controle.tamanho();
    }

    public int getColumnCount() {
        return 2;
    }

    public Object getValueAt(int linha, int coluna) {
        return controle.conteudoLinha(linha, coluna);
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Codigo";
            default:
                return "Nome";
        }
    }
}
