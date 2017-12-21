package Exe2JTableCores;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class Editor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private JButton botao;
    private Color cor;

    public Editor() {
        botao = new JButton("Alterar");
        botao.addActionListener(this);
    }

    public Object getCellEditorValue() {
        return cor;
    }

    public Component getTableCellEditorComponent(JTable tabela, Object valor, boolean selecionna, int linha, int coluna) {
        return botao;
    }

    public void actionPerformed(ActionEvent evento) {
        Color corSelecionada = JColorChooser.showDialog(null, "título da janela", cor);
        if (corSelecionada != null) {
            cor = corSelecionada;
            stopCellEditing();
        } else {
            cancelCellEditing();
        }
    }
}
