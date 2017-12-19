package Fornecedor;

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
        if (valor.getClass() == String.class || valor.getClass() == Integer.class || valor.getClass() == Double.class) {
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
        if (pesquisa.equals("Inscrição Estadual")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 2))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("CNPJ")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 3))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Inscrição Municipal")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 4))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Tipo Fornecedor")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 5))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Endereço")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 6))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Bairro")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 7))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Numero")) {
            try {
                if ((Integer) tabela.getModel().getValueAt(linha, 8) == Integer.parseInt(texto)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }
        if (pesquisa.equals("Cidade")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 9))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Estado")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 10))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Região")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 11))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Fone")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 12))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Celular")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 13))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("E-Mail")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 14))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("MSN")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 15))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Descrição")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 16))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Comissão")) {
            try {
                if ((Double) tabela.getModel().getValueAt(linha, 17) == Double.parseDouble(texto)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }
        if (pesquisa.equals("Compra Mínima")) {
            try {
                if ((Double) tabela.getModel().getValueAt(linha, 18) == Double.parseDouble(texto)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }
        if (pesquisa.equals("Compra Máxima")) {
            try {
                if ((Double) tabela.getModel().getValueAt(linha, 19) == Double.parseDouble(texto)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }
        if (pesquisa.equals("Valor Frete")) {
            try {
                if ((Double) tabela.getModel().getValueAt(linha, 20) == Double.parseDouble(texto)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }
        if (pesquisa.equals("ICMS")) {
            try {
                if ((Double) tabela.getModel().getValueAt(linha, 21) == Double.parseDouble(texto)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }
        if (pesquisa.equals("Cofins")) {
            try {
                if ((Double) tabela.getModel().getValueAt(linha, 22) == Double.parseDouble(texto)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }
        if (pesquisa.equals("IPI")) {
            try {
                if ((Double) tabela.getModel().getValueAt(linha, 23) == Double.parseDouble(texto)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }
        if (pesquisa.equals("Juros a.m.")) {
            try {
                if ((Double) tabela.getModel().getValueAt(linha, 24) == Double.parseDouble(texto)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }
        if (pesquisa.equals("Descontos")) {
            try {
                if ((Double) tabela.getModel().getValueAt(linha, 25) == Double.parseDouble(texto)) {
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
