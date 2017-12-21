package Exe3AutomovelPadraoObserver;

public class NivelCombustivel implements Combustivel {

    public void mostraCombustivel(int combustivel, int combustivelMax) {
        if (combustivel < combustivelMax / 4) {
            System.out.println("Nível do combustivel do automóvel está vazio: 1/4");
        } else if (combustivel < combustivelMax / 2) {
            System.out.println("Nível do combustivel do automóvel está pela metade: 1/2");
        } else if (combustivel < (combustivelMax / 4) * 3) {
            System.out.println("Nível do combustivel do automóvel: 3/4");
        } else {
            System.out.println("Nível do combustivel do automóvel está cheio: 1");
        }
    }
}
