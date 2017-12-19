package View;

import java.util.Scanner;

public class Exe5 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] nome = new String[5];
        double[] altura = new double[5];
        double maiorAltura = -1, somaAlturas = 0;
        String jogMaiorAltura = "", linha = null;
        System.out.println("Algoritimo de jogo de basquete entre com os dados de 5 pessoas");
        for (int i = 0; i < nome.length; i++) {
            System.out.println("Entre com os dados da " + (i + 1) + "ª pessoa");
            System.out.println("Entre com o nome ");
            if (linha != null) {
                scan.nextLine();
            }
            linha = scan.nextLine();
            nome[i] = linha;
            while (true) {
                System.out.println("Entre com a altura");
                altura[i] = scan.nextDouble();
                if (altura[i] <= 0) {
                    System.out.println("Altura inválida");
                } else {
                    break;
                }
            }
            somaAlturas += altura[i];
            if (altura[i] > maiorAltura) {
                maiorAltura = altura[i];
                jogMaiorAltura = nome[i];
            }
        }
        System.out.println("Jogador com a maior altura: " + jogMaiorAltura + ", com a altura " + maiorAltura);
        System.out.println("Média das alturas: " + (somaAlturas / nome.length));
    }
}
