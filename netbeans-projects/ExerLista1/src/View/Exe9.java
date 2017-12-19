package View;

import java.util.Scanner;

public class Exe9 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Leitura de 3 valores e colocando em ordem crescente");
        System.out.println("Entre com o primeiro valor");
        int num1 = scan.nextInt();
        System.out.println("Entre com o segundo valor");
        int num2 = scan.nextInt();
        System.out.println("Entre com o terceiro valor");
        int num3 = scan.nextInt();
        int aux = 0;
        for (int i = 0; i < 2; i++) {
            if (num1 > num2) {
                aux = num1;
                num1 = num2;
                num2 = aux;
            }
            if (num2 > num3) {
                aux = num2;
                num2 = num3;
                num3 = aux;
            }
        }
        System.out.println("[ " + num1 + " - " + num2 + " - " + num3 + " ]");
    }
}
