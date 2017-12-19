package View;

import java.util.Scanner;

public class Exe6 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] vet1 = new int[10];
        int[] vet2 = new int[10];
        System.out.println("Soma das posições");
        for (int i = 0; i < 20; i++) {
            if (i < 10) {
                System.out.println("Entre com o " + (i + 1) + "° número");
                vet1[i] = scan.nextInt();
            } else {
                System.out.println("Entre com o " + (i + 1) + "° número");
                vet2[i - 10] = scan.nextInt();
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(vet1[i] + " + " + vet2[i] + " = " + (vet1[i] + vet2[i]));
        }
    }
}
