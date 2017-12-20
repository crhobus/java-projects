package OrdenacaoSelecao;

import java.util.Random;

public class SelecaoSort {

    private int vetor[];
    private static Random numRandom = new Random();

    public SelecaoSort(int tamanho) {
        vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = numRandom.nextInt(100);
        }
    }

    public void sort() {
        int menor;
        for (int i = 0; i < vetor.length - 1; i++) {
            menor = i;
            for (int y = i + 1; y < vetor.length; y++) {
                if (vetor[y] < vetor[menor]) {
                    menor = y;
                }
            }
            troca(i, menor);
            imprimirPassagem(i + 1, menor);
        }
    }

    private void troca(int primeiro, int segundo) {
        int temp = vetor[primeiro];
        vetor[primeiro] = vetor[segundo];
        vetor[segundo] = temp;
    }

    private void imprimirPassagem(int passagem, int indece) {
        System.out.print(String.format("depois de passar %2d: ", passagem));
        for (int i = 0; i < indece; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.print(vetor[indece] + "* ");
        for (int i = indece + 1; i < vetor.length; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.print("\n                 ");
        for (int i = 0; i < passagem; i++) {
            System.out.print("-- ");
        }
        System.out.println("\n");
    }

    @Override
    public String toString() {
        StringBuffer temp = new StringBuffer();
        for (int num : vetor) {
            temp.append(num + " ");
        }
        temp.append("\n");
        return temp.toString();
    }
}
