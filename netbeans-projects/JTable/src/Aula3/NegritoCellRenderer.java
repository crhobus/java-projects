package Aula3;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class NegritoCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable tabela,
            Object valor, // que vem do getValueAt
            boolean selecionado,
            boolean temFoco,
            int linha, int col) {

        JLabel l = (JLabel) super.getTableCellRendererComponent(tabela, valor, selecionado, temFoco, linha, col);
        Font fonte = l.getFont();
        Font novaFonte = fonte.deriveFont(Font.BOLD);
        l.setFont(novaFonte);
        l.setBackground(Color.YELLOW);
        l.setForeground(Color.RED);

        if (valor.getClass() == Integer.class) {
            l.setHorizontalAlignment(
                    SwingConstants.RIGHT);
        } else {
            l.setHorizontalAlignment(
                    SwingConstants.LEFT);
        }

        return this;
    }
}






