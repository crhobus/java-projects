package CaioRenanHobus.Visao;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class Renderizadora implements TableCellRenderer {

    public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado, boolean temFocus, int linha, int coluna) {
        JComponent jc = null;
        if (valor != null) {
            JLabel label = new JLabel(valor.toString());
            label.setOpaque(true);
            jc = label;

            if (valor.getClass() == Double.class || valor.getClass() == String.class) {
                label.setHorizontalAlignment(SwingConstants.CENTER);
            }
            if ((Double) tabela.getModel().getValueAt(linha, 1) == 1) {
                label.setBackground(Color.GREEN);
            } else {
                tabela.setBackground(tabela.getBackground());
            }
        }
        return jc;
    }
}
