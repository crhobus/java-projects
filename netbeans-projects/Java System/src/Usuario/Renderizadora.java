package Usuario;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class Renderizadora implements TableCellRenderer {

    private SimpleDateFormat sDataf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat sHoraf = new SimpleDateFormat("HH:mm");
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
        if (valor.getClass() == String.class || valor.getClass() == Integer.class || valor.getClass() == Date.class) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }
        jc.setBackground(Color.WHITE);
        valor = tabela.getModel().getValueAt(linha, coluna);
        if (valor.getClass().equals(Date.class)) {
            if (coluna == 3) {
                label.setText(sDataf.format(valor).toString());
            } else {
                label.setText(sHoraf.format(valor).toString());
            }
        }
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
        if (pesquisa.equals("Permiss�o")) {
            try {
                if ((Integer) tabela.getModel().getValueAt(linha, 2) == Integer.parseInt(texto)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }
        if (pesquisa.equals("Data")) {
            try {
                if (sDataf.format((Date) tabela.getModel().getValueAt(linha, 3)).equals(sDataf.format(sDataf.parse(texto)))) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (ParseException ex) {
            }
        }
        if (pesquisa.equals("Hora")) {
            try {
                if (sHoraf.format((Date) tabela.getModel().getValueAt(linha, 4)).equals(sHoraf.format(sHoraf.parse(texto)))) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (ParseException ex) {
            }
        }
        return jc;
    }
}
