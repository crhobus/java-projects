package View;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class Renderizadora implements TableCellRenderer {

    public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado, boolean temFocus, int linha, int coluna) {
        JComponent jc = null;
        JLabel label = new JLabel(valor.toString());
        label.setOpaque(true);
        jc = label;
        label.setHorizontalAlignment(SwingConstants.CENTER);
        if (linha == 1 || linha == 3) {
            jc.setBackground(Color.WHITE);
        }
        return jc;
    }
}
