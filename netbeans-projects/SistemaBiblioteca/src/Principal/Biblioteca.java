package Principal;

import java.util.List;

import Agente.Autor;
import Agente.Editor;
import ArquivosDAO.RepositorioDadosDAO;
import Publicacao.Artigo;
import Publicacao.Livro;
import Publicacao.Publicacao;
import Publicacao.Revista;

public class Biblioteca {

    private static Biblioteca biblioteca;
    private RepositorioDadosDAO dadosDAO;

    private Biblioteca() {
        dadosDAO = new RepositorioDadosDAO();
    }

    public static Biblioteca getInstance() {
        if (biblioteca == null) {
            biblioteca = new Biblioteca();
        }
        return biblioteca;
    }

    public void addAutor(Autor autor) {
        dadosDAO.addAutor(autor);
    }

    public Autor findAutor(int codigo) {
        return dadosDAO.findAutor(codigo);
    }

    public void addEditor(Editor editor) {
        dadosDAO.addEditor(editor);
    }

    public Editor findEditor(int codigo) {
        return dadosDAO.findEditor(codigo);
    }

    public void addArtigo(Artigo artigo) {
        dadosDAO.addArtigo(artigo);
    }

    public Artigo findArtigo(int codigo) {
        return dadosDAO.findArtigo(codigo);
    }

    public void addLivro(Livro livro) {
        dadosDAO.addLivro(livro);
    }

    public Livro findLivro(int codigo) {
        return dadosDAO.findLivro(codigo);
    }

    public void addRevista(Revista revista) {
        dadosDAO.addRevista(revista);
    }

    public Revista findRevista(int codigo) {
        return dadosDAO.findRevista(codigo);
    }

    public void removePub(int codigo) {
        dadosDAO.removePub(codigo);
    }

    public void removeAgente(int codigo) {
        dadosDAO.removeAgente(codigo);
    }

    public List<Publicacao> findPubs(String titulo) {
        return dadosDAO.findPubs(titulo);
    }
}
