package ImagemProxy;

import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImagemProxy implements Icon {

    private ImageIcon imageIcon;
    private URL imageURL;
    private Thread recuperacaoThread;
    private boolean recuperacao = false;

    public ImagemProxy(URL url) {
        imageURL = url;
    }

    public void paintIcon(final Component c, Graphics g, int x, int y) {
        if (imageIcon != null) {
            imageIcon.paintIcon(c, g, x, y);
        } else {
            g.drawString("Carregando imagem, por favor aguarde ...", x + 300, y + 190);
            if (!recuperacao) {
                recuperacao = true;
                recuperacaoThread = new Thread(new Runnable() {

                    public void run() {
                        try {
                            imageIcon = new ImageIcon(imageURL, "Imagem");
                            c.repaint();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                recuperacaoThread.start();
            }
        }
    }

    public int getIconWidth() {
        if (imageIcon != null) {
            return imageIcon.getIconWidth();
        } else {
            return 800;
        }
    }

    public int getIconHeight() {
        if (imageIcon != null) {
            return imageIcon.getIconHeight();
        } else {
            return 600;
        }
    }
}
