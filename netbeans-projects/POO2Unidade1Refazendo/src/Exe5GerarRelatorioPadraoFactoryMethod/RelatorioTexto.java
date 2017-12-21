package Exe5GerarRelatorioPadraoFactoryMethod;

import java.util.List;

public class RelatorioTexto extends Relatorio {

    private int rodape;

    @Override
    public void cabecalho(String titulo) {
        System.out.println(titulo);
    }

    @Override
    public void conteudo(List<String> conteudo) {
        for (String str : conteudo) {
            System.out.println("\t" + str);
        }
        rodape = conteudo.size();
    }

    @Override
    public void rodape() {
        System.out.println("Total listado: " + rodape);
    }
}
