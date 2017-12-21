package Exe3AutomovelPadraoObserver;

public class NivelOleo implements Oleo {

    public void mostraOleo(double oleo, double oleoMax) {
        if (oleo < oleoMax / 2) {
            System.out.println("Nível de Oleo do automóvel: Abaixo de 1/2");
        } else if (oleo > oleoMax / 2) {
            System.out.println("Nível de Oleo do automóvel: Acima de 1/2");
        } else {
            System.out.println("Nível de Oleo do automóvel: 1/2");
        }
    }
}
