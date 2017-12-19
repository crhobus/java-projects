package Exe1;

import java.util.Scanner;

import Excecao.ExceptionNumber;

public class Exe1Principal {

    private Scanner scan;
    private Cadastro cadas;

    public Exe1Principal() {
        scan = new Scanner(System.in);
        cadas = new Cadastro();
        System.out.println("Cadastro de Pessoas");
        String nome, linha = null;
        while (true) {
            Pessoa pessoa = new Pessoa();
            System.out.println("Entre com o nome ou digite sair para encerar o programa");
            if (linha != null) {
                scan.nextLine();
            }
            linha = scan.nextLine();
            nome = linha;
            if (nome.equalsIgnoreCase("Sair")) {
                break;
            }
            pessoa.setNome(nome);
            while (true) {
                try {
                    pessoa.setIdade(getIdade());
                    break;
                } catch (ExceptionNumber ex) {
                    System.err.println(ex.getMessage());
                }
            }
            while (true) {
                try {
                    pessoa.setSexo(getSexo());
                    break;
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
            while (true) {
                try {
                    pessoa.setCargo(getCargo());
                    break;
                } catch (ExceptionNumber ex) {
                    System.err.println(ex.getMessage());
                }
            }
            cadas.addPessoa(pessoa);
        }
        System.out.println("Pessoas do sexo masculino: " + cadas.getMasculino());
        System.out.println("Pessoas do sexo feminino: " + cadas.getFeminino());
        System.out.println("Pessoas do cargo de gerente: " + cadas.getGerente());
        System.out.println("Pessoas do cargo de atendente: " + cadas.getAtendente());
        System.out.println("Pessoas do cargo de açougueiro: " + cadas.getAcougueiro());
        System.out.println("Pessoas do cargo de secretária: " + cadas.getSecretaria());
        System.out.println("Pessoas do cargo de almoxarife: " + cadas.getAlmoxarife());
        System.out.println("Pessoas do cargo de padeiro: " + cadas.getPadeiro());
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

    private int getCargo() throws ExceptionNumber {
        try {
            System.out.println("Entre com o cargo, as opções são:");
            System.out.println(cadas.getCargos());
            int cargo = Integer.parseInt(scan.next());
            if (cargo >= 1 && cargo <= 6) {
                return cargo;
            }
            throw new ExceptionNumber("Opção inválida, entre novamente");
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
