package View;

import java.util.Scanner;

public class Exe28 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Entre com um número");
        double num1 = scan.nextInt();
        System.out.println("Entre com o segundo número");
        double num2 = scan.nextInt();
        System.out.println("Entre com o tipo da operação: +, ‐, *, /");
        String operacao = scan.next();
        if (operacao.equals("+")) {
            System.out.println("Soma dos valores é " + (num1 + num2));
        } else {
            if (operacao.equals("-")) {
                System.out.println("Subtração dos valores é " + (num1 - num2));
            } else {
                if (operacao.equals("*")) {
                    System.out.println("Multilicação dos valores é " + (num1 * num2));
                } else {
                    if (operacao.equals("/")) {
                        if (num2 == 0) {
                            System.err.println("Erro: Divisão por zero");
                        } else {
                            System.out.println("Divisão dos valores é " + (num1 / num2));
                        }
                    } else {
                        System.err.println("Operador inválido");
                    }
                }
            }
        }
    }
}
