package exercicio1;

import javax.swing.JOptionPane;

public class TartarugaCorps implements MostradorVelocidade {

    public void mostraVelocidade(int v) {
        JOptionPane.showMessageDialog(null, "A velocidade atual é: " + v);
    }
}
