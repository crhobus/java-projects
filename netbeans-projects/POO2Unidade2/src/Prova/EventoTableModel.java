package Prova;

import java.util.*;

import javax.swing.table.AbstractTableModel;

public class EventoTableModel extends AbstractTableModel {

    private List<Evento> eventos = new ArrayList<Evento>();

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return eventos.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        Evento e = eventos.get(row);
        switch (col) {
            case 0:
                return e.getNome();
            case 1:
                return e.getData();
            default:
                Boolean b[] = new Boolean[3];
                b[0] = e.isChuva();
                b[1] = e.isCapac();
                b[2] = e.isCaro();
                return b;
        }
    }

    @Override
    public void setValueAt(Object valor, int row, int col) {
        Boolean b[] = (Boolean[]) valor;
        Evento e = eventos.get(row);
        e.setChuva(b[0]);
        e.setCapac(b[1]);
        e.setCaro(b[2]);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return String.class;
            case 1:
                return Date.class;
            default:
                return Boolean[].class;
        }
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Nome";
            case 1:
                return "Data";
            default:
                return "Restrições";
        }
    }

    public void add(Evento evento) {
        eventos.add(evento);
    }
}
