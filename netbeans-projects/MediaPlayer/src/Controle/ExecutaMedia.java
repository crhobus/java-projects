package Controle;

import MediaFactory.AudioVideoFactory;
import MediaFactory.ImagemFactory;
import MediaFactory.MediaFactory;
import Modelo.Media;
import javax.swing.JLabel;

public class ExecutaMedia {

    private static ExecutaMedia executaMedia;
    private MediaFactory factory;
    private final MediaFactory[] factories = {new AudioVideoFactory(), new ImagemFactory()};

    private ExecutaMedia() {
    }

    public static ExecutaMedia getInstance() {
        if (executaMedia == null) {
            executaMedia = new ExecutaMedia();
        }
        return executaMedia;
    }

    public void executar(int tipo, Media media, JLabel lbNomeMedia, JLabel lbTamanhoMedia, JLabel lbImgPanel, Thread thread) {
        factory = factories[tipo];
        factory.executarMedia(media, lbNomeMedia, lbTamanhoMedia, lbImgPanel, thread);
    }

    public void stop() {
        if (factory != null) {
            factory.stop();
        }
    }

    public void pause() {
        if (factory != null) {
            factory.pause();
        }
    }

    public void reiniciar() {
        if (factory != null) {
            factory.reiniciar();
        }
    }

    public void aumentarVol() {
        if (factory != null) {
            factory.aumentarVol();
        }
    }

    public void diminuirVol() {
        if (factory != null) {
            factory.diminuirVol();
        }
    }

    public void mudo(boolean mudo) {
        if (factory != null) {
            factory.mudo(mudo);
        }
    }
}
