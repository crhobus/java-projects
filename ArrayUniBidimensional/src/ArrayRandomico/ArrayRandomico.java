package ArrayRandomico;

import java.util.Random;

public class ArrayRandomico {

    public static void main(String[] args) {
        int i, aux[], soma, media, posicao = 0, maior = -1, somapar = 0,
                quantospar = 0, somapares = 0, menor = 31, fatorial = 1;
        aux = new int[20];
        Random numRandomico = new Random();
        for (i = 0; i < 20; i++) {
            aux[i] = numRandomico.nextInt(60);
            System.out.println("NUMERO GERADO = " + aux[i]);
        }
        soma = 0;
        for (i = 0; i < 20; i++) {
            soma += aux[i];
        }
        media = soma / 20;
        System.out.println("Média Aritimética dos numeros gerados : " + media);
        for (i = 0; i < 20; i++) {
            if (aux[i] > maior) {
                maior = aux[i];
                posicao = i;
            }
        }
        System.out.println("posição do numero é: " + posicao);
        System.out.println("O maior numero que esta nesta posição é: " + maior);
        for (i = 0; i < 20; i++) {
            if ((aux[i] % 2) != 0) {
                somapar += aux[i];
                quantospar++;
            }
        }

        System.out.println("Média Aritimética dos numeros ímpares: " + somapar / quantospar);
        for (i = 0; i < 20; i++) {
            if ((i % 2) == 0) {
                somapares += aux[i];
            }
        }
        media = somapares / 10;
        System.out.println("Média dos numeros das posições pares: "
                + media);
        for (i = 0; i < 20; i++) {
            if (aux[i] < menor) {
                menor = aux[i];
            }
        }
        fatorial = 1;
        for (i = menor; i > 1; i--) {
            fatorial = fatorial * i;
        }

        System.out.println("Menor Numero: " + menor);
        System.out.println("seu Fatorial: " + fatorial);
    }
}
