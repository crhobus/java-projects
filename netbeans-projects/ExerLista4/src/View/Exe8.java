package View;

import java.util.Scanner;

public class Exe8 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] m = new int[3][2];
        int[][] transposta = new int[2][3];
        System.out.println("Gerando matriz transposta");
        System.out.println("Matriz M");
        for (int i = 0; i < 3; i++) {
            for (int y = 0; y < 2; y++) {
                System.out.println("Entre com um número para a linha " + (i + 1) + ", coluna " + (y + 1));
                m[i][y] = scan.nextInt();
                transposta[y][i] = m[i][y];
            }
        }
        System.out.println("\nMatriz[3][2] M:");
        for (int i = 0; i < 3; i++) {
            System.out.print("[\t");
            for (int y = 0; y < 2; y++) {
                System.out.print(m[i][y] + "\t");
            }
            System.out.print("]\n");
        }
        System.out.println("\nMatriz Transposta[2][3]:");
        for (int i = 0; i < 2; i++) {
            System.out.print("[\t");
            for (int y = 0; y < 3; y++) {
                System.out.print(transposta[i][y] + "\t");
            }
            System.out.print("]\n");
        }
    }
}
