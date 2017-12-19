package View;

import java.util.Scanner;

public class Exe4 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] matriz = new int[7][4];
        System.out.println("Encontrar menor valor e sua posição");
        int menor = 1999999999, linha = 0, coluna = 0;
        for (int i = 0; i < 7; i++) {
            for (int y = 0; y < 4; y++) {
                System.out.println("Entre com um número para a linha " + (i + 1) + ", coluna " + (y + 1));
                matriz[i][y] = scan.nextInt();
                if (matriz[i][y] < menor) {
                    menor = matriz[i][y];
                    linha = i;
                    coluna = y;
                }
            }
        }
        System.out.println("Matriz[7][4]:");
        for (int i = 0; i < 7; i++) {
            System.out.print("[\t");
            for (int y = 0; y < 4; y++) {
                System.out.print(matriz[i][y] + "\t");
            }
            System.out.print("]\n");
        }
        System.out.println("Menor valor contido na matriz: " + menor + ", na linha: " + (linha + 1) + ", coluna: " + (coluna + 1));
    }
}
