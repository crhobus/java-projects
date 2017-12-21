package Trabalho1;

public class PDF implements TipoRelatorio {

    public Corpo getCorpo() {
        return new PDFCorpo();
    }

    public Titulo getTitulo() {
        return new PDFTitulo();
    }

    public Rodape getRodape() {
        return new PDFRodape();
    }
}
