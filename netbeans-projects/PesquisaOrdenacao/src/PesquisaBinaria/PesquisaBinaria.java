package PesquisaBinaria;

import java.util.Arrays;
import java.util.Random;

public class PesquisaBinaria {

    private int vetor[];
    private Random numraRandom = new Random();

    public PesquisaBinaria(int tamanho) {
        vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = numraRandom.nextInt(100);
        }
        Arrays.sort(vetor);
    }

    public int buscaBinaria(int num) {
        int menor = 0;
        int maior = vetor.length - 1;
        int meio = (menor + maior + 1) / 2;
        int localizado = -1;
        do {
            System.out.print(elementosRestantes(menor, maior));
            for (int i = 0; i < meio; i++) {
                System.out.print("  ");
            }
            System.out.println(" * ");
            if (num == vetor[meio]) {
                localizado = meio;
            } else {
                if (num < vetor[meio]) {
                    maior = meio - 1;
                } else {
                    menor = meio + 1;
                }
            }
            meio = (menor + maior + 1) / 2;
        } while (menor <= maior && localizado == -1);
        return localizado;
    }

    public String elementosRestantes(int menor, int maior) {
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < menor; i++) {
            temp.append("  ");
        }
        for (int i = menor; i <= maior; i++) {
            temp.append(vetor[i] + " ");
        }
        temp.append("\n");
        return temp.toString();
    }

    @Override
    public String toString() {
        return elementosRestantes(0, vetor.length - 1);
    }
}
