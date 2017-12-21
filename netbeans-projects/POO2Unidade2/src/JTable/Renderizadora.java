package JTable;

import java.awt.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class Renderizadora implements TableCellRenderer {

    private SimpleDateFormat sDataf = new SimpleDateFormat("dd/MM/yyyy");
    private NumberFormat nRealf = NumberFormat.getCurrencyInstance();

    public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado, boolean temFocus, int linha, int coluna) {
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
        if ((Boolean) tabela.getModel().getValueAt(linha, 1) == false) {
            label.setFont(tabela.getFont());
        }
        if (valor.getClass().equals(Date.class)) {
            label.setText(sDataf.format(valor).toString());
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }
        if (valor.getClass() == Double.class) {
            label.setText(nRealf.format(valor).toString());
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }
        if ((Double) tabela.getModel().getValueAt(linha, 2) >= 100) {
            jc.setForeground(Color.white);
            jc.setBackground(Color.red);
        } else {
            jc.setBackground(tabela.getBackground());
            jc.setForeground(tabela.getForeground());
        }
        return jc;
    }
}
