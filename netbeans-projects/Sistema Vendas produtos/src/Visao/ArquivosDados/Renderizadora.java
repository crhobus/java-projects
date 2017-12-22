package Visao.ArquivosDados;

import java.awt.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class Renderizadora implements TableCellRenderer {

    public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado, boolean temFocus, int linha, int coluna) {
        JComponent jc = null;
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        JLabel label = new JLabel(valor.toString());
        label.setFont(fonte);
        label.setOpaque(true);
        jc = label;
        if (valor.getClass() == Boolean.class) {
            JCheckBox check = new JCheckBox();
            check.setSelected((Boolean) valor);
            check.setHorizontalAlignment(SwingConstants.CENTER);
            jc = check;
        }
        if (valor.getClass() == Date.class) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            label.setText(sdf.format((Date) valor));
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }
        if (linha % 2 == 0) {
            jc.setBackground(new Color(210, 240, 245));
        } else {
            jc.setBackground(Color.WHITE);
        }
        return jc;
    }
}
