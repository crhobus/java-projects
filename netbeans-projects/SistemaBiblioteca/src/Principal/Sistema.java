package Principal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;

import Agente.Autor;
import Agente.Editor;
import Publicacao.Artigo;
import Publicacao.Livro;
import Publicacao.Publicacao;
import Publicacao.Revista;

public class Sistema {

    private static SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        Biblioteca biblioteca = Biblioteca.getInstance();

        Autor autor1 = new Autor(1, "Caio Renan", "Hobus");
        Autor autor2 = new Autor(2, "Morais", "Gonsalves");
        Autor autor3 = new Autor(3, "Marcelo", "Silva");
        Autor autor4 = new Autor(4, "Marta", "Morais");
        Autor autor5 = new Autor(5, "Thiago", "Matos");
        Autor autor6 = new Autor(6, "Luiza", "Correa");

        Editor editor1 = new Editor(7, "Ana");
        Editor editor2 = new Editor(8, "Marcelo");
        Editor editor3 = new Editor(9, "Hobus");
        Editor editor4 = new Editor(10, "Renan");
        Editor editor5 = new Editor(11, "Elias");
        Editor editor6 = new Editor(12, "Marcos");

        biblioteca.addAutor(autor1);
        biblioteca.addAutor(autor2);
        biblioteca.addAutor(autor3);
        biblioteca.addAutor(autor4);
        biblioteca.addAutor(autor5);
        biblioteca.addAutor(autor6);
        biblioteca.addEditor(editor1);
        biblioteca.addEditor(editor2);
        biblioteca.addEditor(editor3);
        biblioteca.addEditor(editor4);
        biblioteca.addEditor(editor5);
        biblioteca.addEditor(editor6);

        Autor[] autores1 = {autor1, autor2, autor3};
        String[] palavraChave1 = {"w", "u", "k"};
        Artigo artigo1 = null;
        try {
            artigo1 = new Artigo(1, "tal", formatDate.parse("07/10/2010"), editor1, autores1, palavraChave1);
            biblioteca.addArtigo(artigo1);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro na converção da data", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        Autor[] autores2 = {autor3, autor5, autor6};
        String[] palavraChave2 = {"dvkjs", "dkhsbvkj", "vdhmcbdj"};
        Artigo artigo2 = null;
        try {
            artigo2 = new Artigo(2, "uu", formatDate.parse("11/10/2009"), editor4, autores2, palavraChave2);
            biblioteca.addArtigo(artigo2);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro na converção da data", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        Autor[] autores3 = {autor1, autor4, autor5};
        try {
            biblioteca.addLivro(new Livro(3, "bj", formatDate.parse("12/09/2010"), editor5, autores3));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro na converção da data", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        Artigo[] artigos = {artigo1, artigo2};
        try {
            biblioteca.addRevista(new Revista(4, "tal", formatDate.parse("16/04/2008"), editor2, artigos));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro na converção da data", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        Autor autorLido1 = biblioteca.findAutor(1);
        imprimeAutor(autorLido1);

        System.out.println("----------------------------------------------");

        Editor editorLido1 = biblioteca.findEditor(8);
        imprimeEditor(editorLido1);

        System.out.println("----------------------------------------------");

        Artigo artigoLido1 = biblioteca.findArtigo(1);
        imprimeArtigo(artigoLido1);

        System.out.println("----------------------------------------------");

        Artigo artigoLido2 = biblioteca.findArtigo(2);
        imprimeArtigo(artigoLido2);

        System.out.println("----------------------------------------------");

        Livro livroLido1 = biblioteca.findLivro(3);
        imprimeLivro(livroLido1);

        System.out.println("----------------------------------------------");


        Revista revistaLida1 = biblioteca.findRevista(4);
        imprimeRevista(revistaLida1);

        System.out.println("----------------------------------------------");

        System.out.println("\nLista de Publicações");
        List<Publicacao> lista = biblioteca.findPubs("tal");
        for (Publicacao pub : lista) {
            System.out.println("Codigo: " + pub.getCodigo());
            System.out.println("Titulo: " + pub.getTitulo());
            System.out.println("Data: " + formatDate.format(pub.getData()));
            imprimeEditor(pub.getEditor());
            System.out.println("-------------------------------");
        }
    }

    private static void imprimeAutor(Autor autor) {
        if (autor != null) {
            System.out.println("\nAutor");
            System.out.println("Codigo: " + autor.getCodigo());
            System.out.println("Nome: " + autor.getNome());
            System.out.println("Sobrenome: " + autor.getSobrenome());
        }
    }

    private static void imprimeEditor(Editor editor) {
        if (editor != null) {
            System.out.println();
            System.out.println("\nEditor");
            System.out.println("Codigo: " + editor.getCodigo());
            System.out.println("Nome: " + editor.getNome());
        }
    }

    private static void imprimeArtigo(Artigo artigo) {
        if (artigo != null) {
            System.out.println("\nArtigo");
            System.out.println("Codigo: " + artigo.getCodigo());
            System.out.println("Titulo: " + artigo.getTitulo());
            System.out.println("Data: " + formatDate.format(artigo.getData()));
            imprimeEditor(artigo.getEditor());
            Autor[] autores = artigo.getAutores();
            System.out.println("Autores");
            for (int i = 0; i < autores.length; i++) {
                imprimeAutor(autores[i]);
            }
            String[] palavraChaves = artigo.getPalavrasChave();
            System.out.println("\nPalavras Chave");
            for (int i = 0; i < palavraChaves.length; i++) {
                System.out.println(palavraChaves[i]);
            }
        }
    }

    private static void imprimeLivro(Livro livro) {
        if (livro != null) {
            System.out.println("\nLivro");
            System.out.println("Codigo: " + livro.getCodigo());
            System.out.println("Titulo: " + livro.getTitulo());
            System.out.println("Data: " + formatDate.format(livro.getData()));
            imprimeEditor(livro.getEditor());
            Autor[] autores = livro.getAutores();
            System.out.println("Autores");
            for (int i = 0; i < autores.length; i++) {
                imprimeAutor(autores[i]);
            }
        }
    }

    private static void imprimeRevista(Revista revista) {
        if (revista != null) {
            System.out.println("\nRevista");
            System.out.println("Codigo: " + revista.getCodigo());
            System.out.println("Titulo: " + revista.getTitulo());
            System.out.println("Data: " + formatDate.format(revista.getData()));
            imprimeEditor(revista.getEditor());
            Artigo[] artigos = revista.getArtigos();
            System.out.println("Artigos");
            for (int i = 0; i < artigos.length; i++) {
                imprimeArtigo(artigos[i]);
            }
        }
    }
}
