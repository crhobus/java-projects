package invert;

import filtroImagemBola.DisplayTwoSynchronizedImages;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Invert {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "LookAndFeel não suportado", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        System.setProperty("com.sun.media.jai.disableMediaLib", "true");
        PlanarImage input = JAI.create("fileload", "lena.jpg");
        PlanarImage output = JAI.create("invert", input);
        JFrame frame = new JFrame();
        frame.setTitle("Invert - lena.jpg");
        frame.getContentPane().add(new DisplayTwoSynchronizedImages(input, output));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
