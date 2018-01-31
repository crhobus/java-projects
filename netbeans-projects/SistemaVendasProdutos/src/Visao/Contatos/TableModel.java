package Visao.Contatos;

import Controle.ContatosControl;
import javax.swing.table.*;

public class TableModel extends AbstractTableModel {

    private ContatosControl controle;

    public TableModel(ContatosControl controle) {
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
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Sel";
            case 1:
                return "Codigo";
            case 2:
                return "Nome";
            case 3:
                return "Vip";
            case 4:
                return "Fone 1";
            case 5:
                return "Fone 2";
            case 6:
                return "Fone 3";
            case 7:
                return "E-Mail";
            default:
                return "Remover";
        }
    }

    @Override
    public Class<?> getColumnClass(int coluna) {
        switch (coluna) {
            case 0:
                return Boolean.class;
            case 1:
                return Integer.class;
            case 2:
                return String.class;
            case 3:
                return Boolean[].class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return String.class;
            case 7:
                return String.class;
            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        Boolean[] aux = new Boolean[3];
        aux = (Boolean[]) getValueAt(linha, 3);

        if (coluna == 0) {
            return true;
        }
        if (coluna == 1) {
            return false;
        }
        if (coluna == 2) {
            return true;
        }
        if (coluna == 3) {
            return true;
        }
        if (coluna == 4) {
            return aux[0];
        }
        if (coluna == 5) {
            return aux[1];
        }
        if (coluna == 6) {
            return aux[2];
        }
        if (coluna == 7) {
            return true;
        }
        if (coluna == 8) {
            return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        controle.alteraLinha(valor, linha, coluna);
        fireTableDataChanged();
    }

    public void adicionar() {
        Boolean vip[];
        vip = new Boolean[3];
        vip[0] = false;
        vip[1] = false;
        vip[2] = false;
        controle.adicionar("", "(  )    -    ", "(  )    -    ", "(  )    -    ", "", vip);
    }
}
