package CopiaTexto;

import javax.swing.JFrame;

public class TextAreaDemo {

    public static void main(String[] args) {
        CopiaTexto copiaTexto = new CopiaTexto();
        copiaTexto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        copiaTexto.setSize(425, 200);
        copiaTexto.setLocationRelativeTo(null);
        copiaTexto.setVisible(true);
    }
}
