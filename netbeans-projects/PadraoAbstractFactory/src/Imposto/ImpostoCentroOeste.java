package Imposto;

public class ImpostoCentroOeste implements Imposto {

    public double getValorImposto(double valor) {
        if (valor <= 5000) {
            return 50;
        } else {
            return valor + (valor * 0.12);
        }
    }
}
