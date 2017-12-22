package Publicacao;

import java.util.Date;

import Agente.Autor;
import Agente.Editor;

public class Livro extends Publicacao {

    private Autor[] autores;

    public Livro(int codigo, String titulo, Date data, Editor editor, Autor[] autores) {
        setCodigo(codigo);
        setTitulo(titulo);
        setData(data);
        setEditor(editor);
        setAutores(autores);
    }

    public Autor[] getAutores() {
        return autores;
    }

    public void setAutores(Autor[] autores) {
        this.autores = autores;
    }
}
