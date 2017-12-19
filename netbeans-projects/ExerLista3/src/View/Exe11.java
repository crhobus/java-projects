package View;

import java.util.Scanner;

public class Exe11 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] vet = new int[7];
        for (int i = 0; i < vet.length; i++) {
            System.out.println("Entre com o " + (i + 1) + "° número");
            vet[i] = scan.nextInt();
        }
        for (int i = vet.length - 1; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                if (vet[j] > vet[j + 1]) {
                    int temp = vet[j];
                    vet[j] = vet[j + 1];
                    vet[j + 1] = temp;
                }
            }
        }
        System.out.println("\nElementos na ordem inversa às que foram digitadas");
        System.out.print("[ ");
        for (int i = 0; i < vet.length; i++) {
            System.out.print(vet[i] + " ");
        }
        System.out.println("]");
    }
}
