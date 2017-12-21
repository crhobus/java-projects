package Exe1Automovel;

public class China extends Mostrador {

    private String espaco;
    private String proporcao;
    private double quantidade;

    public void mostraCombustivel(int tanqueCheio, int combustivel) {
        quantidade = (double) combustivel / (double) tanqueCheio;
        proporcao = "";
        if (quantidade > 0.75) {
            proporcao = "xxxx";
        } else if (quantidade >= 0.50 && quantidade <= 0.75) {
            proporcao = "xxx";
        } else if (quantidade >= 0.25 && quantidade < 0.50) {
            proporcao = "xx";
        } else if (quantidade > 0 && quantidade < 0.25) {
            proporcao = "x";
        } else {
            proporcao = "";
        }

        System.out.printf("Combustivel atual (%s)  : %d\n", proporcao, combustivel);
    }

    public void mostraOleo(double oleo) {
        proporcao = "";
        if (oleo / 15 >= 0.75) {
            proporcao = "Bom";
        } else {
            proporcao = "Ruim";
        }
        System.out.println("Nível do óleo: " + proporcao);
    }

    public void mostraVelocidade(int velocidade) {
        proporcao = "";
        if (velocidade == 0 || (velocidade > 0 && velocidade <= 9)) {
            espaco = "00";
        } else if (velocidade < 100 && velocidade > 9) {
            espaco = "0";
        }
        System.out.println("[" + espaco + velocidade + "]");
    }
}
