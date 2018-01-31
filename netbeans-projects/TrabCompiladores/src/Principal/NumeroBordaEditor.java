package Principal;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.AbstractBorder;

public class NumeroBordaEditor extends AbstractBorder {

    private static final long serialVersionUID = 1L;
    private int linha = 18;
    private int altura = 8;
    private int largura = 7;

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int l, int altura) {
        int linhas = (int) (((double) altura / (double) this.linha) + 0.5);
        String str = "";
        for (int i = 0; i < linhas; i++) {
            str = String.valueOf(i + 1);
            g.drawString(str, this.largura * ((String.valueOf(linhas).length()) - str.length()) + 2, this.linha * i + 14);
        }
        g.setColor(g.getColor());
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(1, this.calculoEsquerda(c.getHeight()) + 10, 1, 1);
    }

    private int calculoEsquerda(int altura) {
        return this.altura * (String.valueOf((int) (((double) altura / (double) this.linha) + 0.5)).length());
    }
}
