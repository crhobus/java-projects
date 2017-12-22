package Visao.Contatos;

import Controle.ContatosControl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class EditorSelecao extends AbstractCellEditor implements TableCellEditor, ItemListener {

    private JCheckBox check;
    private JPanel painel;
    private ContatosControl controle;

    public EditorSelecao(ContatosControl controle) {
        this.controle = controle;
        painel = new JPanel();
        check = new JCheckBox();
        check.setBackground(Color.WHITE);
        painel.add(check);
        check.addItemListener(this);
        painel.setBackground(Color.WHITE);
    }

    public Object getCellEditorValue() {
        return check.isSelected();
    }

    public Component getTableCellEditorComponent(JTable tabela, Object valor, boolean selecionado, int linha, int coluna) {
        return painel;
    }

    public void itemStateChanged(ItemEvent evento) {
        stopCellEditing();
    }

    @Override
    public boolean stopCellEditing() {
        if (controle.verificaSelecionado() == true) {
            fireEditingStopped();
            return true;
        } else {
            cancelCellEditing();
            return false;
        }
    }
}
