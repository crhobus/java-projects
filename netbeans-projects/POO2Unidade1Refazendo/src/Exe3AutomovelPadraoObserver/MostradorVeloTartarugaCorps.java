package Exe3AutomovelPadraoObserver;

import javax.swing.JOptionPane;

public class MostradorVeloTartarugaCorps implements Velocidade {

    public void mostraVelocidade(int velocidade) {
        JOptionPane.showMessageDialog(null, "Velocidade atual da Tartaruga Corps: " + velocidade, "Velocidade", JOptionPane.INFORMATION_MESSAGE);
    }
}
