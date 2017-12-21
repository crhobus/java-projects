package JTable;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;

public class AbreJanela extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private JButton botao;
    private String nome;

    public AbreJanela() {
        botao = new JButton("Alterar");
        botao.addActionListener(this);
    }

    public Object getCellEditorValue() {
        return nome;
    }

    public Component getTableCellEditorComponent(JTable tabela, Object valor, boolean seleciona, int linha, int coluna) {
        return botao;
    }

    public void actionPerformed(ActionEvent evento) {
        JFileChooser selecionar = new JFileChooser();
        selecionar.setCurrentDirectory(new File("."));

        int res = selecionar.showOpenDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            nome = selecionar.getSelectedFile().getName();
            nome = nome.substring(0, nome.length() - 4);
            stopCellEditing();
        } else {
            cancelCellEditing();
        }
    }
}
