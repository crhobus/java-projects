package ControleRemoto;

public class ComRetCDApaSom implements Comando {

    private AparelhoSom aparelhoSom;

    public ComRetCDApaSom(AparelhoSom aparelhoSom) {
        this.aparelhoSom = aparelhoSom;
    }

    public void executar() {
        aparelhoSom.retirarCD();
    }
}
