package View;

import java.util.Scanner;

public class Exe17 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Soma de diversos números inteiros");
        int i = 1, soma = 0, num;
        while (true) {
            System.out.println("Entre com o " + i + "° número");
            num = scan.nextInt();
            if (num == -1) {
                break;
            } else {
                soma += num;
            }
            i++;
        }
        System.out.println("A soma de todos os números é " + soma);
    }
}
