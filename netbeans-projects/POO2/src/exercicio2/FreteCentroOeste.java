package exercicio2;

public class FreteCentroOeste implements Frete {

    @Override
    public double getValorFrete(Regiao origem, double peso, double valorTotal) throws Exception {
        if (origem.getClass().equals(RegiaoNorte.class) || origem.getClass().equals(RegiaoSul.class)) {
            return 80.00;
        } else if (origem.getClass().equals(RegiaoNordeste.class) || origem.getClass().equals(RegiaoSudeste.class)) {
            return 70.00;
        } else if (origem.getClass().equals(RegiaoCentroOeste.class)) {
            return 30.00;
        } else {
            throw new Exception("Valor do frete não calculado, pois a região de origem não foi encontrada.");
        }
    }
}
