package Visao.Contatos;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;

public class Renderizadora implements TableCellRenderer {

    private String texto, pesquisa;

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
        if (coluna == 4 || coluna == 5 || coluna == 6) {
            try {
                MaskFormatter mascara = new MaskFormatter("(##)####-####");
                JFormattedTextField ftfCampo = new JFormattedTextField(mascara);
                ftfCampo.setBorder(null);
                if (valor != null) {
                    ftfCampo.setText(valor.toString());
                }
                jc = ftfCampo;
            } catch (Exception ex) {
            }
        }
        if (valor.getClass() == String.class || valor.getClass() == Integer.class) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }
        if (valor.getClass() == Boolean.class) {
            JCheckBox check = new JCheckBox();
            check.setSelected((Boolean) valor);
            check.setHorizontalAlignment(SwingConstants.CENTER);
            jc = check;
        }
        if (coluna == 3) {
            JPanel painel = new JPanel();
            JCheckBox check1 = new JCheckBox();
            check1.setBackground(Color.WHITE);
            painel.add(check1);
            JCheckBox check2 = new JCheckBox();
            check2.setBackground(Color.WHITE);
            painel.add(check2);
            JCheckBox check3 = new JCheckBox();
            check3.setBackground(Color.WHITE);
            painel.add(check3);
            Boolean[] v = (Boolean[]) valor;
            check1.setSelected(v[0]);
            check2.setSelected(v[1]);
            check3.setSelected(v[2]);
            jc = painel;
        }
        if (coluna == 8) {
            JPanel painel = new JPanel(new GridLayout());
            Icon figura = new ImageIcon("botaoRemover.png");//imagem adicionar
            JButton btRemover = new JButton(figura);
            painel.add(btRemover);
            jc = painel;
        }
        if ((Boolean) tabela.getModel().getValueAt(linha, 0) == true) {
            jc.setBackground(Color.CYAN);
        } else {
            jc.setBackground(Color.WHITE);
        }
        if (pesquisa.equals("Codigo")) {
            try {
                if ((Integer) tabela.getModel().getValueAt(linha, 1) == Integer.parseInt(texto)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }
        if (pesquisa.equals("Nome")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 2))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Fone 1")) {
            if (texto.equals((String) tabela.getModel().getValueAt(linha, 4))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Fone 2")) {
            if (texto.equals((String) tabela.getModel().getValueAt(linha, 5))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Fone 3")) {
            if (texto.equals((String) tabela.getModel().getValueAt(linha, 6))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("E-Mail")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 7))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        return jc;
    }
}
