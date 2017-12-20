package ControleRemoto;

public class ComColCDApaSom implements Comando {

    private AparelhoSom aparelhoSom;

    public ComColCDApaSom(AparelhoSom aparelhoSom) {
        this.aparelhoSom = aparelhoSom;
    }

    public void executar() {
        aparelhoSom.colocarCD();
    }
}
