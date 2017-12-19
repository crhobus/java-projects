package Exe3;

import java.util.Scanner;

import Excecao.ExceptionNumber;

public class Exe3Principal {

    private Scanner scan;
    private Enquete enquete;

    public Exe3Principal() {
        scan = new Scanner(System.in);
        enquete = new Enquete();
        int opcao, idade;
        String resp;
        System.out.println("Enquete sobre o filme Alice no país das maravilhas");
        while (true) {
            System.out.println("digite 's' para realizar a enquete ou 'n' para encerrar");
            resp = scan.next();
            if (resp.equalsIgnoreCase("s")) {
                while (true) {
                    try {
                        opcao = getOpcao();
                        break;
                    } catch (ExceptionNumber ex) {
                        System.err.println(ex.getMessage());
                    }
                }
                while (true) {
                    try {
                        idade = getIdade();
                        break;
                    } catch (ExceptionNumber ex) {
                        System.err.println(ex.getMessage());
                    }
                }
                enquete.optar(opcao, idade);
            } else {
                if (resp.equalsIgnoreCase("n")) {
                    break;
                } else {
                    System.out.println("Opção inválida");
                }
            }
        }
        if (!enquete.isVazio()) {
            System.out.println(enquete.getExcelente());
            System.out.println(enquete.getOtimo());
            System.out.println(enquete.getBom());
            System.out.println(enquete.getRegular());
            System.out.println(enquete.getRuim());
            System.out.println("Faixa etária:");
            System.out.println(enquete.getPriFaixaEtaria());
            System.out.println(enquete.getSegFaixaEtaria());
            System.out.println(enquete.getTerFaixaEtaria());
        }
    }

    private int getOpcao() throws ExceptionNumber {
        try {
            System.out.println("Entre com uma das opções");
            System.out.println(enquete.getOpcoes());
            int opcao = Integer.parseInt(scan.next());
            if (opcao >= 1 && opcao <= 5) {
                return opcao;
            }
            throw new ExceptionNumber("Opção inválida, entre novamente");
        } catch (NumberFormatException ex) {
            throw new ExceptionNumber();
        }
    }

    private int getIdade() throws ExceptionNumber {
        try {
            System.out.println("Entre com a idade");
            int idade = Integer.parseInt(scan.next());
            if (idade > 0) {
                return idade;
            }
            throw new ExceptionNumber("Idade inválida, entre novamente");
        } catch (NumberFormatException ex) {
            throw new ExceptionNumber();
        }
    }
}
