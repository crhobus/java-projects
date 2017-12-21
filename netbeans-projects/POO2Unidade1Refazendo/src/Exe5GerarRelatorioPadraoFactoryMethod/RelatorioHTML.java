package Exe5GerarRelatorioPadraoFactoryMethod;

import java.util.List;

public class RelatorioHTML extends Relatorio {

    private int rodape;

    @Override
    public void cabecalho(String titulo) {
        System.out.println("<html>");
        System.out.println("\t<head>");
        System.out.println("\t   <title>" + titulo + "</title>");
        System.out.println("\t</header>");
        System.out.println("\t<body>");
    }

    @Override
    public void conteudo(List<String> conteudo) {
        System.out.println("\t  <dados>");
        for (String str : conteudo) {
            System.out.println("\t\t" + str);
        }
        rodape = conteudo.size();
        System.out.println("\t  </dados>");
    }

    @Override
    public void rodape() {
        System.out.println("\t<rodape>Total listado: " + rodape + "</rodape>");
        System.out.println("\t</body>");
        System.out.println("</html>");
    }
}
