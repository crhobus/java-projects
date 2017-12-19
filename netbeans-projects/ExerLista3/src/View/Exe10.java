package View;

import java.util.Scanner;

public class Exe10 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] vet = new int[3];
        for (int i = 0; i < vet.length; i++) {
            System.out.println("Entre com o " + (i + 1) + "° número");
            vet[i] = scan.nextInt();
        }
        int menorNumero = vet[0], posicaoMenor = 0;
        for (int i = 1; i < vet.length; i++) {
            if (vet[i] < menorNumero) {
                posicaoMenor = i;
            }
        }
        System.out.print("[ ");
        for (int i = 0; i < vet.length; i++) {
            System.out.print(vet[i] + " ");
        }
        System.out.println("]");
        System.out.println("Posição do menor número: " + (posicaoMenor + 1));
    }
}
