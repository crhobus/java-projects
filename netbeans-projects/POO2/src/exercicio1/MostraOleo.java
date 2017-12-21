package exercicio1;

public class MostraOleo implements MostradorOleo {

    public void mostraOleo(double oleo, double oleoMax) {
        if (oleo < oleoMax / 2) {
            System.out.println("Oleo: Abaixo de 1/2");
        } else if (oleo > oleoMax / 2) {
            System.out.println("Oleo: Acima de 1/2");
        } else {
            System.out.println("Oleo: 1/2");
        }
    }
}
