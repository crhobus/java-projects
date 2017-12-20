package ControleRemoto;

public class ComDesLuz implements Comando {

    private Luz light;

    public ComDesLuz(Luz light) {
        this.light = light;
    }

    public void executar() {
        light.desligar();
    }
}
