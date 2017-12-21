package Exe5GerarRelatorioPadraoFactoryMethod;

public class RelatorioXMLFactory extends RelatorioFactory {

    @Override
    public Relatorio criarRelatorio() {
        return new RelatorioXML();
    }
}
