package CaioRenanHobus.Visao;

import CaioRenanHobus.Controle.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class EditorAdicionar extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private JButton btAdicionar;
    private ProdutoControl controle;
    private JTable table;

    public EditorAdicionar(ProdutoControl controle) {
        this.controle = controle;
        Icon figura = new ImageIcon("Adicionar.jpg");//imagem adicionar
        btAdicionar = new JButton(figura);
        btAdicionar.addActionListener(this);
    }

    public Object getCellEditorValue() {
        return controle;
    }

    public Component getTableCellEditorComponent(JTable tabela, Object valor, boolean selecionado, int linha, int coluna) {
        table = tabela;
        return btAdicionar;
    }

    public void actionPerformed(ActionEvent evento) {
        controle.adicionar(table.getSelectedRow());
        stopCellEditing();
    }
}
