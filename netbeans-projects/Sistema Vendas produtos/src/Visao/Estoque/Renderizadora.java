package Visao.Estoque;

import java.awt.*;
import java.text.*;
import javax.swing.*;
import javax.swing.table.*;

public class Renderizadora implements TableCellRenderer {

    private NumberFormat nRealf = NumberFormat.getCurrencyInstance();

    public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado, boolean temFocus, int linha, int coluna) {
        JComponent jc = null;
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        JLabel label = new JLabel(valor.toString());
        label.setFont(fonte);
        label.setOpaque(true);
        jc = label;
        if (valor.getClass() == String.class || valor.getClass() == Integer.class || valor.getClass() == Double.class) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }
        if (valor.getClass() == Double.class) {
            label.setText(nRealf.format(valor).toString());
        }
        jc.setBackground(Color.WHITE);
        return jc;
    }
}
