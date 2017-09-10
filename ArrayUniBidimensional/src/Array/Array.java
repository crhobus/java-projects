package Array;

import java.util.Random;

public class Array {

    public static void main(String[] args) {
        Random meusNums;
        int vetor[], i, somatorio = 0, qtdPrimos = 0, ehprimo, aux;
        meusNums = new Random();
        vetor = new int[20];
        for (i = 0; i < 20; i++) {
            vetor[i] = meusNums.nextInt(99);
        }
        for (i = 0; i < 20; i++) {
            somatorio += vetor[i];
            ehprimo = 1;
            for (aux = vetor[i] - 1; aux > 1; aux--) {
                if ((vetor[i] % aux) == 0) {
                    ehprimo = 0;
                    aux = 1;
                }
            }
            qtdPrimos += ehprimo;
        }
        System.out.println("SOMATORIO: " + somatorio);
        System.out.println("PRIMOS IDENTIFICADOS: " + qtdPrimos);
    }
}
