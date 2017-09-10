package AssistenciaTecRandom;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class EditorSalvar extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private JButton button;

    public EditorSalvar() {
        button = new JButton("Salvar");
        button.addActionListener(this);
    }

    public Object getCellEditorValue() {
        return "Salvo";
    }

    public Component getTableCellEditorComponent(JTable tabela, Object valor, boolean selecionado, int linha, int coluna) {
        return button;
    }

    public void actionPerformed(ActionEvent evento) {
        stopCellEditing();
    }
}
