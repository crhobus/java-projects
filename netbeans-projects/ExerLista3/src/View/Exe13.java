package View;

import java.util.Scanner;

public class Exe13 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] vet = new int[30];
        System.out.println("Preenchendo o vetor");
        int n = 0;
        while (true) {
            System.out.println("Entre com o tamanho do vetor");
            n = scan.nextInt();
            if (n > 0 && n <= 30) {
                for (int i = 0; i < n; i++) {
                    System.out.println("Entre com o " + (i + 1) + "° elemento");
                    vet[i] = scan.nextInt();
                }
                break;
            } else {
                System.out.println("Tamanho não alocado, tente novamente");
            }
        }
        if (n != 0) {
            System.out.println("Vetor preenchido:");
            System.out.print("[ ");
            for (int i = 0; i < n; i++) {
                System.out.print(vet[i] + " ");
            }
            System.out.println("]");
        }
    }
}
