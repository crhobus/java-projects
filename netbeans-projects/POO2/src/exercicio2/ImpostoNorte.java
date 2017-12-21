package exercicio2;

public class ImpostoNorte implements Imposto {

    @Override
    public double getValorImposto(Regiao origem, double valorTotal) {
        return valorTotal * 0.1;
    }
}
