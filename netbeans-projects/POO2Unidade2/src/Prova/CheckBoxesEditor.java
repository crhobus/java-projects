package Prova;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class CheckBoxesEditor extends AbstractCellEditor implements TableCellEditor {

    private JCheckBox c1;
    private JCheckBox c2;
    private JCheckBox c3;
    private JPanel p;

    public CheckBoxesEditor() {
        c1 = new JCheckBox();
        c1.setBackground(Color.white);
        c2 = new JCheckBox();
        c2.setBackground(Color.white);
        c3 = new JCheckBox();
        c3.setBackground(Color.white);

        p = new JPanel();
        p.add(c1);
        p.add(c2);
        p.add(c3);

        p.setBackground(Color.WHITE);
    }

    @Override
    public Component getTableCellEditorComponent(JTable tabela, Object valor,
            boolean selecionado, int linha, int coluna) {
        Boolean[] v = (Boolean[]) valor;

        c1.setSelected(v[0]);
        c2.setSelected(v[1]);
        c3.setSelected(v[2]);
        return p;
    }

    @Override
    public Object getCellEditorValue() {
        Boolean[] b = new Boolean[3];
        b[0] = c1.isSelected();
        b[1] = c2.isSelected();
        b[2] = c3.isSelected();
        return b;
    }
}
