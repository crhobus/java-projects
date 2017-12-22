package Visao.Funcionarios;

import java.awt.*;
import java.text.*;
import javax.swing.*;
import javax.swing.table.*;

public class Renderizadora implements TableCellRenderer {

    private String texto;
    private String pesquisa;
    private NumberFormat nRealf = NumberFormat.getCurrencyInstance();

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
        if (pesquisa.equals("Data Admissão")) {
            if (tabela.getModel().getValueAt(linha, 2).equals(texto)) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Função")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 3))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("CPF")) {
            if (tabela.getModel().getValueAt(linha, 4).equals(texto)) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("RG")) {
            if (tabela.getModel().getValueAt(linha, 5).equals(texto)) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Sexo")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 6))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Endereço")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 7))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Número")) {
            try {
                if ((Integer) tabela.getModel().getValueAt(linha, 8) == Integer.parseInt(texto)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }
        if (pesquisa.equals("Bairro")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 9))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("CEP")) {
            if (tabela.getModel().getValueAt(linha, 10).equals(texto)) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Cidade")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 11))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Estado")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 12))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Complemento")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 13))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Fone")) {
            if (tabela.getModel().getValueAt(linha, 14).equals(texto)) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Celular")) {
            if (tabela.getModel().getValueAt(linha, 15).equals(texto)) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Data Nascimento")) {
            if (tabela.getModel().getValueAt(linha, 16).equals(texto)) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Salário")) {
            try {
                if ((Double) tabela.getModel().getValueAt(linha, 17) == Integer.parseInt(texto)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }
        if (pesquisa.equals("E-Mail")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 18))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        return jc;
    }
}
