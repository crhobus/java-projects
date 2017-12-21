package Exe1Automovel;

import javax.swing.JOptionPane;

public class Tartaruga extends Mostrador {

    public void mostraVelocidade(int velocidade) {
        JOptionPane.showMessageDialog(null, "Velocidade atual: " + velocidade + "km/h");
    }

    @Override
    public void mostraCombustivel(int tanqueCheio, int combustivel) {
    }

    @Override
    public void mostraOleo(double oleo) {
    }
}
