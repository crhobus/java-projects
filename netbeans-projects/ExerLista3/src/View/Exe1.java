package View;

import java.util.Scanner;

public class Exe1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] vet = new int[5];
        int soma = 0, qtdadeMaior = 0, negativo = 0, positivo = 0;
        double media;
        for (int i = 0; i < vet.length; i++) {
            System.out.println("Entre com o " + (i + 1) + "º elemento");
            vet[i] = scan.nextInt();
            soma += vet[i];
        }
        media = (double) soma / vet.length;
        for (int i = 0; i < vet.length; i++) {
            if (vet[i] >= media) {
                qtdadeMaior++;
            }
            if (vet[i] >= 0) {
                positivo++;
            } else {
                negativo++;
            }
        }
        System.out.println("Soma dos elementos: " + soma);
        System.out.println("Média dos elementos: " + media);
        System.out.println("Quantidade de elementos iguais ou maiores que a média: " + qtdadeMaior);
        System.out.println("Porcentagem dos elementos que são iguais ou maiores que a média: " + ((qtdadeMaior * 100) / vet.length) + "%");
        System.out.println("Quantidade de elementos positivos: " + positivo);
        System.out.println("Quantidade de elementos negativos: " + negativo);
    }
}
