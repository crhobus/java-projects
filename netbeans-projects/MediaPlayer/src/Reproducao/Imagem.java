package Reproducao;

import Modelo.Media;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;

public class Imagem extends Reproducao {

    private Thread thread;

    @Override
    public void executar(Media media, JLabel lbNomeMedia, JLabel lbTamanhoMedia, JLabel lbImgPanel, Thread thread) {
        this.thread = thread;
        lbNomeMedia.setText(media.getNomeArq());
        lbTamanhoMedia.setText(media.getTamArq());
        try {
            lbImgPanel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
                    new URL("file:/" + media.getEndereco().replace("\\", "/"))).getScaledInstance(675, 350, Image.SCALE_DEFAULT)));
        } catch (MalformedURLException ex) {
        }
        try {
            thread.sleep(7000);
        } catch (InterruptedException ex) {
        }
    }

    @Override
    public void stop() {
        thread.interrupt();
    }

    @Override
    public void pause() {
        thread.suspend();
    }

    @Override
    public void reiniciar() {
        thread.resume();
    }
}
