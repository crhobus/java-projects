package PermutacaoRecursivo;

import javax.swing.JOptionPane;

public class Sistema {

    public static void main(String[] args) {
        Permutacao permutacao = new Permutacao();
        String texto = JOptionPane.showInputDialog(null, "Entre com uma palavra");
        permutacao.permutarString("", texto);
    }
}
