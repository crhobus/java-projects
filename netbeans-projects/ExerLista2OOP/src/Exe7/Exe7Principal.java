package Exe7;

import javax.swing.JOptionPane;

import Excecao.ExceptionNumber;

public class Exe7Principal {

    public Exe7Principal() {
        System.out.println("Jogo da advinhação");
        int num1, num2;
        while (true) {
            try {
                num1 = getNumero("Entre com um número para que outra pessoa possa advinhar");
                break;
            } catch (ExceptionNumber ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (num1 != -1) {
            while (true) {
                try {
                    num2 = getNumero("Tente advinhar o número gerado");
                    break;
                } catch (ExceptionNumber ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (num2 != -1) {
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

    private int getNumero(String msg) throws ExceptionNumber {
        try {
            String s = JOptionPane.showInputDialog(null, msg, "Entrada", JOptionPane.INFORMATION_MESSAGE);
            if (s == null) {
                return -1;
            }
            return Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            throw new ExceptionNumber();
        }
    }
}
