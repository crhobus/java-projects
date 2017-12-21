package Trabalho1;

public class HTML implements TipoRelatorio {

    public Corpo getCorpo() {
        return new HTMLCorpo();
    }

    public Titulo getTitulo() {
        return new HTMLTitulo();
    }

    public Rodape getRodape() {
        return new HTMLRodape();
    }
}
