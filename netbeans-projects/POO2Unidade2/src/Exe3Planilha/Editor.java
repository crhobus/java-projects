package Exe3Planilha;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class Editor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private JButton botao;
    private String operacao;

    public Editor() {
        botao = new JButton("Clique-me");
        botao.addActionListener(this);
    }

    public Object getCellEditorValue() {
        return operacao;
    }

    public Component getTableCellEditorComponent(JTable tabela, Object valor, boolean selecionado, int linha, int coluna) {
        return botao;
    }

    public void actionPerformed(ActionEvent evento) {
        Selecao sel = new Selecao();
        sel.setModal(true);
        sel.setVisible(true);
        operacao = sel.getOperacao();
        if (!"".equals(operacao)) {
            fireEditingStopped();
            stopCellEditing();
        } else {
            cancelCellEditing();
        }
    }
}
