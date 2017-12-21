package exercicio2;

public class ImpostoNordeste implements Imposto {

    @Override
    public double getValorImposto(Regiao origem, double valorTotal) {
        if (valorTotal <= 1000) {
            return valorTotal * 0.08;
        } else if (valorTotal <= 15000) {
            return valorTotal * 0.1;
        } else {
            return valorTotal = 0.12;
        }
    }
}
