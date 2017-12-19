package View;

import java.util.Scanner;

public class Exe5 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Entre com o primeiro valor");
        int v1 = scan.nextInt();
        System.out.println("Entre com o segundo valor");
        int v2 = scan.nextInt();
        System.out.println("Entre com o terceiro valor");
        int v3 = scan.nextInt();
        System.out.println("Entre com o quarto valor");
        int v4 = scan.nextInt();
        System.out.println("Média aritimética: " + ((double) (v1 + v2 + v3 + v4)) / 4);
    }
}
