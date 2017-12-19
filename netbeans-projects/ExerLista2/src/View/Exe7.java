package View;

import javax.swing.JOptionPane;

public class Exe7 {

    public static void main(String[] args) {
        System.out.println("Jogo da advinhação");
        String msg1 = JOptionPane.showInputDialog(null, "Entre com um número para que outra pessoa possa advinhar", "Entrada", JOptionPane.INFORMATION_MESSAGE);
        if (msg1 != null) {
            int num1 = Integer.parseInt(msg1);
            String msg2 = JOptionPane.showInputDialog(null, "Tente advinhar o número gerado", "Entrada", JOptionPane.INFORMATION_MESSAGE);
            if (msg2 != null) {
                int num2 = Integer.parseInt(msg2);
                if (num1 == num2) {
                    System.out.println("Voce acertou");
                } else {
                    if (num2 < num1) {
                        System.out.println("O número é menor do que você digitou ");
                    } else {
                        System.out.println("O número é maior do que você digitou");
                    }
                }
            }
        }
    }
}
