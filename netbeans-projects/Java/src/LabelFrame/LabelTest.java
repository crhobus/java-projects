package LabelFrame;

import javax.swing.JFrame;

public class LabelTest {

    public static void main(String[] args) {
        LabelFrame labelFrame = new LabelFrame();
        labelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        labelFrame.setSize(275, 180);
        labelFrame.setLocationRelativeTo(null);
        labelFrame.setVisible(true);
    }
}
