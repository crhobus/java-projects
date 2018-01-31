package view.componentes.table;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableColumn;

public class RowNumberTable extends JTable implements ChangeListener, PropertyChangeListener {

    private JTable table;

    public RowNumberTable(JTable table) {
        this.table = table;
        table.addPropertyChangeListener(this);

        setFocusable(false);
        setAutoCreateColumnsFromModel(false);

        updateRowHeight();
        updateModel();
        updateSelectionModel();

        TableColumn coluna = new TableColumn();
        coluna.setHeaderValue("");
        addColumn(coluna);
        coluna.setCellRenderer(new RowNumberRenderer());

        getColumnModel().getColumn(0).setPreferredWidth(30);
        setPreferredScrollableViewportSize(getPreferredSize());

        getTableHeader().setReorderingAllowed(false);
    }

    @Override
    public void addNotify() {
        super.addNotify();

        Component c = getParent();

        if (c instanceof JViewport) {
            JViewport viewport = (JViewport) c;
            viewport.addChangeListener(this);
        }
    }

    @Override
    public int getRowCount() {
        return table.getRowCount();
    }

    @Override
    public int getRowHeight(int row) {
        return table.getRowHeight(row);
    }

    @Override
    public Object getValueAt(int row, int column) {
        return Integer.toString(row + 1);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public void stateChanged(ChangeEvent evt) {
        JViewport viewport = (JViewport) evt.getSource();
        JScrollPane scrollPane = (JScrollPane) viewport.getParent();
        scrollPane.getVerticalScrollBar().setValue(viewport.getViewPosition().y);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("rowHeight".equals(evt.getPropertyName())) {
            updateRowHeight();
        }

        if ("selectionModel".equals(evt.getPropertyName())) {
            updateSelectionModel();
        }

        if ("model".equals(evt.getPropertyName())) {
            updateModel();
        }
    }

    private void updateRowHeight() {
        setRowHeight(table.getRowHeight());
    }

    private void updateModel() {
        setModel(table.getModel());
    }

    private void updateSelectionModel() {
        setSelectionModel(table.getSelectionModel());
    }
}