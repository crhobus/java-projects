package Exe3Planilha;

import java.util.*;
import javax.swing.table.*;

public class TableModel extends AbstractTableModel {

    private ArrayList<ArrayPlanilha> lista = new ArrayList<ArrayPlanilha>();

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int linha, int coluna) {
        ArrayPlanilha cadas = lista.get(linha);
        switch (coluna) {
            case 0:
                return cadas.getNum1();
            case 1:
                return cadas.getNum2();
            case 2:
                return cadas.getOperacao();
            default:
                return cadas.getResultado();
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return Double.class;
            case 1:
                return Double.class;
            case 2:
                return String.class;
            default:
                return Double.class;
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Numero 1";
            case 1:
                return "Numero 2";
            case 2:
                return "Operação";
            default:
                return "Resultado";
        }
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        return coluna == 0 || coluna == 1 || coluna == 2;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        ArrayPlanilha cadas = lista.get(linha);
        try {
            switch (coluna) {
                case 0:
                    cadas.setNum1((Double) valor);
                    break;
                case 1:
                    cadas.setNum2((Double) valor);
                    break;
                case 2:
                    cadas.setOperacao((String) valor);
                    break;
            }
            fireTableDataChanged();
        } catch (Exception ex) {
        }
    }

    public void adicionar() {
        ArrayPlanilha cadas = new ArrayPlanilha(lista);
        lista.add(cadas);
    }

    public void remover(int linha) {
        lista.remove(linha);
        fireTableRowsUpdated(linha, linha);
        fireTableDataChanged();
    }
}
