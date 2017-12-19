package View;

import java.util.Scanner;

public class Exe1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] matriz = new int[5][6];
        System.out.println("Calculo da média dos valores pares da matriz[5][6]");
        int soma = 0, qtdade = 0;
        for (int i = 0; i < 5; i++) {
            for (int y = 0; y < 6; y++) {
                System.out.println("Entre com um número para a linha " + (i + 1) + ", coluna " + (y + 1));
                matriz[i][y] = scan.nextInt();
                if (matriz[i][y] % 2 == 0) {
                    soma += matriz[i][y];
                    qtdade++;
                }
            }
        }
        System.out.println("Matriz[5][6]:");
        for (int i = 0; i < 5; i++) {
            System.out.print("[\t");
            for (int y = 0; y < 6; y++) {
                System.out.print(matriz[i][y] + "\t");
            }
            System.out.print("]\n");
        }
        if (qtdade == 0) {
            System.out.println("Não há valores pares na matriz[5][6]");
        } else {
            double media = (double) soma / (double) qtdade;
            System.out.println("Média dos valores pares da matriz[5][6]: " + media);
        }
    }
}
