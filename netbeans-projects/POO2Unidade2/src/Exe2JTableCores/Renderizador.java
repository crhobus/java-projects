package Exe2JTableCores;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class Renderizador implements TableCellRenderer {

    public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado, boolean temFocus, int linha, int coluna) {
        Color cor = (Color) tabela.getModel().getValueAt(linha, 2);
        JComponent jc = null;
        JLabel label = new JLabel(valor.toString());
        label.setOpaque(true);
        jc = label;

        if (valor.getClass() == Boolean.class) {
            JCheckBox check = new JCheckBox();
            check.setSelected((Boolean) valor);
            check.setHorizontalAlignment(SwingConstants.CENTER);
            jc = check;
        }
        if (valor.getClass() == Color.class) {
            jc.setBackground(cor);
        } else {
            jc.setBackground(tabela.getBackground());
        }
        if ((Boolean) tabela.getModel().getValueAt(linha, 1) == true) {
            jc.setBackground(cor);
        }
        return jc;
    }
}

