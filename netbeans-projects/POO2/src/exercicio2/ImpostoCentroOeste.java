package exercicio2;

public class ImpostoCentroOeste implements Imposto {

    @Override
    public double getValorImposto(Regiao origem, double valorTotal) {
        if (valorTotal <= 5000) {
            return valorTotal * 0.12 + 50;
        } else {
            return valorTotal * 0.12;
        }
    }
}
