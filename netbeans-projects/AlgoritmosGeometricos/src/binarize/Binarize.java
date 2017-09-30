package binarize;

import filtroImagemBola.DisplayTwoSynchronizedImages;
import java.awt.image.renderable.ParameterBlock;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Binarize {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "LookAndFeel não suportado", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        System.setProperty("com.sun.media.jai.disableMediaLib", "true");
        PlanarImage imagem = JAI.create("fileload", "lena.jpg");
        ParameterBlock pb = new ParameterBlock();
        pb.addSource(imagem);
        pb.add(127.0);
        PlanarImage binarizada = JAI.create("binarize", pb);
        JFrame frame = new JFrame();
        frame.setTitle("Binarize - lena.jpg");
        frame.getContentPane().add(new DisplayTwoSynchronizedImages(imagem, binarizada));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
