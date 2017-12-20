package Controle;

import Modelo.Media;
import Modelo.MediaConstante;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controle {

    private List<Media> lista = new ArrayList<Media>();
    private static Controle controle;

    private Controle() {
    }

    public static Controle getInstance() {
        if (controle == null) {
            controle = new Controle();
        }
        return controle;
    }

    public void addLista(File arquivo, String descFilter) {
        Media media = new Media(arquivo, descFilter);
        lista.add(media);
    }

    public void remover(int linha) {
        lista.remove(linha);
    }

    public void limpar() {
        lista.clear();
    }

    public int tamanho() {
        return lista.size();
    }

    public Object conteudoLinha(int linha, int coluna) {
        Media media = lista.get(linha);
        switch (coluna) {
            case 0:
                return linha + 1;
            case 1:
                return media.getNomeArq();
            case 2:
                return media.getDuracao();
            case 3:
                return media.getTamArq();
            case 4:
                return media.getTipoMedia().equals(MediaConstante.AUDIO);
            case 5:
                return media.getTipoMedia().equals(MediaConstante.VIDEO);
            case 6:
                return media.getTipoMedia().equals(MediaConstante.IMAGEM);
            case 7:
                return media.getUltDataMod();
            default:
                return "remover";
        }
    }

    public Media mediaAtual(int i) {
        Media media = lista.get(i);
        return media;
    }

    public List<Media> getMedias() {
        return lista;
    }
}
