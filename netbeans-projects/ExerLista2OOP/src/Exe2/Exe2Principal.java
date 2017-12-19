package Exe2;

import java.util.Scanner;

import Excecao.ExceptionNumber;

public class Exe2Principal {

    private Scanner scan;
    private Cadastro cadas;

    public Exe2Principal() {
        scan = new Scanner(System.in);
        cadas = new Cadastro();
        System.out.println("Jogadores de basquete");
        String nome = "", linha = null;
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
            Jogador jogador = new Jogador();
            jogador.setNome(nome);
            while (true) {
                try {
                    jogador.setAltura(getAltura());
                    break;
                } catch (ExceptionNumber ex) {
                    System.err.println(ex.getMessage());
                }
            }
            while (true) {
                try {
                    jogador.setIdade(getIdade());
                    break;
                } catch (ExceptionNumber ex) {
                    System.err.println(ex.getMessage());
                }
            }
            while (true) {
                try {
                    jogador.setPeso(getPeso());
                    break;
                } catch (ExceptionNumber ex) {
                    System.err.println(ex.getMessage());
                }
            }
            cadas.addJogador(jogador);
        }
        if (!cadas.isVazio()) {
            System.out.println(cadas.getQtdadeCadas());
            System.out.println(cadas.getMaiorAltura());
            System.out.println(cadas.getMaiorIdade());
            System.out.println(cadas.getMaiorPeso());
            System.out.println(cadas.getMediaAltura());
        }
    }

    private double getAltura() throws ExceptionNumber {
        return getDouble("Entre com a altura", "Altura inválida");
    }

    private double getPeso() throws ExceptionNumber {
        return getDouble("Entre com o Peso", "Peso inválido");
    }

    private double getDouble(String msg, String erro) throws ExceptionNumber {
        try {
            System.out.println(msg);
            double altura = Double.parseDouble(scan.next());
            if (altura >= 0) {
                return altura;
            }
            throw new ExceptionNumber(erro);
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
