package ArrayBidimensional;

import java.util.Random;

public class ColecaoNumerosInteiros {

    private int elementos[][];
    private int ate;

    public ColecaoNumerosInteiros(int limite) {
        ate = limite;
        elementos = new int[4][4];
    }

    public void carregar() {
        int i, j;
        Random numRandomico = new Random();
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                elementos[i][j] = numRandomico.nextInt(ate);
            }
        }
    }

    public void mostrar() {
        int i, j;
        System.out.println("VEJA A MATRIZ: ");
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                System.out.print(" " + elementos[i][j]);
            }
            System.out.println("\n");
        }
    }

    public int get(int linha, int coluna) {
        return elementos[linha][coluna];
    }

    public int set(int linha, int coluna, int quem) {
        return elementos[linha][coluna] = quem;
    }

    public int getSoma() {
        int soma = 0;
        int i, j;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                soma = soma + elementos[i][j];
            }
        }
        return soma;
    }

    public int getMaior() {
        int i, j, maior = elementos[0][0];
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                if (elementos[i][j] > maior) {
                    maior = elementos[i][j];
                }
            }
        }
        return maior;
    }

    public int getSomaLinha(int linha) {
        int soma = 0;
        int i;
        for (i = 0; i < 4; i++) {
            soma = soma + elementos[linha][i];
        }
        return soma;
    }

    public int getSomaColuna(int coluna) {
        int soma = 0;
        int j;
        for (j = 0; j < 4; j++) {
            soma = soma + elementos[coluna][j];
        }
        return soma;
    }
}
