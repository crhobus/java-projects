package filtroImagemBola;

import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.swing.JFrame;

/**
 *
 * @author Caio Renan Hobus
 */
public class Sistema {

    public static void main(String[] args) {
        System.setProperty("com.sun.media.jai.disableMediaLib", "true");
        PlanarImage imagem = JAI.create("fileload", "Bola.jpg");
        float[] kernelMatrix = {1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f,
                                1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f,
                                1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f,
                                1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f,
                                1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f,
                                1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f,
                                1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f, 1f/49f};
        KernelJAI kernel = new KernelJAI(7, 7, kernelMatrix);
        PlanarImage bordas = JAI.create("convolve", imagem, kernel);
        JFrame frame = new JFrame("Processamento imagem - Bola.jpg - Caio Renan Hobus");
        frame.add(new DisplayTwoSynchronizedImages(imagem, bordas));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
