package View;

import java.util.Scanner;

public class Exe12 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Leitura de 3 valores");
        System.out.println("Entre com o primeiro valor");
        int num1 = scan.nextInt();
        System.out.println("Entre com o segundo valor");
        int num2 = scan.nextInt();
        System.out.println("Entre com o terceiro valor");
        int num3 = scan.nextInt();
        int menor = num1;
        if (num2 < menor) {
            menor = num2;
        }
        if (num3 < menor) {
            menor = num3;
        }
        System.out.println("O menor valor é: " + menor);
    }
}
