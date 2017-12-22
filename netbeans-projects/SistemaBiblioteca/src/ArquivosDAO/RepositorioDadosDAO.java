package ArquivosDAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import Agente.Autor;
import Agente.Editor;
import Publicacao.Artigo;
import Publicacao.Livro;
import Publicacao.Publicacao;
import Publicacao.Revista;

public class RepositorioDadosDAO {

    public void addAutor(Autor autor) {
        File diretorio = new File("agentes");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(diretorio, autor.getCodigo() + ".txt"))));
                out.println("Autor");
                out.println(autor.getCodigo());
                out.println(autor.getNome());
                out.println(autor.getSobrenome());
                out.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo " + autor.getCodigo() + ".txt do diretorio de agentes", "Erro no autor", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Autor findAutor(int codigo) {
        File diretorio = new File("agentes");
        if (diretorio.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File(diretorio, codigo + ".txt")));
                try {
                    String linha = in.readLine();
                    if (linha.equals("Autor")) {
                        codigo = Integer.parseInt(in.readLine());
                        String nome = in.readLine();
                        String sobrenome = in.readLine();
                        in.close();
                        return new Autor(codigo, nome, sobrenome);
                    } else {
                        in.close();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro na leitura de uma linha do arquivo " + codigo + ".txt do diretorio de agentes", "Erro no autor", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo " + codigo + ".txt do diretorio de agentes", "Erro no autor", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    public void addEditor(Editor editor) {
        File diretorio = new File("agentes");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(diretorio, editor.getCodigo() + ".txt"))));
                out.println("Editor");
                out.println(editor.getCodigo());
                out.println(editor.getNome());
                out.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo " + editor.getCodigo() + ".txt do diretorio de agentes", "Erro no editor", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Editor findEditor(int codigo) {
        File diretorio = new File("agentes");
        if (diretorio.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File(diretorio, codigo + ".txt")));
                try {
                    String linha = in.readLine();
                    if (linha.equals("Editor")) {
                        codigo = Integer.parseInt(in.readLine());
                        String nome = in.readLine();
                        in.close();
                        return new Editor(codigo, nome);
                    } else {
                        in.close();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro na leitura de uma linha do arquivo " + codigo + ".txt do diretorio de agentes", "Erro no autor", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo " + codigo + ".txt do diretorio de agentes", "Erro no autor", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    public void addArtigo(Artigo artigo) {
        File diretorio = new File("publicacoes");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(diretorio, artigo.getCodigo() + ".txt"))));
                out.println("Artigo");
                out.println(artigo.getCodigo());
                out.println(artigo.getTitulo());
                out.println(SimpleDateFormat.getInstance().format(artigo.getData()));
                out.println(artigo.getEditor().getCodigo());
                Autor[] autores = artigo.getAutores();
                out.println(autores.length);
                for (int i = 0; i < autores.length; i++) {
                    out.println(autores[i].getCodigo());
                }
                String[] palavrasChave = artigo.getPalavrasChave();
                out.println(palavrasChave.length);
                for (int i = 0; i < palavrasChave.length; i++) {
                    out.println(palavrasChave[i]);
                }
                out.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo " + artigo.getCodigo() + ".txt do diretorio de publicacoes", "Erro no artigo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Artigo findArtigo(int codigo) {
        File diretorio = new File("publicacoes");
        if (diretorio.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File(diretorio, codigo + ".txt")));
                try {
                    String linha = in.readLine();
                    if (linha.equals("Artigo")) {
                        codigo = Integer.parseInt(in.readLine());
                        String titulo = in.readLine();
                        Date data = null;
                        try {
                            data = SimpleDateFormat.getInstance().parse(in.readLine());
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(null, "Erro na converção da data", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        Editor editor = findEditor(Integer.parseInt(in.readLine()));
                        int qtdadeAut = Integer.parseInt(in.readLine());
                        Autor[] autores = new Autor[qtdadeAut];
                        for (int i = 0; i < qtdadeAut; i++) {
                            autores[i] = findAutor(Integer.parseInt(in.readLine()));
                        }
                        int qtdadePalChave = Integer.parseInt(in.readLine());
                        String[] palavraChave = new String[qtdadePalChave];
                        for (int i = 0; i < qtdadePalChave; i++) {
                            palavraChave[i] = in.readLine();
                        }
                        in.close();
                        return new Artigo(codigo, titulo, data, editor, autores, palavraChave);
                    } else {
                        in.close();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro na leitura de uma linha do arquivo " + codigo + ".txt do diretorio de publicacoes", "Erro no artigo", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo " + codigo + ".txt do diretorio de publicacoes", "Erro no artigo", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    public void addLivro(Livro livro) {
        File diretorio = new File("publicacoes");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(diretorio, livro.getCodigo() + ".txt"))));
                out.println("Livro");
                out.println(livro.getCodigo());
                out.println(livro.getTitulo());
                out.println(SimpleDateFormat.getInstance().format(livro.getData()));
                out.println(livro.getEditor().getCodigo());
                Autor[] autores = livro.getAutores();
                out.println(autores.length);
                for (int i = 0; i < autores.length; i++) {
                    out.println(autores[i].getCodigo());
                }
                out.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo " + livro.getCodigo() + ".txt do diretorio de publicacoes", "Erro no livro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Livro findLivro(int codigo) {
        File diretorio = new File("publicacoes");
        if (diretorio.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File(diretorio, codigo + ".txt")));
                try {
                    String linha = in.readLine();
                    if (linha.equals("Livro")) {
                        codigo = Integer.parseInt(in.readLine());
                        String titulo = in.readLine();
                        Date data = null;
                        try {
                            data = SimpleDateFormat.getInstance().parse(in.readLine());
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(null, "Erro na converção da data", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        Editor editor = findEditor(Integer.parseInt(in.readLine()));
                        int qtdadeAut = Integer.parseInt(in.readLine());
                        Autor[] autores = new Autor[qtdadeAut];
                        for (int i = 0; i < qtdadeAut; i++) {
                            autores[i] = findAutor(Integer.parseInt(in.readLine()));
                        }
                        in.close();
                        return new Livro(codigo, titulo, data, editor, autores);
                    } else {
                        in.close();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro na leitura de uma linha do arquivo " + codigo + ".txt do diretorio de publicacoes", "Erro no livro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo " + codigo + ".txt do diretorio de publicacoes", "Erro no livro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    public void addRevista(Revista revista) {
        File diretorio = new File("publicacoes");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(diretorio, revista.getCodigo() + ".txt"))));
                out.println("Revista");
                out.println(revista.getCodigo());
                out.println(revista.getTitulo());
                out.println(SimpleDateFormat.getInstance().format(revista.getData()));
                out.println(revista.getEditor().getCodigo());
                Artigo[] artigos = revista.getArtigos();
                out.println(artigos.length);
                for (int i = 0; i < artigos.length; i++) {
                    out.println(artigos[i].getCodigo());
                }
                out.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo " + revista.getCodigo() + ".txt do diretorio de publicacoes", "Erro na revista", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Revista findRevista(int codigo) {
        File diretorio = new File("publicacoes");
        if (diretorio.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File(diretorio, codigo + ".txt")));
                try {
                    String linha = in.readLine();
                    if (linha.equals("Revista")) {
                        codigo = Integer.parseInt(in.readLine());
                        String titulo = in.readLine();
                        Date data = null;
                        try {
                            data = SimpleDateFormat.getInstance().parse(in.readLine());
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(null, "Erro na converção da data", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        Editor editor = findEditor(Integer.parseInt(in.readLine()));
                        int qtdadeArt = Integer.parseInt(in.readLine());
                        Artigo[] artigos = new Artigo[qtdadeArt];
                        for (int i = 0; i < qtdadeArt; i++) {
                            artigos[i] = findArtigo(Integer.parseInt(in.readLine()));
                        }
                        in.close();
                        return new Revista(codigo, titulo, data, editor, artigos);
                    } else {
                        in.close();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro na leitura de uma linha do arquivo " + codigo + ".txt do diretorio de publicacoes", "Erro na revista", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo " + codigo + ".txt do diretorio de publicacoes", "Erro na revista", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    public void removePub(int codigo) {
        File diretorio = new File("publicacoes");
        if (diretorio.exists()) {
            File arq = new File(diretorio, codigo + ".txt");
            if (arq.exists()) {
                arq.delete();
            }
        }
    }

    public void removeAgente(int codigo) {
        File diretorio = new File("agentes");
        if (diretorio.exists()) {
            File arq = new File(diretorio, codigo + ".txt");
            if (arq.exists()) {
                arq.delete();
            }
        }
    }

    public List<Publicacao> findPubs(String titulo) {
        List<Publicacao> lista = new ArrayList<Publicacao>();
        File diretorio = new File("publicacoes");
        if (diretorio.exists()) {
            File[] arqs = diretorio.listFiles();
            for (int i = 0; i < arqs.length; i++) {
                Artigo artigo = findArtigo(Integer.parseInt(arqs[i].getName().substring(0, 1)));
                if (artigo != null) {
                    if (artigo.getTitulo().equalsIgnoreCase(titulo)) {
                        lista.add(artigo);
                    }
                } else {
                    Livro livro = findLivro(Integer.parseInt(arqs[i].getName().substring(0, 1)));
                    if (livro != null) {
                        if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                            lista.add(livro);
                        }
                    } else {
                        Revista revista = findRevista(Integer.parseInt(arqs[i].getName().substring(0, 1)));
                        if (revista != null) {
                            if (revista.getTitulo().equalsIgnoreCase(titulo)) {
                                lista.add(revista);
                            }
                        }
                    }
                }
            }
        }
        return lista;
    }
}
