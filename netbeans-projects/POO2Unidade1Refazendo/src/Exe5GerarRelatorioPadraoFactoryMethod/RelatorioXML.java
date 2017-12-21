package Exe5GerarRelatorioPadraoFactoryMethod;

import java.util.List;

public class RelatorioXML extends Relatorio {

    private int rodape;

    @Override
    public void cabecalho(String titulo) {
        System.out.println("<xml>");
        System.out.println("\t<titulo>" + titulo + "</titulo>");
    }

    @Override
    public void conteudo(List<String> conteudo) {
        System.out.println("\t<conteudo>");
        for (String str : conteudo) {
            System.out.println("\t     " + str);
        }
        rodape = conteudo.size();
        System.out.println("\t</conteudo>");
    }

    @Override
    public void rodape() {
        System.out.println("\t<rodape>Total listado: " + rodape + "</rodape>");
        System.out.println("</xml>");
    }
}
