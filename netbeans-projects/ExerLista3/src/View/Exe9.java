package View;

import java.util.Scanner;

public class Exe9 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] vet = {1, 3, 4, 50, 10, 47, 49, 10, 3, 10};
        System.out.println("Entre com um número para ser buscado no vetor");
        int num = scan.nextInt();
        boolean encontrado = false;
        for (int i = 0; i < vet.length; i++) {
            if (vet[i] == num) {
                encontrado = true;
            }
        }
        if (encontrado) {
            System.out.println("Número encontrado: " + num);
        } else {
            System.out.println("Número não encontrado");
        }
    }
}
