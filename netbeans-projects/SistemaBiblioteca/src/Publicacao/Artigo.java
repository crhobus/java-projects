package Publicacao;

import java.util.Date;

import Agente.Autor;
import Agente.Editor;

public class Artigo extends Publicacao {

    private Autor[] autores;
    private String[] palavrasChave;

    public Artigo(int codigo, String titulo, Date data, Editor editor, Autor[] autores, String[] palavraChave) {
        setCodigo(codigo);
        setTitulo(titulo);
        setData(data);
        setEditor(editor);
        setAutores(autores);
        setPalavrasChave(palavraChave);
    }

    public Autor[] getAutores() {
        return autores;
    }

    public void setAutores(Autor[] autores) {
        this.autores = autores;
    }

    public String[] getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(String[] palavrasChave) {
        this.palavrasChave = palavrasChave;
    }
}
