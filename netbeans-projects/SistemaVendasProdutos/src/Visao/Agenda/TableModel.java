package Visao.Agenda;

import Controle.AgendaControl;
import javax.swing.table.*;

public class TableModel extends AbstractTableModel {

    private AgendaControl controle;

    public TableModel(AgendaControl controle) {
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
                return "Nome";
            case 2:
                return "Data";
            case 3:
                return "Horário";
            default:
                return "Descricao";
        }
    }
}
