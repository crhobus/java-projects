package Setor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class Renderizadora implements TableCellRenderer {

    private String texto;
    private String pesquisa;

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
        if (valor.getClass() == String.class || valor.getClass() == Integer.class) {
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
        if (pesquisa.equals("Nome")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 1))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        return jc;
    }
}
