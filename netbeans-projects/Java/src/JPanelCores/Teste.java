package JPanelCores;

import javax.swing.JFrame;

public class Teste {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Using colors");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanelCores jPanelCores = new JPanelCores();
        frame.add(jPanelCores);
        frame.setSize(400, 180);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
