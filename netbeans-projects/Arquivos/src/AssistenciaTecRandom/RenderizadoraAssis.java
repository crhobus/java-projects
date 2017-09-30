package AssistenciaTecRandom;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class RenderizadoraAssis implements TableCellRenderer {

    private String texto;
    private String pesquisa;

    public RenderizadoraAssis(String texto, String pesquisa) {
        this.texto = texto;
        this.pesquisa = pesquisa;
    }

    public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado, boolean temFocus, int linha, int coluna) {
        JComponent jc = null;
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        JLabel label = new JLabel(valor.toString());
        label.setFont(fonte);
        label.setOpaque(true);
        jc = label;
        if (valor.getClass() == String.class || valor.getClass() == Integer.class || valor.getClass() == Boolean.class || valor.getClass() == Double.class) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }
        if (valor.getClass() == Boolean.class) {
            JCheckBox check = new JCheckBox();
            check.setSelected((Boolean) valor);
            check.setHorizontalAlignment(SwingConstants.CENTER);
            jc = check;
        }
        if (coluna == 20) {
            JButton button = new JButton("Salvar");
            button.setHorizontalAlignment(SwingConstants.CENTER);
            jc = button;
        } else {
            jc.setBackground(Color.WHITE);
            if (pesquisa.equals("Codigo")) {
                try {
                    if ((Integer) tabela.getModel().getValueAt(linha, 0) == Integer.parseInt(texto)) {
                        jc.setBackground(Color.CYAN);
                    } else {
                        jc.setBackground(Color.WHITE);
                    }
                } catch (Exception ex) {
                }
            }
            if (pesquisa.equals("Cod. Clie")) {
                try {
                    if ((Integer) tabela.getModel().getValueAt(linha, 1) == Integer.parseInt(texto)) {
                        jc.setBackground(Color.CYAN);
                    } else {
                        jc.setBackground(Color.WHITE);
                    }
                } catch (Exception ex) {
                }
            }
        }
        return jc;
    }
}
