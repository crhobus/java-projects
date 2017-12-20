package Imposto;

public class ImpostoSuldeste implements Imposto {

    public double getValorImposto(double valor) {
        return valor + (valor * 0.07);
    }
}
