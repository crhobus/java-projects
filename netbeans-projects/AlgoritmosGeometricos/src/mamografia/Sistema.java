package mamografia;

import filtroImagemBola.DisplayTwoSynchronizedImages;
import java.awt.image.renderable.ParameterBlock;
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
        PlanarImage imagem = JAI.create("fileload", "mama6.png");

        float[] mascara = {0, 0, 0, 0, 0,
                           0, 1, 1, 1, 0,
                           0, 1, 1, 1, 0,
                           0, 1, 1, 1, 0,
                           0, 0, 0, 0, 0};
        KernelJAI kernel = new KernelJAI(5, 5, mascara);

        //Abertura
        PlanarImage abertErode = JAI.create("erode", imagem, kernel);
        PlanarImage abertdilate = JAI.create("dilate", abertErode, kernel);

        //fechamento
        PlanarImage fechadilate = JAI.create("dilate", imagem, kernel);
        PlanarImage fechaerode = JAI.create("erode", fechadilate, kernel);

        //subtração
        PlanarImage subtract = JAI.create("subtract", fechaerode, abertdilate);

        ParameterBlock pb = new ParameterBlock();
        pb.addSource(subtract);
        pb.add(35.0);
        PlanarImage binarizada = JAI.create("binarize", pb);

        JFrame frame = new JFrame("Processamento imagem - Bola.jpg - Caio Renan Hobus");
        frame.add(new DisplayTwoSynchronizedImages(imagem, binarizada));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
