package Exe3Planilha;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class Renderizador implements TableCellRenderer {

    public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado, boolean temFocus, int linha, int coluna) {
        JComponent jc = null;
        if (valor != null) {
            JLabel label = new JLabel(valor.toString());
            label.setOpaque(true);
            jc = label;
            if (" som c1".equals((String) tabela.getModel().getValueAt(linha, 2))) {
                jc.setBackground(Color.YELLOW);
            } else {
                if (" som c2".equals((String) tabela.getModel().getValueAt(linha, 2))) {
                    jc.setBackground(Color.CYAN);
                } else {
                    if ((Double) tabela.getModel().getValueAt(linha, 3) > 100) {
                        jc.setForeground(Color.WHITE);
                        jc.setBackground(Color.BLACK);
                    } else {
                        jc.setBackground(tabela.getBackground());
                        jc.setForeground(tabela.getForeground());
                    }
                }
            }
        }
        return jc;
    }
}
