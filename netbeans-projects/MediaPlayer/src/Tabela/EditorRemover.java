package Tabela;

import Controle.Controle;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class EditorRemover extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private JButton btRemover;
    private JTable tabela;
    private Controle controle;

    public EditorRemover(Controle controle) {
        this.controle = controle;
        btRemover = new JButton("remover");
        btRemover.addActionListener(this);
    }

    public Object getCellEditorValue() {
        return controle;
    }

    public Component getTableCellEditorComponent(JTable tabela, Object valor, boolean selecionado, int linha, int coluna) {
        this.tabela = tabela;
        return btRemover;
    }

    public void actionPerformed(ActionEvent evento) {
        controle.remover(tabela.getSelectedRow());
        stopCellEditing();
    }
}
