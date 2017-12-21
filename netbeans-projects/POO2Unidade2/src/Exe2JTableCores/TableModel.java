package Exe2JTableCores;

import java.awt.*;
import java.util.*;
import javax.swing.table.*;

public class TableModel extends AbstractTableModel {

    private ArrayList<Linhas> lista = new ArrayList<Linhas>();

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int row, int col) {
        Linhas lin = lista.get(row);
        switch (col) {
            case 0:
                return lin.texto;
            case 1:
                return lin.usaCor;
            case 2:
                return lin.cor;
            default:
                return lin.cor;
        }
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Texto";
            case 1:
                return "Usar Cor";
            case 2:
                return "Cor";
            default:
                return "Cor";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return String.class;
            case 1:
                return Boolean.class;
            case 2:
                return Color.class;
            default:
                return Color.class;
        }
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        return coluna == 1 || coluna == 2 || coluna == 3;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        Linhas cadas = lista.get(linha);
        try {
            switch (coluna) {
                case 1:
                    cadas.setUsaCor((Boolean) valor);
                    break;
                case 2:
                    Scanner sc = new Scanner((String) valor);
                    sc.useDelimiter("\\D+");
                    Color color = new Color(sc.nextInt(), sc.nextInt(), sc.nextInt());
                    cadas.setCor(color);
                    break;
                case 3:
                    cadas.setCor((Color) valor);
            }
            fireTableRowsUpdated(linha, linha);
        } catch (Exception ex) {
        }
    }

    public void adicionar(String texto) {
        Linhas lin = new Linhas();
        lin.texto = texto;
        lin.usaCor = false;
        lin.cor = Color.WHITE;
        lista.add(lin);
    }

    private class Linhas {

        private String texto;
        private boolean usaCor;
        private Color cor;

        public Color getCor() {
            return cor;
        }

        public void setCor(Color cor) {
            this.cor = cor;
        }

        public String getTexto() {
            return texto;
        }

        public void setTexto(String texto) {
            this.texto = texto;
        }

        public boolean isUsaCor() {
            return usaCor;
        }

        public void setUsaCor(boolean usaCor) {
            this.usaCor = usaCor;
        }
    }
}
