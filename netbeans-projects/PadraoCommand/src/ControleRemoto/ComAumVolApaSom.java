package ControleRemoto;

public class ComAumVolApaSom implements Comando {

    private AparelhoSom aparelhoSom;

    public ComAumVolApaSom(AparelhoSom aparelhoSom) {
        this.aparelhoSom = aparelhoSom;
    }

    public void executar() {
        aparelhoSom.aumentarVolume();
    }
}
