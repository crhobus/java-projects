package ControleRemoto;

public class ComDimVolApaSom implements Comando {

    private AparelhoSom aparelhoSom;

    public ComDimVolApaSom(AparelhoSom aparelhoSom) {
        this.aparelhoSom = aparelhoSom;
    }

    public void executar() {
        aparelhoSom.diminuirVolume();
    }
}
