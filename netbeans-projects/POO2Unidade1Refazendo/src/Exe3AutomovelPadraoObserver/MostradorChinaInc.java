package Exe3AutomovelPadraoObserver;

public class MostradorChinaInc implements Velocidade, Combustivel, Oleo {

    public void mostraVelocidade(int velocidade) {
        System.out.println("Velocidade atual no mostrador China Inc: " + velocidade);
    }

    public void mostraCombustivel(int combustivel, int combustivelMax) {
        if (combustivel == 0) {
            System.out.println("tanque está vazio no mostrador China Inc");
        } else if (combustivel < combustivelMax / 4) {
            System.out.println("x tanque está abaixo de 1/4 da capacidade no mostrador China Inc");
        } else if (combustivel < combustivelMax / 2) {
            System.out.println("xx tanque está entre 1/4 e 1/2 da capacidade no mostrador China Inc");
        } else if (combustivel < (combustivelMax / 4) * 3) {
            System.out.println("xxx tanque está entre 1/2 e 3/4 da capacidade no mostrador China Inc");
        } else {
            System.out.println("xxxx tanque está acima de 3/4 da capacidade no mostrador China Inc");
        }

    }

    public void mostraOleo(double oleo, double oleoMax) {
        if (oleo > (oleoMax / 4) * 3) {
            System.out.println("Oleo no mostrador China Inc: Bom acima de 3/4");
        } else {
            System.out.println("Oleo no mostrador China Inc: Ruim abaixo de 3/4");
        }
    }
}
