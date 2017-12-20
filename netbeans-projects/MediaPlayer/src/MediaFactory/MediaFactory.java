package MediaFactory;

import Modelo.Media;
import Reproducao.Reproducao;
import javax.swing.JLabel;

public abstract class MediaFactory {

    private Reproducao reproducao;

    public abstract Reproducao createMedia(Media media, JLabel lbNomeMedia, JLabel lbTamanhoMedia, JLabel lbImgPanel, Thread thread);

    public void executarMedia(Media media, JLabel lbNomeMedia, JLabel lbTamanhoMedia, JLabel lbImgPanel, Thread thread) {
        reproducao = createMedia(media, lbNomeMedia, lbTamanhoMedia, lbImgPanel, thread);
        reproducao.executar(media, lbNomeMedia, lbTamanhoMedia, lbImgPanel, thread);
    }

    public void stop() {
        reproducao.stop();
    }

    public void pause() {
        reproducao.pause();
    }

    public void reiniciar() {
        reproducao.reiniciar();
    }

    public void aumentarVol() {
        reproducao.aumentarVol();
    }

    public void diminuirVol() {
        reproducao.diminuirVol();
    }

    public void mudo(boolean mudo) {
        reproducao.mudo(mudo);
    }
}
