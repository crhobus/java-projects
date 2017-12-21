package exercicio2;

public class FreteNorte implements Frete {

    @Override
    public double getValorFrete(Regiao origem, double peso, double valorTotal) throws Exception {
        if (peso <= 3.5) {
            return 100;
        } else {
            throw new Exception("Peso máximo excedido.");
        }
    }
}
