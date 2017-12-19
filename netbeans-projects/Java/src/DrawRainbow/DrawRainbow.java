package DrawRainbow;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawRainbow extends JPanel {

    private final Color VIOLET = new Color(128, 0, 128);
    private final Color INDIGO = new Color(75, 0, 130);
    private Color colors[] = {Color.WHITE, Color.WHITE, VIOLET, INDIGO, Color.BLUE, Color.GREEN,
        Color.YELLOW, Color.ORANGE, Color.RED};

    public DrawRainbow() {
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int radius = 20;
        int centerX = getWidth() / 2;
        int centerY = getHeight() - 10;
        for (int cont = colors.length; cont > 0; cont--) {
            g.setColor(colors[cont - 1]);
            g.fillArc(centerX - cont * radius, centerY - cont * radius, cont * radius * 2, cont * radius * 2, 0, 180);
        }
    }
}
