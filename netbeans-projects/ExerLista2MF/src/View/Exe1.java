package View;

import java.util.Scanner;

public class Exe1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String nome, linha = null, nomeMaiorSal = "";
        int idade, horasTrab, horasTrab50, horasTrab100, cargo, qtdade = 0, gerente = 0, secretaria = 0,
                programador = 0, anSistemas = 0, webDesigner = 0, suporte = 0, idade1 = 0, idade2 = 0, idade3 = 0, idade4 = 0;
        double valorHora, salarioBruto, descInss, descImpRenda, descValTrans, salarioLiquido, valorMaiorSal = -1;
        boolean invalido;
        System.out.println("Sistema Cadastro de Funcionários");
        while (true) {
            System.out.println("Entre com o nome do funcionário");
            if (linha != null) {
                scan.nextLine();
            }
            linha = scan.nextLine();
            nome = linha;
            if (nome.equalsIgnoreCase("Sair")) {
                break;
            }
            qtdade++;
            while (true) {
                System.out.println("Entre com a idade");
                idade = scan.nextInt();
                if (idade >= 16) {
                    break;
                } else {
                    System.out.println("Idade inválida");
                }
            }
            if (idade >= 16 && idade <= 26) {
                idade1++;
            } else {
                if (idade >= 27 && idade <= 40) {
                    idade2++;
                } else {
                    if (idade >= 41 && idade <= 50) {
                        idade3++;
                    } else {
                        idade4++;
                    }
                }
            }
            while (true) {
                invalido = false;
                System.out.println("Entre com o cargo abaixo, as opções são:");
                System.out.println("1 - Gerente");
                System.out.println("2 - Secretária");
                System.out.println("3 - Programador");
                System.out.println("4 - Analista de Sistemas");
                System.out.println("5 - Web-Designer");
                System.out.println("6 - Suporte");
                cargo = scan.nextInt();
                switch (cargo) {
                    case 1:
                        gerente++;
                        break;
                    case 2:
                        secretaria++;
                        break;
                    case 3:
                        programador++;
                        break;
                    case 4:
                        anSistemas++;
                        break;
                    case 5:
                        webDesigner++;
                        break;
                    case 6:
                        suporte++;
                        break;
                    default:
                        System.out.println("Cargo inválido entre novamente");
                        invalido = true;
                        break;
                }
                if (!invalido) {
                    break;
                }
            }
            while (true) {
                System.out.println("Entre com o valor da hora trabalhada");
                valorHora = scan.nextDouble();
                if (valorHora >= 0) {
                    break;
                } else {
                    System.out.println("Valor inválido entre novamente");
                }
            }
            while (true) {
                System.out.println("Entre com a quantidade de horas trabalhas normalmente");
                horasTrab = scan.nextInt();
                if (horasTrab >= 0) {
                    break;
                } else {
                    System.out.println("Quantidade inválida entre novamente");
                }
            }
            while (true) {
                System.out.println("Entre com a quantidade de horas trabalhas com acréscimo de 50%");
                horasTrab50 = scan.nextInt();
                if (horasTrab50 >= 0) {
                    break;
                } else {
                    System.out.println("Quantidade inválida entre novamente");
                }
            }
            while (true) {
                System.out.println("Entre com a quantidade de horas trabalhas com acréscimo de 100%");
                horasTrab100 = scan.nextInt();
                if (horasTrab100 >= 0) {
                    break;
                } else {
                    System.out.println("Quantidade inválida entre novamente");
                }
            }
            salarioBruto = (valorHora * horasTrab) + ((valorHora * 1.5) * horasTrab50) + ((valorHora * 2) * horasTrab100);
            System.out.println("Salário bruto: " + salarioBruto);
            if (salarioBruto <= 2400) {
                descInss = salarioBruto * 0.08;
            } else {
                descInss = salarioBruto * 0.11;
            }
            System.out.println("Desconto INSS: " + descInss);
            descValTrans = salarioBruto * 0.06;
            System.out.println("Desconto Vale Transporte: " + descValTrans);
            descImpRenda = 0;
            if (salarioBruto >= 1500 && salarioBruto <= 2500) {
                descImpRenda = salarioBruto * 0.07;
            } else {
                if (salarioBruto > 2500 && salarioBruto <= 3000) {
                    descImpRenda = salarioBruto * 0.11;
                } else {
                    if (salarioBruto > 3000) {
                        descImpRenda = salarioBruto * 0.25;
                    }
                }
            }
            System.out.println("Desconto Imposto de Renda: " + descImpRenda);
            salarioLiquido = salarioBruto - descInss - descValTrans - descImpRenda;
            System.out.println("Salário líquido: " + salarioLiquido);
            if (salarioLiquido > valorMaiorSal) {
                valorMaiorSal = salarioLiquido;
                nomeMaiorSal = nome;
            }
        }
        if (qtdade != 0) {
            System.out.println("Número de funcionários que foram cadastrados: " + qtdade);
            System.out.println("Número de funcionários com idade entre 16 á 26 anos: " + idade1);
            System.out.println("Número de funcionários com idade entre 27 á 40 anos: " + idade2);
            System.out.println("Número de funcionários com idade entre 41 á 50 anos: " + idade3);
            System.out.println("Número de funcionários com idade superior a 50 anos: " + idade4);
            System.out.println("Número de funcionários com os cargos de Gerente: " + gerente);
            System.out.println("Número de funcionários com os cargos de Secretária: " + secretaria);
            System.out.println("Número de funcionários com os cargos de Programador: " + programador);
            System.out.println("Número de funcionários com os cargos de Analista de Sistemas: " + anSistemas);
            System.out.println("Número de funcionários com os cargos de Web-Designer: " + webDesigner);
            System.out.println("Número de funcionários com os cargos de Suporte: " + suporte);
            System.out.println("Funcionário com o maior salário líquido: " + nomeMaiorSal + ", valor do salário R$ " + valorMaiorSal);
        }
    }
}
