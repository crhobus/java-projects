package View;

import java.util.Scanner;

public class Exe2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Jogadores de basquete");
        String nome = "", jogMaiorId = "", jogMaiorPeso = "", linha = null;
        double altura, peso, maiorAltura = -1, somaAltura = 0, maiorPeso = -1;
        int idade, qtdadeJog = 0, maiorIdade = -1;
        while (true) {
            System.out.println("Entre com o nome ou digite sair para encerrar o programa");
            if (linha != null) {
                scan.nextLine();
            }
            linha = scan.nextLine();
            nome = linha;
            if (nome.equalsIgnoreCase("Sair")) {
                break;
            }
            qtdadeJog++;
            while (true) {
                System.out.println("Entre com a altura");
                altura = scan.nextDouble();
                if (altura >= 0) {
                    break;
                }
                System.out.println("Altura inválida");
            }
            if (altura > maiorAltura) {
                maiorAltura = altura;
            }
            somaAltura += altura;
            while (true) {
                System.out.println("Entre com a idade");
                idade = scan.nextInt();
                if (idade >= 0) {
                    break;
                }
                System.out.println("Idade inválida");
            }
            if (idade > maiorIdade) {
                maiorIdade = idade;
                jogMaiorId = nome;
            }
            while (true) {
                System.out.println("Entre com o peso");
                peso = scan.nextDouble();
                if (peso >= 0) {
                    break;
                }
                System.out.println("Peso inválido");
            }
            if (peso > maiorPeso) {
                maiorPeso = peso;
                jogMaiorPeso = nome;
            }
        }
        if (qtdadeJog != 0) {
            System.out.println("Quantidade de jogadores cadastrados: " + qtdadeJog);
            System.out.println("Altura do maior jogador: " + maiorAltura);
            System.out.println("Jogador mais velho: " + jogMaiorId + ", com a idade " + maiorIdade);
            System.out.println("Jogador mais pesado: " + jogMaiorPeso + ", com a idade " + maiorPeso);
            System.out.println("Média das alturas jogadores: " + (somaAltura / qtdadeJog));
        }
    }
}
