package Aula3;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class SliderEditor extends JSlider implements TableCellEditor {

    public Component getTableCellEditorComponent(JTable tabela, Object valor, boolean isSelected, int row, int col) {
        Integer iV = (Integer) valor;
        setValue(iV);
        return this;
    }

    public Object getCellEditorValue() {
        return getValue();
    }

    public boolean isCellEditable(EventObject arg0) {
        return true;
    }

    public boolean shouldSelectCell(EventObject arg0) {
        return true;
    }
    private ArrayList<CellEditorListener> lista = new ArrayList<CellEditorListener>();

    public void addCellEditorListener(CellEditorListener listener) {
        lista.add(listener);
    }

    public void removeCellEditorListener(CellEditorListener listener) {
        lista.remove(listener);
    }

    public boolean stopCellEditing() {
        fireEditingStopped();
        return true;
    }

    protected void fireEditingStopped() {

        ChangeEvent change = new ChangeEvent(this);
        for (int x = lista.size() - 1; x >= 0; x--) {
            CellEditorListener editor = lista.get(x);
            editor.editingStopped(change);
        }

    }

    public void cancelCellEditing() {
    }
}
