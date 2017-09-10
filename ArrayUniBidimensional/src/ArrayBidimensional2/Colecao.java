package ArrayBidimensional2;

import java.util.Random;

public class Colecao {

    private int matriz[][], mTrans[][], qtd;

    public Colecao(int i) {
        matriz = new int[i][i];
        mTrans = new int[i][i];
        qtd = i;
    }

    public boolean carregar(int i) {
        int lin, col;
        boolean carrega = true;
        Random num = new Random();
        if (i >= 0 && i <= qtd) {
            for (lin = 0; lin < i; lin++) {
                for (col = 0; col < i; col++) {
                    matriz[lin][col] = num.nextInt(10);
                }
            }
        } else {
            carrega = false;
        }
        CarregarTrans(i);
        return carrega;
    }

    public boolean CarregarTrans(int i) {
        int lin, col;
        boolean carrega = true;
        if (i >= 0 && i <= qtd) {
            for (lin = 0; lin < i; lin++) {
                for (col = 0; col < i; col++) {
                    mTrans[col][lin] = matriz[lin][col];
                }
            }
        } else {
            carrega = false;
        }
        return carrega;
    }

    public void mostrar(String qual) {
        boolean mostrar = false;
        int lin, col;
        while (mostrar == false) {
            if (qual == "Normal") {
                for (lin = 0; lin < qtd; lin++) {
                    for (col = 0; col < qtd; col++) {
                        System.out.print(matriz[lin][col] + " ");
                    }
                    System.out.print("\n");
                }
            }
            if (qual == "Transversa") {
                for (lin = 0; lin < qtd; lin++) {
                    for (col = 0; col < qtd; col++) {
                        System.out.print(mTrans[lin][col] + " ");
                    }
                    System.out.print("\n");
                }
            }
            mostrar = true;
            System.out.print("True or false? " + mostrar + "\n");
        }
    }

    public void ordenar(int lin) {
        int caixab = 0;
        int caixaa = 0;
        for (int a = 0; a < matriz.length; a++) {
            for (int b = 0; b < matriz.length; b++) {
                System.out.println("gggggggggggggggggggggggggggggggggggggggggg");
                if (matriz[lin][a] < matriz[lin][b]) {
                    caixaa = matriz[lin][b];
                    caixab = matriz[lin][a];
                    matriz[lin][b] = caixab;
                    matriz[lin][a] = caixaa;

                }
            }

        }
    }

    public int get(int a, int b) {
        int numero = 0;
        if (a <= qtd && b <= qtd) {
            numero = matriz[a][b];
        }
        return numero;
    }

    public int set(int set, int a, int b) {
        if (set >= 0) {
            matriz[a][b] = set;
        }
        CarregarTrans(qtd);
        return set;
    }

    public int menor() {
        int menor = 9999, a, b;
        for (a = 0; a < matriz.length; a++) {
            for (b = 0; b < matriz.length; b++) {
                if (menor > matriz[a][b]) {
                    menor = matriz[a][b];
                }
            }
        }
        return menor;
    }

    public int somatorio() {
        int soma = 0, a, b;
        for (a = 0; a < matriz.length; a++) {
            for (b = 0; b < qtd; b++) {
                soma += matriz[a][b];
            }
        }
        return soma;
    }

    public int SomaLinha(int lin) {
        int soma = 0, a;
        for (a = 0; a < matriz.length; a++) {
            soma += matriz[lin][a];
        }
        return soma;
    }

    public int SomaColuna(int coluna) {
        int somacoluna = 0;
        for (int a = 0; a < matriz.length; a++) {
            somacoluna += matriz[a][coluna];
        }
        return somacoluna;
    }

    public int SomaDiagonal() {
        int soma = 0, a;
        for (a = 0; a < qtd; a++) {
            soma += matriz[a][a];
        }
        return soma;
    }
}
