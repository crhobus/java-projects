package Principal;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImgFundo extends JPanel {

    private Image imgFundo;
    private BufferedImage dimenssaoImg;
    private int height = -1;
    private int width = -1;

    public ImgFundo(String img) {
        imgFundo = new ImageIcon(img).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (height != getHeight() || width != getWidth()) {
            criarDimeImg();
        }
        g.drawImage(dimenssaoImg, 0, 0, this);
    }

    private void criarDimeImg() {
        width = getWidth();
        height = getHeight();
        if (width > 0 && height > 0) {
            dimenssaoImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            dimenssaoImg.createGraphics().drawImage(imgFundo, AffineTransform.getScaleInstance(((float) width) / imgFundo.getWidth(this), ((float) height) / imgFundo.getHeight(this)), null);
        } else {
            dimenssaoImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        }
    }
}