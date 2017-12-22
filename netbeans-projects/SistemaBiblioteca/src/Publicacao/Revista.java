package Publicacao;

import java.util.Date;

import Agente.Editor;

public class Revista extends Publicacao {

    private Artigo[] artigos;

    public Revista(int codigo, String titulo, Date data, Editor editor, Artigo[] artigos) {
        setCodigo(codigo);
        setTitulo(titulo);
        setData(data);
        setEditor(editor);
        setArtigos(artigos);
    }

    public Artigo[] getArtigos() {
        return artigos;
    }

    public void setArtigos(Artigo[] artigos) {
        this.artigos = artigos;
    }
}
