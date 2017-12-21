package Trabalho1;

import java.util.ArrayList;

public class Relatorio {

    private String titulo;
    private ArrayList<String> corpo;
    private String rodape;
    private TipoRelatorio tipoRelatorio;

    public Relatorio(String titulo, ArrayList<String> corpo, String rodape, TipoRelatorio tipoRelatorio) {
        this.titulo = titulo;
        this.corpo = corpo;
        this.rodape = rodape;
        this.tipoRelatorio = tipoRelatorio;
    }

    public void imprimir() {
        System.out.println(tipoRelatorio.getTitulo().titulo(titulo));
        System.out.println(tipoRelatorio.getCorpo().corpo(corpo));
        System.out.println(tipoRelatorio.getRodape().rodape(rodape));
    }
}
