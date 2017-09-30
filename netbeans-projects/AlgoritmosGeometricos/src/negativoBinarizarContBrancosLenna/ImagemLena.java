package negativoBinarizarContBrancosLenna;

import com.sun.media.jai.widget.DisplayJAI;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.iterator.RandomIter;
import javax.media.jai.iterator.RandomIterFactory;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Caio Renan Hobus
 */
public class ImagemLena extends JFrame {

    private JPanel panelNorteEsq, panelNorteDir, panelSulEsq, panelSulDir;

    public ImagemLena() {
        super("Processamento imagem - Lena.jpg - Caio Renan Hobus");
        setLayout(new BorderLayout());

        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BorderLayout());
        panelNorte.setBorder(BorderFactory.createEtchedBorder());
        getContentPane().add(panelNorte, BorderLayout.NORTH);

        JPanel panelSul = new JPanel();
        panelSul.setLayout(new BorderLayout());
        panelSul.setBorder(BorderFactory.createEtchedBorder());
        getContentPane().add(panelSul, BorderLayout.CENTER);

        panelNorteEsq = new JPanel();
        panelNorteEsq.setLayout(new BorderLayout());
        panelNorteEsq.setBorder(BorderFactory.createEtchedBorder());
        panelNorteEsq.add(new JLabel("Imagem Original"), BorderLayout.NORTH);
        panelNorteEsq.add(new JLabel(new ImageIcon("lena.jpg")), BorderLayout.CENTER);
        panelNorte.add(panelNorteEsq, BorderLayout.WEST);

        panelNorteDir = new JPanel();
        panelNorteDir.setLayout(new BorderLayout());
        panelNorteDir.setBorder(BorderFactory.createEtchedBorder());
        panelNorteDir.add(new JLabel("Invert"), BorderLayout.NORTH);
        panelNorte.add(panelNorteDir, BorderLayout.CENTER);

        panelSulEsq = new JPanel();
        panelSulEsq.setLayout(new BorderLayout());
        panelSulEsq.setBorder(BorderFactory.createEtchedBorder());
        panelSulEsq.add(new JLabel("Binarizada"), BorderLayout.NORTH);
        panelSul.add(panelSulEsq, BorderLayout.WEST);

        panelSulDir = new JPanel();
        panelSulDir.setLayout(new BorderLayout());
        panelSulDir.setBorder(BorderFactory.createEtchedBorder());
        panelSulDir.add(new JLabel("Pixels brancos"), BorderLayout.NORTH);
        panelSul.add(panelSulDir, BorderLayout.CENTER);

        try {
            invert(new File("lena.jpg"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro no processamento da imagem", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void invert(File arquivoImagem) throws IOException {
        BufferedImage imagem = ImageIO.read(arquivoImagem);
        WritableRaster raster = imagem.getRaster();
        int[] pixel = new int[3];
        for (int h = 0; h < imagem.getHeight(); h++) {
            for (int w = 0; w < imagem.getWidth(); w++) {
                pixel[0] = 255 - raster.getPixel(h, w, pixel)[0];
                raster.setPixel(h, w, pixel);
            }
        }

        panelNorteDir.add(new JLabel(new ImageIcon(imagem)), BorderLayout.CENTER);

        binarizar(imagem);
    }

    private void binarizar(BufferedImage imagem) {
        ParameterBlock pb = new ParameterBlock();
        pb.addSource(imagem);
        pb.add(127.0);
        PlanarImage binarizada = JAI.create("binarize", pb);

        panelSulEsq.add(new DisplayJAI(binarizada), BorderLayout.CENTER);

        contBrancos(binarizada);
    }

    private void contBrancos(PlanarImage imagemBinarizada) {
        RandomIter iterator = RandomIterFactory.create(imagemBinarizada, null);
        int qtBrancos = 0;
        int[] pixel = new int[3];
        for (int h = 0; h < imagemBinarizada.getHeight(); h++) {
            for (int w = 0; w < imagemBinarizada.getWidth(); w++) {
                iterator.getPixel(h, w, pixel);
                if (pixel[0] == 1) {
                    qtBrancos++;
                }
            }
        }
        panelSulDir.add(new JLabel("                           Quantidade: " + qtBrancos), BorderLayout.CENTER);
    }
}
