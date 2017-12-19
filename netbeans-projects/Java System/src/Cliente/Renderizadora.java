package Cliente;

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
        if (pesquisa.equals("RG")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 2))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("CPF")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 3))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Profissão")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 4))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Empresa")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 5))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Fone Empresa")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 6))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Sexo")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 7))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("CEP")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 8))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Endereço")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 9))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Bairro")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 10))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Numero")) {
            try {
                if ((Integer) tabela.getModel().getValueAt(linha, 11) == Integer.parseInt(texto)) {
                    jc.setBackground(Color.CYAN);
                } else {
                    jc.setBackground(Color.WHITE);
                }
            } catch (Exception ex) {
            }
        }
        if (pesquisa.equals("Complemento")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 12))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Cidade")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 13))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Estado")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 14))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Fone")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 15))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Celular")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 16))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("E-Mail")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 17))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        if (pesquisa.equals("Descrição")) {
            if (texto.equalsIgnoreCase((String) tabela.getModel().getValueAt(linha, 18))) {
                jc.setBackground(Color.CYAN);
            } else {
                jc.setBackground(Color.WHITE);
            }
        }
        return jc;
    }
}
