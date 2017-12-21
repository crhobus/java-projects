package Exe3AutomovelPadraoObserver;

public class NivelOleoPowerOilsa implements Oleo {

    public void mostraOleo(double oleo, double oleoMax) {
        if (oleo < oleoMax / 4) {
            System.out.println("Nível de Oleo do Power Oil S/A: Baixo");
        } else if (oleo < oleoMax / 2) {
            System.out.println("Nível de Oleo do Power Oil S/A: Atenção");
        } else {
            System.out.println("Nível de Oleo do Power Oil S/A: Normal");
        }
    }
}
