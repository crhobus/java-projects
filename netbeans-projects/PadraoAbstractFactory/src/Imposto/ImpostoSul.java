package Imposto;

public class ImpostoSul implements Imposto {

    public double getValorImposto(double valor) {
        return valor + (valor * 0.12);
    }
}
