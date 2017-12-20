package Frete;

public class FreteNorte implements Frete {

    public double getValorFrete(double peso, double valor) {
        if (peso <= 50) {
            return 30;
        }
        return 30 + (peso - 50) * 10;
    }
}
