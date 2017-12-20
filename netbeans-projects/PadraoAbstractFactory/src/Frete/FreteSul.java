package Frete;

public class FreteSul implements Frete {

    public double getValorFrete(double peso, double valor) {
        return valor + (valor * 0.03);
    }
}
