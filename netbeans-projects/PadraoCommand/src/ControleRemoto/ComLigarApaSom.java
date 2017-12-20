package ControleRemoto;

public class ComLigarApaSom implements Comando {

    private AparelhoSom aparelhoSom;

    public ComLigarApaSom(AparelhoSom aparelhoSom) {
        this.aparelhoSom = aparelhoSom;
    }

    public void executar() {
        aparelhoSom.ligar();
    }
}
