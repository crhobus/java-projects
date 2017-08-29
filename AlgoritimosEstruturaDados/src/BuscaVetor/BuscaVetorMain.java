package BuscaVetor;

import java.util.Random;

public class BuscaVetorMain {

    public static void main(String[] args) {
        BuscaVetor busca = new BuscaVetor();
        int vetor[] = new int[50];
        Random numRandom = new Random();
        System.out.print("Conteúdo do vetor: ");
        for (int i = 0; i < 50; i++) {
            vetor[i] = numRandom.nextInt(40);
            System.out.print(vetor[i] + " ");
        }
        System.out.println();
        System.out.println("Busca vetor no vetor: " + busca.busca(vetor, 34));
        int vetor2[] = new int[10];
        vetor2[0] = 2;
        vetor2[1] = 3;
        vetor2[2] = 5;
        vetor2[3] = 43;
        vetor2[4] = 87;
        vetor2[5] = 88;
        vetor2[6] = 94;
        vetor2[7] = 99;
        vetor2[8] = 900;
        vetor2[9] = 911;
        System.out.print("Conteúdo do vetor 2: ");
        for (int y = 0; y < vetor2.length; y++) {
            System.out.print(vetor2[y] + " ");
        }
        System.out.println();
        System.out.println("Busca linear ordenada no vetor: " + busca.buscaLinearOrdenada(vetor2, 94));
        System.out.println();
    }
}
