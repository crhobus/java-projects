package Exe4;

import java.util.Scanner;

import Excecao.ExceptionNumber;

public class Exe4Principal {

    private Scanner scan;
    private Votacao votacao;

    public Exe4Principal() {
        scan = new Scanner(System.in);
        votacao = new Votacao();
        int opcao;
        System.out.println("Sistema de votação");
        while (true) {
            while (true) {
                try {
                    opcao = getOpcao();
                    break;
                } catch (ExceptionNumber ex) {
                    System.err.println(ex.getMessage());
                }
            }
            if (opcao == 0) {
                break;
            }
            votacao.votar(opcao);
        }
        System.out.println(votacao.getResultadoVotacao());
    }

    private int getOpcao() throws ExceptionNumber {
        try {
            System.out.println("Entre com uma das opções ou digite 0 para sair");
            System.out.println(votacao.getCandidatos());
            int opcao = Integer.parseInt(scan.next());
            if (opcao >= 0 && opcao <= 5) {
                return opcao;
            }
            throw new ExceptionNumber("Opção inválida, entre novamente");
        } catch (NumberFormatException ex) {
            throw new ExceptionNumber();
        }
    }
}
