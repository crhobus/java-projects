package ArrayExercicio;

import java.util.Random;

public class ArrayExe {

    private int vetor[];
    private int quantidade;

    public ArrayExe(int numero) {
        quantidade = 0;
        vetor = new int[numero];
    }

    public boolean carregar(int qts) {
        int aux;
        Random numRandomico = new Random();
        if (quantidade <= vetor.length) {
            for (aux = 0; aux < qts; aux++) {
                vetor[aux] = numRandomico.nextInt(99);
            }
            quantidade = qts;
            return true;
        }
        return false;
    }

    public void mostrar() {
        int aux;
        System.out.println("\nVEJA O MEU CONTEÚDO: ");
        if (quantidade > 0) {
            for (aux = 0; aux < quantidade; aux++) {
                System.out.print(" " + vetor[aux]);
            }

        } else {
            System.out.println("Não tem ninguem");
        }

    }

    public int somatorio() {
        int soma = 0;
        int aux;
        for (aux = 0; aux < quantidade; aux++) {
            soma += vetor[aux];
        }
        return soma;
    }

    public int qtdPrimos() {
        int conta = 0, i, aux, ehprimo;
        for (i = 0; i < quantidade; i++) {
            ehprimo = 1;
            for (aux = vetor[i] - 1; aux > 1; aux--) {
                if (((vetor[i]) % aux) == 0) {
                    ehprimo = 0;
                    aux = 1;
                }
            }
            conta = conta + ehprimo;
        }
        return conta;
    }
}
