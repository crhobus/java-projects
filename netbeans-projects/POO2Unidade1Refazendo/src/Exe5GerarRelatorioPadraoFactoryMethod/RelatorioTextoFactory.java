package Exe5GerarRelatorioPadraoFactoryMethod;

public class RelatorioTextoFactory extends RelatorioFactory {

    @Override
    public Relatorio criarRelatorio() {
        return new RelatorioTexto();
    }
}
