package View;

import java.util.Scanner;

public class Exe6 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] matriz = new int[3][3];
        int[][] matrizResult = new int[3][3];
        System.out.println("Cálculo de multiplicação na matriz");
        for (int i = 0; i < 3; i++) {
            for (int y = 0; y < 3; y++) {
                System.out.println("Entre com um número para a linha " + (i + 1) + ", coluna " + (y + 1));
                matriz[i][y] = scan.nextInt();
            }
        }
        System.out.println("\nEntre com um número para multiplicar cada elemento da matriz");
        int numMultiplicador = scan.nextInt();
        System.out.println("\nMatriz original[3][3]:");
        for (int i = 0; i < 3; i++) {
            System.out.print("[\t");
            for (int y = 0; y < 3; y++) {
                System.out.print(matriz[i][y] + "\t");
                matrizResult[i][y] = matriz[i][y] * numMultiplicador;
            }
            System.out.print("]\n");
        }
        System.out.println("\nMatriz resultante[3][3] multiplicado cada valor por " + numMultiplicador);
        for (int i = 0; i < 3; i++) {
            System.out.print("[\t");
            for (int y = 0; y < 3; y++) {
                System.out.print(matrizResult[i][y] + "\t");
            }
            System.out.print("]\n");
        }
    }
}
