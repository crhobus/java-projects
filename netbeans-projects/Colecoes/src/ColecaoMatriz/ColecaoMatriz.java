package ColecaoMatriz;

import java.util.Random;

public class ColecaoMatriz {

    private int matriz[][];
    private int matrizTransposta[][];
    private int qtdade;

    public ColecaoMatriz(int limite) {
        qtdade = limite;
        matriz = new int[limite][limite];
        matrizTransposta = new int[limite][limite];
    }

    public boolean carregar(int quantos) {
        Random numRandomico = new Random();
        if (quantos <= matriz.length) {
            for (int i = 0; i < quantos; i++) {
                for (int j = 0; j < quantos; j++) {
                    matriz[i][j] = numRandomico.nextInt(50);
                }
            }
            carregarTransposta();
            qtdade = quantos;
            return true;
        }
        return false;
    }

    public boolean carregarTransposta() {
        int[][] matriz_auxiliar = new int[this.qtdade][this.qtdade];
        if (qtdade != 0) {
            for (int col = 0; col < qtdade; col++) {
                for (int lin = 0; lin < qtdade; lin++) {
                    matriz_auxiliar[lin][col] = matriz[col][lin];
                }
            }
            matrizTransposta = matriz_auxiliar;
            return true;
        }
        return false;
    }

    public boolean mostrar(boolean quem) {
        if (this.qtdade > 0) { // tem elementos
            if (quem == true) {
                System.out.println("1==> ELEMENTOS DA MATRIZ NORMAL ");
                for (int i = 0; i < qtdade; i++) {
                    for (int j = 0; j < qtdade; j++) {
                        System.out.print(" " + matriz[i][j]);
                    }
                    System.out.println("\n");
                }
                return true;
            } else {
                System.out.println("2==> ELEMENTOS DA MATRIZ TRANSPOSTA ");
                for (int i = 0; i < qtdade; i++) {
                    for (int j = 0; j < qtdade; j++) {
                        System.out.print(" " + matrizTransposta[i][j]);
                    }
                    System.out.println("\n");
                }
                return true;
            }
        }
        return false;
    }

    public void ordenar(int linha) {
        int menor = 0;
        for (int i = 0; i <= qtdade; i++) {
            for (int j = i + 1; j <= qtdade; j++) {
                if (matriz[linha][i] > matriz[linha][j]) {
                    menor = matriz[linha][i];
                    matriz[linha][i] = matriz[linha][j];
                    matriz[linha][j] = menor;
                }
            }
        }
        carregarTransposta();
        System.out.println("A MATRIZ Normal foi ordenada: " + linha);
        mostrar(true);
    }

    public int set(int linha, int coluna, int quem) {
        matriz[linha][coluna] = quem;
        return matrizTransposta[linha][coluna] = quem;
    }

    public int get(int linha, int coluna) {
        return matriz[linha][coluna];
    }

    public int menor() {
        int i, j, menor = matriz[0][0];
        for (i = 0; i < qtdade; i++) {
            for (j = 0; j < qtdade; j++) {
                if (matriz[i][j] < menor) {
                    menor = matriz[i][j];
                }
            }
        }
        return menor;
    }

    public int somatorio() {
        int soma = 0;
        int i, j;
        for (i = 0; i < qtdade; i++) {
            for (j = 0; j < qtdade; j++) {
                soma = soma + matriz[i][j];
            }
        }
        return soma;
    }

    public int somalinha(int linha) {
        int soma = 0;
        int i;
        for (i = 0; i < qtdade; i++) {
            soma = soma + matriz[linha][i];
        }
        return soma;
    }

    public int somacoluna(int coluna) {
        int soma = 0;
        int j;
        for (j = 0; j < qtdade; j++) {
            soma = soma + matriz[coluna][j];
        }
        return soma;
    }

    public int somadiagonal() {
        int soma = 0;
        int i, j;
        for (i = 0; i < qtdade; i++) {
            for (j = 0; j < qtdade; j++) {
                if (i == j) {
                    soma = soma + matriz[i][j];
                }
            }
        }
        return soma;
    }
}
