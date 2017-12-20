package PesquisaLinear;

import java.util.Random;

public class PesquisaLinear {

    private int vetor[];
    private Random numRandom = new Random();

    public PesquisaLinear(int tamanho) {
        vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = numRandom.nextInt(100);
        }
    }

    public int buscaLinear(int num) {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == num) {
                return i;
            }
        }
        return -1;
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
