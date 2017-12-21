package exercicio2;

public class ImpostoSul implements Imposto {

    @Override
    public double getValorImposto(Regiao origem, double valorTotal) {
        return valorTotal * 0.12;
    }
}
