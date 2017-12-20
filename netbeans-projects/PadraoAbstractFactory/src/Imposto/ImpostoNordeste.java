package Imposto;

public class ImpostoNordeste implements Imposto {

    public double getValorImposto(double valor) {
        if (valor <= 1000) {
            return valor + (valor * 0.08);
        } else {
            if (valor < 15000) {
                return valor + (valor * 0.10);
            } else {
                return valor + (valor * 0.12);
            }
        }
    }
}
