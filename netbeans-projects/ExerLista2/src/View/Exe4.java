package View;

import java.util.Scanner;

public class Exe4 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int candidato1 = 0, candidato2 = 0, candidato3 = 0, candidato4 = 0, nulo = 0, opcao, qtdade = 0, maiorVotacao = -1;
        boolean sair = false;
        String vecedor = "";
        System.out.println("Sistema de votação");
        while (true) {
            System.out.println("Entre com uma das opções ou 0 para sair");
            System.out.println("1 - José Serra");
            System.out.println("2 - Dilma");
            System.out.println("3 - Lula");
            System.out.println("4 - Geraldo Alkmin");
            System.out.println("5 - Nulo");
            opcao = scan.nextInt();
            switch (opcao) {
                case 0:
                    sair = true;
                    break;
                case 1:
                    candidato1++;
                    break;
                case 2:
                    candidato2++;
                    break;
                case 3:
                    candidato3++;
                    break;
                case 4:
                    candidato4++;
                    break;
                case 5:
                    nulo++;
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
            if (sair) {
                break;
            }
            qtdade++;
        }
        if (qtdade != 0) {
            if (candidato1 > maiorVotacao) {
                maiorVotacao = candidato1;
                vecedor = "José Serra";
            }
            if (candidato2 > maiorVotacao) {
                maiorVotacao = candidato2;
                vecedor = "Dilma";
            }
            if (candidato3 > maiorVotacao) {
                maiorVotacao = candidato3;
                vecedor = "Lula";
            }
            if (candidato4 > maiorVotacao) {
                maiorVotacao = candidato4;
                vecedor = "Geraldo Alkmin";
            }
            System.out.println("Vencedor " + vecedor);
            System.out.println(((candidato1 * 100) / qtdade) + "% José Serra");
            System.out.println(((candidato2 * 100) / qtdade) + "% Dilma");
            System.out.println(((candidato3 * 100) / qtdade) + "% Lula");
            System.out.println(((candidato4 * 100) / qtdade) + "% Geraldo Alkmin");
            System.out.println(((nulo * 100) / qtdade) + "% Nulo");
        }
    }
}
