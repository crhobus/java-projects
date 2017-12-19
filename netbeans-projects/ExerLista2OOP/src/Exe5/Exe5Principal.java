package Exe5;

import java.util.Scanner;

import Excecao.ExceptionNumber;

public class Exe5Principal {

    private Scanner scan;
    private Cadastro cadas;

    public Exe5Principal() {
        scan = new Scanner(System.in);
        cadas = new Cadastro();
        String nome, linha = null;
        double somaNotas;
        while (true) {
            System.out.println("Entre com o nome ou digite Sair para encerar");
            if (linha != null) {
                scan.nextLine();
            }
            linha = scan.nextLine();
            nome = linha;
            if (nome.equalsIgnoreCase("Sair")) {
                break;
            }
            Aluno aluno = new Aluno();
            aluno.setNome(nome);
            while (true) {
                try {
                    aluno.setSexo(getSexo());
                    break;
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
            somaNotas = 0;
            for (int i = 1; i <= 4; i++) {
                while (true) {
                    try {
                        somaNotas += getNota(i);
                        break;
                    } catch (ExceptionNumber ex) {
                        System.err.println(ex.getMessage());
                    }
                }
            }
            aluno.setMedia(somaNotas / 4);
            System.out.println("Nome do aluno: " + nome + ", média: " + aluno.getMedia());
            cadas.addAluno(aluno);
        }
        if (!cadas.isVazio()) {
            System.out.println(cadas.getResultados());
        }
    }

    private double getNota(int n) throws ExceptionNumber {
        try {
            System.out.println("Entre com a " + n + "ª nota");
            double nota = Double.parseDouble(scan.next());
            if (nota >= 0 && nota <= 10) {
                return nota;
            }
            throw new ExceptionNumber("Nota inválida entre com um valor de 1 a 10");
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
