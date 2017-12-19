package View;

import java.util.Scanner;

public class Exe5 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] a = new int[4][3];
        int[][] b = new int[4][3];
        String[][] c = new String[4][3];
        System.out.println("Comparador de matriz");
        System.out.println("Matriz A");
        for (int i = 0; i < 4; i++) {
            for (int y = 0; y < 3; y++) {
                System.out.println("Entre com um número para a linha " + (i + 1) + ", coluna " + (y + 1));
                a[i][y] = scan.nextInt();
            }
        }
        System.out.println("\nMatriz B");
        for (int i = 0; i < 4; i++) {
            for (int y = 0; y < 3; y++) {
                System.out.println("Entre com um número para a linha " + (i + 1) + ", coluna " + (y + 1));
                b[i][y] = scan.nextInt();
            }
        }
        System.out.println("Matriz[4][3] A:");
        for (int i = 0; i < 4; i++) {
            System.out.print("[\t");
            for (int y = 0; y < 3; y++) {
                System.out.print(a[i][y] + "\t");
            }
            System.out.print("]\n");
        }
        System.out.println("\nMatriz[4][3] B:");
        for (int i = 0; i < 4; i++) {
            System.out.print("[\t");
            for (int y = 0; y < 3; y++) {
                System.out.print(b[i][y] + "\t");
                if (a[i][y] == b[i][y]) {
                    c[i][y] = "V";
                } else {
                    c[i][y] = "F";
                }
            }
            System.out.print("]\n");
        }
        System.out.println("\nMatriz[4][3] C comparadora de números:");
        for (int i = 0; i < 4; i++) {
            System.out.print("[\t");
            for (int y = 0; y < 3; y++) {
                System.out.print(c[i][y] + "\t");
            }
            System.out.print("]\n");
        }
    }
}
