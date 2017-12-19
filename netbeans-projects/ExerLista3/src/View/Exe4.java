package View;

import java.util.Scanner;

public class Exe4 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] nome = new String[20];
        String[] cpf = new String[20];
        double[] altura = new double[20];
        double[] peso = new double[20];
        String[] sexo = new String[20];
        int[] idade = new int[20];
        int contAcimaPeso = 0, qtdadeMulheres = 0, qtdadeMulAcPeso = 0, qtdadePessoasObesas = 0;
        double somaPeso = 0;
        double imc;
        String linha = null;
        System.out.println("Cadastro de 20 pessoas");
        for (int i = 0; i < nome.length; i++) {
            System.out.println("Entre com os dados da " + (i + 1) + "ª pessoa");
            System.out.println("Entre com o nome ");
            if (linha != null) {
                scan.nextLine();
            }
            linha = scan.nextLine();
            nome[i] = linha;
            System.out.println("Entre com o cpf");
            linha = scan.nextLine();
            cpf[i] = linha;
            while (true) {
                System.out.println("Entre com a altura");
                altura[i] = scan.nextDouble();
                if (altura[i] <= 0) {
                    System.out.println("Altura inválida");
                } else {
                    break;
                }
            }
            while (true) {
                System.out.println("Entre com o peso");
                peso[i] = scan.nextDouble();
                if (peso[i] <= 0) {
                    System.out.println("Peso inválido");
                } else {
                    break;
                }
            }
            imc = peso[i] / (altura[i] * altura[i]);
            somaPeso += peso[i];
            if (imc >= 25) {
                contAcimaPeso++;
            }
            while (true) {
                System.out.println("Entre com o sexo M/F");
                if (linha != null) {
                    scan.nextLine();
                }
                linha = scan.nextLine();
                sexo[i] = linha;
                if (sexo[i].equalsIgnoreCase("F")) {
                    qtdadeMulheres++;
                    if (imc <= 18.49) {
                        qtdadeMulAcPeso++;
                    }
                    break;
                } else {
                    if (sexo[i].equalsIgnoreCase("M")) {
                        break;
                    } else {
                        System.out.println("Sexo inválido");
                        linha = null;
                    }
                }
            }
            while (true) {
                System.out.println("Entre com a idade");
                idade[i] = scan.nextInt();
                if (idade[i] < 0) {
                    System.out.println("Idade inválida");
                } else {
                    break;
                }
            }
            if (idade[i] >= 20 && idade[i] <= 30) {
                if (imc >= 30) {
                    qtdadePessoasObesas++;
                }
            }
        }
        System.out.println("Quantidade de pessoas com sobrepeso ou obesas: " + contAcimaPeso);
        System.out.println("Peso médio da população: " + (somaPeso / nome.length));
        if (qtdadeMulheres == 0) {
            System.out.println("Não há mulheres cadastradas");
        } else {
            System.out.println("Percentual de mulheres abaixo do peso: " + ((qtdadeMulAcPeso * 100) / qtdadeMulheres) + "%");
        }
        System.out.println("Quantidade de pessoas entre 20 e 30 anos que estão obesas: " + qtdadePessoasObesas);
    }
}
