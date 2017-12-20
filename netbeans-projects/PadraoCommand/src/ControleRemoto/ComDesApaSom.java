package ControleRemoto;

public class ComDesApaSom implements Comando {

    private AparelhoSom aparelhoSom;

    public ComDesApaSom(AparelhoSom aparelhoSom) {
        this.aparelhoSom = aparelhoSom;
    }

    public void executar() {
        aparelhoSom.desligar();
    }
}
