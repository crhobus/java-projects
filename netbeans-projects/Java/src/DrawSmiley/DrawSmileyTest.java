package DrawSmiley;

import javax.swing.JFrame;

public class DrawSmileyTest {

    public static void main(String[] args) {
        DrawSmiley painel = new DrawSmiley();
        JFrame application = new JFrame();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.add(painel);
        application.setSize(230, 250);
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}
