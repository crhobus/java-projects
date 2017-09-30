package Ordenacao;

import java.util.*;

public class MainOrdenacao {

    public static void main(String[] args) {
        Ordenacao ordena = new Ordenacao();
        int tamanhoVetor = 10;
        int limiteRandom = 500;

        //Ordenando com BubbleSort
        int vetor[] = new int[tamanhoVetor];
        Random numRandom = new Random();
        System.out.print("Conteúdo do vetor: ");
        for (int i = 0; i < tamanhoVetor; i++) {
            vetor[i] = numRandom.nextInt(limiteRandom);
            System.out.print(vetor[i] + " ");
        }
        long tempoBubbleSort = System.currentTimeMillis();
        System.out.println();
        int vetorOdenadoBubbleSort[] = new int[tamanhoVetor];
        vetorOdenadoBubbleSort = ordena.bubbleSort(vetor);
        System.out.print("Conteúdo do vetor ordenado usando BubbleSort: ");
        for (int i = 0; i < tamanhoVetor; i++) {
            System.out.print(vetorOdenadoBubbleSort[i] + " ");
        }
        System.out.println("\nTempo de exeução BubbleSort: " + (System.currentTimeMillis() - tempoBubbleSort));
        System.out.println();

        //Ordenando com QuickSort
        int vetor2[] = new int[tamanhoVetor];
        Random numRandom2 = new Random();
        System.out.print("Conteúdo do vetor 2: ");
        for (int i = 0; i < tamanhoVetor; i++) {
            vetor2[i] = numRandom2.nextInt(limiteRandom);
            System.out.print(vetor2[i] + " ");
        }
        long tempoQuickSort = System.currentTimeMillis();
        System.out.println();
        ordena.quickSort(vetor2);
        System.out.print("Conteúdo do vetor 2 ordenado usando QuickSort: ");
        for (int i = 0; i < tamanhoVetor; i++) {
            System.out.print(vetor2[i] + " ");
        }
        System.out.println("\nTempo de execução QuickSort : " + (System.currentTimeMillis() - tempoQuickSort));
        System.out.println();

        //Ordenando com MergeSort
        int vetor3[] = new int[tamanhoVetor];
        Random numRandom3 = new Random();
        System.out.print("Conteúdo do vetor 3: ");
        for (int i = 0; i < tamanhoVetor; i++) {
            vetor3[i] = numRandom3.nextInt(limiteRandom);
            System.out.print(vetor3[i] + " ");
        }
        long tempoMergeSort = System.currentTimeMillis();
        System.out.println();
        ordena.mergeSort(vetor3, 0, vetor3.length - 1);
        System.out.print("Conteúdo do vetor 3 ordenado usando mergeSort: ");
        for (int i = 0; i < tamanhoVetor; i++) {
            System.out.print(vetor3[i] + " ");
        }
        System.out.println("\nTempo de execução MergeSort : " + (System.currentTimeMillis() - tempoMergeSort));
        System.out.println();
    }
}
