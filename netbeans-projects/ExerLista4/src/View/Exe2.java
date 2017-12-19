package View;

import java.util.Scanner;

public class Exe2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] matriz = new int[5][6];
        System.out.println("Cálculo numa matriz[5][5]");
        int somaQuaLinha = 0, somaSegColuna = 0, somaDigPrin = 0, somaDigSec = 0, j = 4, soma = 0;
        for (int i = 0; i < 5; i++) {
            for (int y = 0; y < 5; y++) {
                System.out.println("Entre com um número para a linha " + (i + 1) + ", coluna " + (y + 1));
                matriz[i][y] = scan.nextInt();
                if (i == 3) {
                    somaQuaLinha += matriz[i][y];
                }
                if (y == 1) {
                    somaSegColuna += matriz[i][y];
                }
                if (i == y) {
                    somaDigPrin += matriz[i][y];
                }
                if (j == y) {
                    somaDigSec += matriz[i][y];
                }
                soma += matriz[i][y];
            }
            j--;
        }
        System.out.println("Soma da quarta linha: " + somaQuaLinha);
        System.out.println("Soma segunda coluna: " + somaSegColuna);
        System.out.println("Soma diagonal principal: " + somaDigPrin);
        System.out.println("Soma diagonal secúndária: " + somaDigSec);
        System.out.println("Soma de todos os elementos da matriz: " + soma);
        System.out.println("Elementos que compõem a matriz:");
        System.out.println("Matriz[5][5]:");
        for (int i = 0; i < 5; i++) {
            System.out.print("[\t");
            for (int y = 0; y < 5; y++) {
                System.out.print(matriz[i][y] + "\t");
            }
            System.out.print("]\n");
        }
    }
}
