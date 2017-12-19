package View;

import java.util.Scanner;

public class Exe9 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Leitura valores A, B, C");
        System.out.println("Entre com o valor de A");
        int a = scan.nextInt();
        System.out.println("Entre com o valor de B");
        int b = scan.nextInt();
        System.out.println("Entre com o valor de C");
        int c = scan.nextInt();
        if (a + b < c) {
            System.out.println("Soma de a + b é menor que c");
        } else {
            System.out.println("Soma de a + b não é menor que c");
        }
    }
}
