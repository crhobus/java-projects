package Prova;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class CheckBoxesRender extends JPanel implements TableCellRenderer {

    private JCheckBox c1;
    private JCheckBox c2;
    private JCheckBox c3;

    public CheckBoxesRender() {
        c1 = new JCheckBox();
        c1.setBackground(Color.white);
        c2 = new JCheckBox();
        c2.setBackground(Color.white);
        c3 = new JCheckBox();
        c3.setBackground(Color.white);
        add(c1);
        add(c2);
        add(c3);
        setBackground(Color.WHITE);
    }

    @Override
    public Component getTableCellRendererComponent(JTable tabela, Object valor,
            boolean selecionado, boolean temFocus, int linha, int coluna) {
        Boolean[] v = (Boolean[]) valor;
        c1.setSelected(v[0]);
        c2.setSelected(v[1]);
        c3.setSelected(v[2]);
        return this;
    }
}
