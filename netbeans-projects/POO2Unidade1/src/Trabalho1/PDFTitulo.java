package Trabalho1;

public class PDFTitulo implements Titulo {

    public String titulo(String texto) {
        return "HEADER{" + texto + "}";
    }
}
