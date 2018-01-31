package view.componentes.table;

import java.awt.Color;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class TTable extends JScrollPane {

    private JTable tabela;
    private TableModel tableModel;
    private TableRenderer rendener;
    private boolean gridMultiSelected;
    private JPopupMenu popupMenu;
    private boolean configurar;

    public TTable() {
        tabela = new JTable();
    }

    public boolean isConfigurar() {
        return configurar;
    }

    public void setConfigurar(boolean configurar) {
        this.configurar = configurar;
    }

    public boolean isGridMultiSelected() {
        return gridMultiSelected;
    }

    public void setGridMultiSelected(boolean gridMultiSelected) {
        this.gridMultiSelected = gridMultiSelected;
    }

    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }

    public void setPopupMenu(JPopupMenu popupMenu) {
        this.popupMenu = popupMenu;
    }

    public TableRenderer getRendener() {
        return rendener;
    }

    public void setRendener(TableRenderer rendener) {
        this.rendener = rendener;
    }

    public JTable getTabela() {
        return tabela;
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }

    //Chamar este método no construtor, obrigatório
    public void inicializaTable() {
        if (configurar) {
            tabela.setModel(tableModel);
            tabela.setRowHeight(22);
            tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            if (!gridMultiSelected) {
                tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            }
            for (int y = 0; y < tabela.getColumnModel().getColumnCount(); y++) {
                tabela.getColumnModel().getColumn(y).setCellRenderer(rendener);
            }
            JTableHeader header = tabela.getTableHeader();
            header.setComponentPopupMenu(popupMenu);
            header.setReorderingAllowed(false);
            ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            setViewportView(tabela);
            getViewport().setBackground(Color.WHITE);
            setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            JTable linhaTabela = new RowNumberTable(tabela);
            setRowHeaderView(linhaTabela);
            setCorner(JScrollPane.UPPER_LEFT_CORNER, linhaTabela.getTableHeader());
            if (tabela.getRowCount() > 0) {
                tabela.addRowSelectionInterval(0, 0);
                tabela.addColumnSelectionInterval(0, 0);
            }
            if (popupMenu != null) {
                tabela.setComponentPopupMenu(popupMenu);
                setComponentPopupMenu(popupMenu);
                linhaTabela.setComponentPopupMenu(popupMenu);
                header.setComponentPopupMenu(popupMenu);
            }
        }
    }

    public void selecionarLinha(int n) {
        tabela.addRowSelectionInterval(n, n);
    }
}
