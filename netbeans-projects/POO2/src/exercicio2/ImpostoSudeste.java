package exercicio2;

public class ImpostoSudeste implements Imposto {

    @Override
    public double getValorImposto(Regiao origem, double valorTotal) {
        if (origem.getClass().equals(RegiaoNorte.class) || origem.getClass().equals(RegiaoNordeste.class)) {
            return valorTotal * 0.05;
        } else if (origem.getClass().equals(RegiaoCentroOeste.class)) {
            return valorTotal * 0.07;
        } else {
            return valorTotal * 0.1;
        }
    }
}
