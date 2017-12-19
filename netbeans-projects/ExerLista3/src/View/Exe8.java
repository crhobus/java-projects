package View;

import java.util.Scanner;

public class Exe8 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] vet = new int[20];
        for (int i = 0; i < vet.length; i++) {
            System.out.println("Entre com o " + (i + 1) + "° número");
            vet[i] = scan.nextInt();
        }
        System.out.println("\nElementos na ordem inversa às que foram digitadas");
        System.out.print("[ ");
        for (int i = vet.length - 1; i >= 0; i--) {
            System.out.print(vet[i] + " ");
        }
        System.out.println("]");
    }
}
