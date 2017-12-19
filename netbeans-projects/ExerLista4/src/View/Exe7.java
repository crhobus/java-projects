package View;

import java.util.Scanner;

public class Exe7 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] a = new int[3][4];
        int[][] b = new int[3][4];
        int[][] c = new int[3][4];
        System.out.println("Soma posições das matrizes");
        System.out.println("Matriz A");
        for (int i = 0; i < 3; i++) {
            for (int y = 0; y < 4; y++) {
                System.out.println("Entre com um número para a linha " + (i + 1) + ", coluna " + (y + 1));
                a[i][y] = scan.nextInt();
            }
        }
        System.out.println("\nMatriz B");
        for (int i = 0; i < 3; i++) {
            for (int y = 0; y < 4; y++) {
                System.out.println("Entre com um número para a linha " + (i + 1) + ", coluna " + (y + 1));
                b[i][y] = scan.nextInt();
            }
        }
        System.out.println("\nMatriz[3][4] A:");
        for (int i = 0; i < 3; i++) {
            System.out.print("[\t");
            for (int y = 0; y < 4; y++) {
                System.out.print(a[i][y] + "\t");
            }
            System.out.print("]\n");
        }
        System.out.println("\nMatriz[3][4] B:");
        for (int i = 0; i < 3; i++) {
            System.out.print("[\t");
            for (int y = 0; y < 4; y++) {
                System.out.print(b[i][y] + "\t");
                c[i][y] = a[i][y] + b[i][y];
            }
            System.out.print("]\n");
        }
        System.out.println("\nMatriz[3][4] C resultante da soma:");
        for (int i = 0; i < 3; i++) {
            System.out.print("[\t");
            for (int y = 0; y < 4; y++) {
                System.out.print(c[i][y] + "\t");
            }
            System.out.print("]\n");
        }
    }
}
