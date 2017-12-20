package ControleRemoto;

public class ComandoLigarLuz implements Comando {

    private Luz light;

    public ComandoLigarLuz(Luz light) {
        this.light = light;
    }

    public void executar() {
        light.ligar();
    }
}
