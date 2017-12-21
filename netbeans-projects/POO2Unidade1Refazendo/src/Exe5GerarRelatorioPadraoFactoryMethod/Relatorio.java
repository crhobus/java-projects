package Exe5GerarRelatorioPadraoFactoryMethod;

import java.util.List;

public abstract class Relatorio {

    public abstract void cabecalho(String titulo);

    public abstract void conteudo(List<String> conteudo);

    public abstract void rodape();
}
