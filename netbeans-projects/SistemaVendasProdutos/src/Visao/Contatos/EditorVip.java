package Visao.Contatos;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class EditorVip extends AbstractCellEditor implements TableCellEditor {

    private JCheckBox check1, check2, check3;
    private JPanel painel;

    public EditorVip() {
        painel = new JPanel();
        check1 = new JCheckBox();
        check1.setBackground(Color.WHITE);
        painel.add(check1);
        check2 = new JCheckBox();
        check2.setBackground(Color.WHITE);
        painel.add(check2);
        check3 = new JCheckBox();
        check3.setBackground(Color.WHITE);
        painel.add(check3);
        painel.setBackground(Color.WHITE);
    }

    public Object getCellEditorValue() {
        Boolean[] vip = new Boolean[3];
        vip[0] = check1.isSelected();
        vip[1] = check2.isSelected();
        vip[2] = check3.isSelected();
        return vip;
    }

    public Component getTableCellEditorComponent(JTable tabela, Object valor, boolean selecionado, int linha, int coluna) {
        Boolean[] v = (Boolean[]) valor;
        check1.setSelected(v[0]);
        check2.setSelected(v[1]);
        check3.setSelected(v[2]);
        return painel;
    }
}
