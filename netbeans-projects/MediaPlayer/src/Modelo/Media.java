package Modelo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;

public class Media {

    private File arquivo;
    private MediaConstante tipo;
    private String duracaoMedia;

    public Media(File arquivo, String descFilter) {
        this.arquivo = arquivo;
        if (descFilter.equalsIgnoreCase("Audio")) {
            tipo = MediaConstante.AUDIO;
        } else {
            if (descFilter.equalsIgnoreCase("Video")) {
                tipo = MediaConstante.VIDEO;
            } else {
                tipo = MediaConstante.IMAGEM;
            }
        }
        if (tipo.equals(MediaConstante.AUDIO) || tipo.equals(MediaConstante.VIDEO)) {
            try {
                SimpleDateFormat hora = new SimpleDateFormat("mm:ss");
                Player mediaPlayer = Manager.createRealizedPlayer(new URL("file:/" + arquivo.getAbsolutePath().replace("\\", "/")));
                this.duracaoMedia = hora.format((mediaPlayer.getDuration().getSeconds() * 1000));
            } catch (IOException ex) {
            } catch (NoPlayerException ex) {
            } catch (CannotRealizeException ex) {
            }
        } else {
            duracaoMedia = "00:00";
        }
    }

    public String getDuracao() {
        return duracaoMedia;
    }

    public String getNomeArq() {
        return arquivo.getName();
    }

    public String getTamArq() {
        return tamArq(arquivo.length());
    }

    public long getUltDataMod() {
        return arquivo.lastModified();
    }

    public String getEndereco() {
        return arquivo.getAbsolutePath();
    }

    public MediaConstante getTipoMedia() {
        return tipo;
    }

    public boolean isAudio() {
        return tipo.equals(MediaConstante.AUDIO);
    }

    public boolean isImagem() {
        return tipo.equals(MediaConstante.IMAGEM);
    }

    public boolean isVideo() {
        return tipo.equals(MediaConstante.VIDEO);
    }

    private String tamArq(long tam) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        if (tam < 1024) {
            return df.format(tam) + " bytes";
        } else {
            if (tam < 1048576) {
                return df.format(tam / (double) 1024) + " KB";
            } else {
                if (tam < 1073741824) {
                    return df.format((tam / (double) 1024) / (double) 1024) + " MB";
                } else {
                    return df.format(((tam / (double) 1024) / (double) 1024) / (double) 1024) + " GB";
                }
            }
        }
    }
}
