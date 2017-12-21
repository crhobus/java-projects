package exercicio2;

public class FreteSudeste implements Frete {

    @Override
    public double getValorFrete(Regiao origem, double peso, double valorTotal) throws Exception {
        if (origem.getClass().equals(RegiaoNorte.class)) {
            return peso * 40;
        } else if (origem.getClass().equals(RegiaoNordeste.class)) {
            return peso * 37;
        } else if (origem.getClass().equals(RegiaoCentroOeste.class)) {
            return peso * 30;
        } else if (origem.getClass().equals(RegiaoSudeste.class)) {
            return peso * 15;
        } else if (origem.getClass().equals(RegiaoSul.class)) {
            return peso * 25;
        } else {
            throw new Exception("Valor do frete não calculado, pois a região de origem não foi encontrada.");
        }
    }
}
