package Vendedor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.NumberFormat;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class Renderizadora implements TableCellRenderer {

    private String texto;
    private String pesquisa;
    private NumberFormat nRealf = NumberFormat.getCurrencyInstance();

    public Renderizadora(String texto, String pesquisa) {
        this.texto = texto;
        this.pesquisa = pesquisa;
    }

    @Override
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
        if (coluna == 5) {
            label.setText(nRealf.format(valor).toString());
            label.setHorizontalAlignment(SwingConstants.CENTER);
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
        if (pesquisa.equals("CPF")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 1))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("E-Mail Vendedor")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 2))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Home Page")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 3))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Comissão")) {
            try {
                if ((Double) tabela.getModel().getValueAt(linha, 4) == Double.parseDouble(texto)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }
        if (pesquisa.equals("Salário")) {
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
