package OrdenacaoInsercao;

import java.util.Random;

public class InsercaoSort {

    private int vetor[];
    private static Random numRandom = new Random();

    public InsercaoSort(int tamanho) {
        vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = numRandom.nextInt(90);
        }
    }

    public void sort() {
        int inserir;
        for (int i = 1; i < vetor.length; i++) {
            inserir = vetor[i];
            int moverItem = i;
            while (moverItem > 0 && vetor[moverItem - 1] > inserir) {
                vetor[moverItem] = vetor[moverItem - 1];
                moverItem--;
            }
            vetor[moverItem] = inserir;
            imprimirPassagem(i, moverItem);
        }
    }

    private void imprimirPassagem(int passagem, int indice) {
        System.out.printf(String.format("depois de passar %d: ", passagem));
        for (int i = 0; i < indice; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.print(vetor[indice] + "* ");
        for (int i = indice + 1; i < vetor.length; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.println("\n                        ");
        for (int i = 0; i <= passagem; i++) {
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
