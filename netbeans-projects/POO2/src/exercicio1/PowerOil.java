package exercicio1;

public class PowerOil implements MostradorOleo {

    public void mostraOleo(double o, double oMax) {
        if (o < oMax / 4) {
            System.out.println("Oleo: Baixo");
        } else if (o < oMax / 2) {
            System.out.println("Oleo: Atenção");
        } else {
            System.out.println("Oleo: Normal");
        }
    }
}
