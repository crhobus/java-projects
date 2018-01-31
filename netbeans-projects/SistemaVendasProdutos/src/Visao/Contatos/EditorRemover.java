package Visao.Contatos;

import Controle.ContatosControl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class EditorRemover extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private ContatosControl controle;
    private JTable tabela;
    private JButton btRemover;

    public EditorRemover(ContatosControl controle) {
        this.controle = controle;
        Icon figura = new ImageIcon("botaoRemover.png");//imagem adicionar
        btRemover = new JButton(figura);
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
