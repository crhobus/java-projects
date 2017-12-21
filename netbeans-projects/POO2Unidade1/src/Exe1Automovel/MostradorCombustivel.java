package Exe1Automovel;

public class MostradorCombustivel {

    private String proporcao;
    private double quantidade;

    public void mostrarCombustivel(int tanqueCheio, int combustivel) {

        quantidade = (double) combustivel / (double) tanqueCheio;

        if (quantidade == 1) {
            proporcao = "cheio";
        } else if (quantidade >= 0.75 && quantidade < 1) {
            proporcao = "3/4";
        } else if (quantidade >= 0.25 && quantidade < 0.75) {
            proporcao = "1/2";
        } else if (quantidade > 0 && quantidade < 0.25) {
            proporcao = "1/4";
        } else {
            proporcao = "vazio";
        }

        System.out.printf("Combustivel atual (%s)  : %d\n", proporcao, combustivel);
    }
}
