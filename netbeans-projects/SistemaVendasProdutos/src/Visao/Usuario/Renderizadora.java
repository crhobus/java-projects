package Visao.Usuario;

import java.awt.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class Renderizadora implements TableCellRenderer {

    private SimpleDateFormat sDataf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat sHoraf = new SimpleDateFormat("HH:mm");
    private String texto;
    private String pesquisa;

    public Renderizadora(String texto, String pesquisa) {
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
        if (valor.getClass() == Integer.class || valor.getClass() == String.class || valor.getClass() == Date.class) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }
        if (valor.getClass().equals(Date.class)) {
            if (coluna == 3) {
                label.setText(sDataf.format(valor).toString());
            } else {
                label.setText(sHoraf.format(valor).toString());
            }
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
        if (pesquisa.equals("Permissão")) {
            try {
                if ((Integer) tabela.getModel().getValueAt(linha, 2) == Integer.parseInt(texto)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }
        /*if (pesquisa.equals("Data")) {
            try {
                if ((Date) tabela.getModel().getValueAt(linha, 3) == Date.parse(nome)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }
        if (pesquisa.equals("Hora")) {
            try {
                if ((Date) tabela.getModel().getValueAt(linha, 3) == Date.parse(nome)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }*/
        return jc;
    }
}
