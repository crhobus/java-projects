package Reproducao;

import Modelo.Media;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.media.*;
import javax.swing.*;

public class AudioVideo extends Reproducao {

    private Player mediaPlayer;
    private Component component, video;
    private Thread thread;

    @Override
    public void executar(Media media, JLabel lbNomeMedia, JLabel lbTamanhoMedia, JLabel lbImgPanel, final Thread thread) {
        this.thread = thread;
        lbNomeMedia.setText(media.getNomeArq());
        lbTamanhoMedia.setText(media.getTamArq());
        Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, true);
        try {
            mediaPlayer = Manager.createRealizedPlayer(new URL("file:/" + media.getEndereco().replace("\\", "/")));
            component = mediaPlayer.getControlPanelComponent();
            video = mediaPlayer.getVisualComponent();
            if (video != null) {
                video.setBounds(2, 2, 671, 316);
                lbImgPanel.add(video);
            }
            if (component != null) {
                component.setBounds(3, 317, 670, 30);
                lbImgPanel.add(component);
            }
            mediaPlayer.start();
            mediaPlayer.addControllerListener(new ControllerListener() {

                public void controllerUpdate(ControllerEvent evento) {
                    if (mediaPlayer != null) {
                        if (mediaPlayer.getMediaTime().getSeconds() >= mediaPlayer.getDuration().getSeconds()) {
                            thread.resume();
                        }
                    }
                }
            });
            thread.suspend();
            stop();
        } catch (NoPlayerException ex) {
            JOptionPane.showMessageDialog(null, "Formato de arquivo não suportado", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (CannotRealizeException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar operação", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            if (component != null) {
                component.setVisible(false);
            }
            if (video != null) {
                video.setVisible(false);
            }
            thread.interrupt();
        }
    }

    @Override
    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    @Override
    public void reiniciar() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    @Override
    public void aumentarVol() {
        if (mediaPlayer.getGainControl().getLevel() >= 0 && mediaPlayer.getGainControl().getLevel() <= 0.9) {
            mediaPlayer.getGainControl().setLevel((float) (mediaPlayer.getGainControl().getLevel() + 0.1));
        }
    }

    @Override
    public void diminuirVol() {
        if (mediaPlayer.getGainControl().getLevel() <= 1 && mediaPlayer.getGainControl().getLevel() >= 0.1) {
            mediaPlayer.getGainControl().setLevel((float) (mediaPlayer.getGainControl().getLevel() - 0.1));
        }
    }

    @Override
    public void mudo(boolean mudo) {
        mediaPlayer.getGainControl().setMute(mudo);
    }
}
