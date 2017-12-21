package Exe1Automovel;

public class PowerOil extends Mostrador {

    private String proporcao;

    public void mostraCombustivel(int tanqueCheio, int combustivel) {
    }

    public void mostraVelocidade(int velocidade) {
    }

    public void mostraOleo(double oleo) {
        if (oleo / 15 >= 0.5) {
            proporcao = "Normal";
        } else if (oleo / 15 < 0.5 && oleo / 15 >= 0.25) {
            proporcao = "Abaixo";
        } else if (oleo / 15 < 0.25) {
            proporcao = "Atenção";
        }
        System.out.println("Nível do óleo: " + proporcao);
    }
}
