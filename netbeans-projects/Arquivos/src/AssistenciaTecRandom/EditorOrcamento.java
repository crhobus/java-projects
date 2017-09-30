package AssistenciaTecRandom;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class EditorOrcamento extends AbstractCellEditor implements TableCellEditor {

    private JCheckBox check;
    private JPanel painel;

    public EditorOrcamento() {
        painel = new JPanel();
        painel.setLayout(null);
        check = new JCheckBox();
        check.setBackground(Color.WHITE);
        check.setBounds(34, 3, 22, 17);
        painel.add(check);
        painel.setBackground(Color.WHITE);
    }

    public Object getCellEditorValue() {
        return check.isSelected();
    }

    public Component getTableCellEditorComponent(JTable tabela, Object valor, boolean selecionado, int linha, int coluna) {
        return painel;
    }
}
