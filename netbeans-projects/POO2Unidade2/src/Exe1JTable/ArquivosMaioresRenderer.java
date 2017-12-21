package Exe1JTable;

import java.awt.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class ArquivosMaioresRenderer implements TableCellRenderer {

    private long limiteTam;

    public ArquivosMaioresRenderer(long limiteTam) {
        this.limiteTam = limiteTam;
    }

    public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado, boolean temfocus, int linha, int coluna) {
        Long tamanho = (Long) tabela.getModel().getValueAt(linha, 2);
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

        if (tamanho > limiteTam) {
            jc.setBackground(new Color(210, 240, 245));
        } else {
            jc.setBackground(tabela.getBackground());
        }

        if (valor.getClass() == Long.class) {
            label.setHorizontalAlignment(SwingConstants.RIGHT);
        }

        if (valor.getClass() == Date.class) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            label.setText(sdf.format((Date) valor));
        }
        return jc;
    }
}

