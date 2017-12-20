package Imposto;

public class ImpostoNorte implements Imposto {

    public double getValorImposto(double valor) {
        return valor + (valor * 0.10);
    }
}
