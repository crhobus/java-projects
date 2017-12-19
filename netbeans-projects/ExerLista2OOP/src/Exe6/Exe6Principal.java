package Exe6;

import java.util.Scanner;

import Excecao.ExceptionNumber;

public class Exe6Principal {

    private Scanner scan;
    private Cadastro cadas;

    public Exe6Principal() {
        scan = new Scanner(System.in);
        cadas = new Cadastro();
        String nome, linha = null;
        System.out.println("Sistema de competição de natação");
        while (true) {
            System.out.println("Entre com o nome ou digite Terminar para encerrar o programa");
            if (linha != null) {
                scan.nextLine();
            }
            linha = scan.nextLine();
            nome = linha;
            if (nome.equalsIgnoreCase("Terminar")) {
                break;
            }
            Competidor competidor = new Competidor();
            competidor.setNome(nome);
            while (true) {
                try {
                    competidor.setIdade(getIdade());
                    break;
                } catch (ExceptionNumber ex) {
                    System.err.println(ex.getMessage());
                }
            }
            while (true) {
                try {
                    competidor.setSexo(getSexo());
                    break;
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
            cadas.addCompetidor(competidor);
        }
        if (!cadas.isVazio()) {
            System.out.println(cadas.getResultados());
        }
    }

    private int getIdade() throws ExceptionNumber {
        try {
            System.out.println("Entre com a idade");
            int idade = Integer.parseInt(scan.next());
            if (idade >= 0 && idade <= 6) {
                throw new ExceptionNumber("Não poderá participar");
            }
            if (idade > 6 && idade <= 30) {
                return idade;
            }
            if (idade > 30) {
                throw new ExceptionNumber("Idade inválida para participar da competição");
            }
            throw new ExceptionNumber("Idade inválida, entre novamente");
        } catch (NumberFormatException ex) {
            throw new ExceptionNumber();
        }
    }

    private String getSexo() throws Exception {
        System.out.println("Entre com o sexo: M/F");
        String sexo = scan.next();
        if (sexo.equalsIgnoreCase("M") || sexo.equalsIgnoreCase("F")) {
            return sexo;
        }
        throw new Exception("Sexo inválido");
    }
}
