package ControleRemoto;

public class ComPararMusCDApaSom implements Comando {

    private AparelhoSom aparelhoSom;

    public ComPararMusCDApaSom(AparelhoSom aparelhoSom) {
        this.aparelhoSom = aparelhoSom;
    }

    public void executar() {
        aparelhoSom.paraTocarMusicaCD();
    }
}
