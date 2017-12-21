package CaioRenanHobus.Visao;

import CaioRenanHobus.Controle.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class EditorRemover extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private JButton btRemover;
    private ProdutoControl controle;
    private JTable table;

    public EditorRemover(ProdutoControl controle) {
        this.controle = controle;
        Icon figura = new ImageIcon("Remover.jpg");//imagem adicionar
        btRemover = new JButton(figura);
        btRemover.addActionListener(this);
    }

    public Object getCellEditorValue() {
        return controle;
    }

    public Component getTableCellEditorComponent(JTable tabela, Object valor, boolean selecionado, int linha, int coluna) {
        this.table = tabela;
        return btRemover;
    }

    public void actionPerformed(ActionEvent e) {
        controle.remover(table.getSelectedRow());
        stopCellEditing();
    }
}
