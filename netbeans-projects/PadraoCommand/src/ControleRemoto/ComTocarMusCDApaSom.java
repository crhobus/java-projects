package ControleRemoto;

public class ComTocarMusCDApaSom implements Comando {

    private AparelhoSom aparelhoSom;

    public ComTocarMusCDApaSom(AparelhoSom aparelhoSom) {
        this.aparelhoSom = aparelhoSom;
    }

    public void executar() {
        aparelhoSom.tocarMusicasCD();
    }
}
