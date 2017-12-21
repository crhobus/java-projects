package Exe5GerarRelatorioPadraoFactoryMethod;

public class RelatorioHTMLFactory extends RelatorioFactory {

    @Override
    public Relatorio criarRelatorio() {
        return new RelatorioHTML();
    }
}
