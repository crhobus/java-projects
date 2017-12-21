package Fractais;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class FractalJPanel extends JPanel {

    private Color cor;
    private int nivel;
    private final int largura = 400;
    private final int altura = 400;

    public FractalJPanel(int nivelAtual) {
        cor = Color.BLUE;
        this.nivel = nivelAtual;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(largura, altura));
    }

    public void desenharFractal(int nivel, int xA, int yA, int xB, int yB, Graphics g) {
        if (nivel == 0) {
            g.drawLine(xA, yA, xB, yB);
        } else {
            int xC = (xA + xB) / 2;
            int yC = (yA + yB) / 2;
            int xD = xA + (xC - xA) / 2 - (yC - yA) / 2;
            int yD = yA + (yC - yA) / 2 - (xC - xA) / 2;
            desenharFractal(nivel - 1, xD, yD, xA, yA, g);
            desenharFractal(nivel - 1, xD, yD, xC, yC, g);
            desenharFractal(nivel - 1, xD, yD, xB, yB, g);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(cor);
        desenharFractal(nivel, 100, 90, 290, 200, g);
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
