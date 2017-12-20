package OrdenacaoIntercalacao;

import java.util.Random;

public class MergeSort {

    private int vetor[];
    private static Random numRandom = new Random();

    public MergeSort(int tamanho) {
        vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = numRandom.nextInt(90);
        }
    }

    public void sort() {
        sortVetor(0, vetor.length - 1);
    }

    private void sortVetor(int menor, int maior) {
        if ((maior - menor) >= 1) {
            int meio1 = (menor + maior) / 2;
            int meio2 = meio1 + 1;
            System.out.println("divisão:      " + subVetor(menor, maior));
            System.out.println("              " + subVetor(menor, meio1));
            System.out.println("              " + subVetor(meio2, maior));
            sortVetor(menor, meio1);
            sortVetor(meio2, maior);
            merge(menor, meio1, meio2, maior);
        }
    }

    private void merge(int esquerda, int meio1, int meio2, int direita) {
        int esquerdaIndice = esquerda;
        int direitaIndice = meio2;
        int combinadoIndice = esquerda;
        int combinado[] = new int[vetor.length];
        System.out.println("merge:  " + subVetor(esquerda, meio1));
        System.out.println("        " + subVetor(meio2, direita));
        while (esquerdaIndice <= meio1 && direitaIndice <= direita) {
            if (vetor[esquerdaIndice] <= vetor[direitaIndice]) {
                combinado[combinadoIndice++] = vetor[esquerdaIndice++];
            } else {
                combinado[combinadoIndice++] = vetor[direitaIndice++];
            }
        }
        if (esquerdaIndice == meio2) {
            while (direitaIndice <= direita) {
                combinado[combinadoIndice++] = vetor[direitaIndice++];
            }
        } else {
            while (esquerdaIndice <= meio1) {
                combinado[combinadoIndice++] = vetor[esquerdaIndice++];
            }
        }
        for (int i = esquerda; i <= direita; i++) {
            vetor[i] = combinado[i];
        }
        System.out.println("          " + subVetor(esquerda, direita));
        System.out.println();
    }

    private String subVetor(int menor, int maior) {
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < menor; i++) {
            temp.append("  ");
        }
        for (int i = menor; i <= maior; i++) {
            temp.append(" " + vetor[i]);
        }
        return temp.toString();
    }

    @Override
    public String toString() {
        return subVetor(0, vetor.length - 1);
    }
}
