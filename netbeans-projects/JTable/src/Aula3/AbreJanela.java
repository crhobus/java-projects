package Aula3;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;

public class AbreJanela extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private String nomePersonagem;

    public Object getCellEditorValue() {
        return nomePersonagem;
    }
    private JButton botao = new JButton("Clique-me");

    public AbreJanela() {
        botao.addActionListener(this);
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return botao;
    }

    public void actionPerformed(ActionEvent arg0) {
        JFileChooser selecionar = new JFileChooser();
        selecionar.setCurrentDirectory(new File("."));
        int res = selecionar.showOpenDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            nomePersonagem = selecionar.getSelectedFile().getName();
            nomePersonagem = nomePersonagem.substring(0, nomePersonagem.length() - 4);
            stopCellEditing();
        } else {
            cancelCellEditing();
        }

    }
}




