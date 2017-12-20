package ControleRemoto;

public class ComLigarRadioApaSom implements Comando {

    private AparelhoSom aparelhoSom;

    public ComLigarRadioApaSom(AparelhoSom aparelhoSom) {
        this.aparelhoSom = aparelhoSom;
    }

    public void executar() {
        aparelhoSom.ligarRadio();
    }
}
