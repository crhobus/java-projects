package View;

import java.util.Scanner;

public class Exe24 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("TABUADA");
        System.out.println("Digite um valor de 1 a 10");
        int num = scan.nextInt();
        if (num >= 1 && num <= 10) {
            for (int i = 1; i <= 10; i++) {
                System.out.println(num + " x " + i + " = " + (i * num));
            }
        } else {
            System.out.println("Número inválido entre novamente");
        }
    }
}
