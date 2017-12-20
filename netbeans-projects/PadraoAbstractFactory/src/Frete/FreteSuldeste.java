package Frete;

public class FreteSuldeste implements Frete {

    public double getValorFrete(double peso, double valor) {
        return peso * 25;
    }
}
