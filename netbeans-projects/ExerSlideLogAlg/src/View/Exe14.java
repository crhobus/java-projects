package View;

import java.util.Scanner;

public class Exe14 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Verifica se número é positivo ou negativo");
        System.out.println("Entre com um número");
        int num = scan.nextInt();
        if (num == 0) {
            System.out.println("Número zero");
        } else {
            if (num > 0) {
                System.out.println("Número positivo");
            } else {
                System.out.println("Número negativo");
            }
        }
        if (num % 2 == 0) {

            System.out.println("Número par");
        } else {
            System.out.println("Número ímpar");
        }
    }
}
