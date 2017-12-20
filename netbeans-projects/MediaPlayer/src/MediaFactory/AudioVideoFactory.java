package MediaFactory;

import Modelo.Media;
import Reproducao.AudioVideo;
import Reproducao.Reproducao;
import javax.swing.JLabel;

public class AudioVideoFactory extends MediaFactory {

    @Override
    public Reproducao createMedia(Media media, JLabel lbNomeMedia, JLabel lbTamanhoMedia, JLabel lbImgPanel, Thread thread) {
        return new AudioVideo();
    }
}
