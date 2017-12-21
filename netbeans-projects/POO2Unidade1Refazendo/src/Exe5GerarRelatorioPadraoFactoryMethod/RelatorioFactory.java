package Exe5GerarRelatorioPadraoFactoryMethod;

import java.util.List;

public abstract class RelatorioFactory {

    public abstract Relatorio criarRelatorio();

    public void fazerRelatorio(String titulo, List<String> conteudo) {
        Relatorio relatorio = criarRelatorio();
        relatorio.cabecalho(titulo);
        relatorio.conteudo(conteudo);
        relatorio.rodape();
    }
}
