package Imagem;

import java.awt.Color;
import javax.swing.JFrame;

public class ImagemTest {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Drawing 2D Shapes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Imagem imagem = new Imagem();
        frame.add(imagem);
        frame.setBackground(Color.WHITE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
