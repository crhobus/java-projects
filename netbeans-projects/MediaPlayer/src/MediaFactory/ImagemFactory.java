package MediaFactory;

import Modelo.Media;
import Reproducao.Imagem;
import Reproducao.Reproducao;
import javax.swing.JLabel;

public class ImagemFactory extends MediaFactory {

    @Override
    public Reproducao createMedia(Media media, JLabel lbNomeMedia, JLabel lbTamanhoMedia, JLabel lbImgPanel, Thread thread) {
        return new Imagem();
    }
}
