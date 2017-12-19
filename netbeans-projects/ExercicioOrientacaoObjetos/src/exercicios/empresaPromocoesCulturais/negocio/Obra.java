package exercicios.empresaPromocoesCulturais.negocio;

/**
 *
 * @author CaioRenan
 */
public class Obra {

    private String titulo;
    private String autor;
    private Parecer[] pareceres;
    private int indParecer;

    public Obra(String titulo, String autor) {
        pareceres = new Parecer[3];
        indParecer = 0;
        setTitulo(titulo);
        setAutor(autor);
    }

    public void addParecer(Parecer p) {
        if (indParecer < pareceres.length) {
            pareceres[indParecer] = p;
            indParecer++;
        }
    }

    public int getQtdPareceres() {
        return indParecer;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo != null
                && !titulo.isEmpty()) {
            this.titulo = titulo;
        } else {
            throw new IllegalArgumentException("Titulo da obra inválido");
        }
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        if (autor != null
                && !autor.isEmpty()) {
            this.autor = autor;
        } else {
            throw new IllegalArgumentException("Autor da obra inválido");
        }
    }

    @Override
    public String toString() {
        String str = "Titulo: " + titulo
                + "\nAutor: " + autor
                + "\nPareceres: " + getQtdPareceres();
        for (int i = 0; i < getQtdPareceres(); i++) {
            str += "\nParecer: " + (i + 1)
                    + " de " + pareceres[i].getNomeParecerista()
                    + "\nemitido em: " + pareceres[i].getData()
                    + "\nconteúdo: " + pareceres[i].getConteudo();
        }
        return str;
    }
}
