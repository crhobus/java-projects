package exercicio1;

public class ChinaInc implements MostradorOleo, MostradorVelocidade, MostradorCombustivel {

    @Override
    public void mostraOleo(double oleo, double oleoMax) {
        if (oleo > (oleoMax / 4) * 3) {
            System.out.println("Óleo bom");
        } else {
            System.out.println("Óleo ruim");
        }
    }

    @Override
    public void mostraVelocidade(int v) {
        System.out.println("Velocidade: " + v);
    }

    @Override
    public void mostraCombustivel(int combustivel, int cMax) {
        if (combustivel == 0) {
            System.out.println("");
        } else if (combustivel < cMax / 4) {
            System.out.println("x");
        } else if (combustivel < cMax / 2) {
            System.out.println("xx");
        } else if (combustivel < (cMax / 4) * 3) {
            System.out.println("xxx");
        } else {
            System.out.println("xxxx");
        }
    }
}
