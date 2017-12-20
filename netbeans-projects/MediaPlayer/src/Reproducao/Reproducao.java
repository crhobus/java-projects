package Reproducao;

import Modelo.Media;
import javax.swing.JLabel;

public abstract class Reproducao {

    public abstract void executar(Media media, JLabel lbNomeMedia, JLabel lbTamanhoMedia, JLabel lbImgPanel, Thread thread);

    public abstract void stop();

    public abstract void pause();

    public abstract void reiniciar();

    public void aumentarVol() {
    }

    public void diminuirVol() {
    }

    public void mudo(boolean mudo) {
    }
}
