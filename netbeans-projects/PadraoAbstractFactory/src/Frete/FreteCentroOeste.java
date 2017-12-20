package Frete;

public class FreteCentroOeste implements Frete {

    public double getValorFrete(double peso, double valor) {
        return peso * 30;
    }
}
