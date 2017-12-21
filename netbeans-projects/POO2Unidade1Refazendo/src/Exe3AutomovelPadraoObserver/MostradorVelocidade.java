package Exe3AutomovelPadraoObserver;

public class MostradorVelocidade implements Velocidade {

    public void mostraVelocidade(int velocidade) {
        System.out.printf("A velocidade atual do automóvel é de %d\n", velocidade);
    }
}
