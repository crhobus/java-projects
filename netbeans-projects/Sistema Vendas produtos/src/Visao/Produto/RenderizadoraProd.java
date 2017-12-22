package Visao.Produto;

import java.awt.*;
import java.text.*;
import javax.swing.*;
import javax.swing.table.*;

public class RenderizadoraProd implements TableCellRenderer {

    private String texto;
    private String pesquisa;
    private NumberFormat nRealf = NumberFormat.getCurrencyInstance();

    public RenderizadoraProd(String texto, String pesquisa) {
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
        if (valor.getClass() == String.class || valor.getClass() == Integer.class || valor.getClass() == Double.class) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }
        if (valor.getClass() == Double.class) {
            label.setText(nRealf.format(valor).toString());
        }
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
        if (pesquisa.equals("Nome")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 1))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Descrição")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 2))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Num. Série")) {
            try {
                if ((Integer) tabela.getModel().getValueAt(linha, 3) == Integer.parseInt(texto)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }
        if (pesquisa.equals("Modelo")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 4))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Valor")) {
            try {
                if ((Double) tabela.getModel().getValueAt(linha, 5) == Double.parseDouble(texto)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }
        return jc;
    }
}
