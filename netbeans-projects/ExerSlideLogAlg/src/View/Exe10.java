package View;

import java.util.Scanner;

public class Exe10 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Leitura valores A, B");
        System.out.println("Entre com o valor de A");
        int a = scan.nextInt();
        System.out.println("Entre com o valor de B");
        int b = scan.nextInt();
        int c;
        if (a == b) {
            c = a + b;
        } else {
            c = a * b;
        }
        System.out.println("Valor de c: " + c);
    }
}
