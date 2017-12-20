package ControleRemoto;

public class ComDesRadioApaSom implements Comando {

    private AparelhoSom aparelhoSom;

    public ComDesRadioApaSom(AparelhoSom aparelhoSom) {
        this.aparelhoSom = aparelhoSom;
    }

    public void executar() {
        aparelhoSom.desligarRadio();
    }
}
