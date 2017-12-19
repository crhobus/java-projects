package View;

import java.util.Scanner;

public class Exe1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Cadastro de Pessoas");
        String nome, sexo, linha = null;
        int idade, cargo, contM = 0, contF = 0, gerente = 0, atendente = 0, acougueiro = 0, secretaria = 0, almoxarife = 0, padeiro = 0;
        boolean flag;
        while (true) {
            System.out.println("Entre com o nome ou digite sair para encerar o programa");
            if (linha != null) {
                scan.nextLine();
            }
            linha = scan.nextLine();
            nome = linha;
            if (nome.equalsIgnoreCase("Sair")) {
                break;
            }
            System.out.println("Entre com a idade");
            idade = scan.nextInt();
            while (true) {
                System.out.println("Entre com o sexo: M/F");
                sexo = scan.next();
                if (sexo.equalsIgnoreCase("M")) {
                    contM++;
                    break;
                } else {
                    if (sexo.equalsIgnoreCase("F")) {
                        contF++;
                        break;
                    } else {
                        System.out.println("Sexo inválido");
                    }
                }
            }
            while (true) {
                flag = false;
                System.out.println("Entre com o cargo, as opções são:");
                System.out.println("1 - Gerente");
                System.out.println("2 - Atendente");
                System.out.println("3 - Açougueiro");
                System.out.println("4 - Secretária");
                System.out.println("5 - Almoxarife");
                System.out.println("6 - Padeiro");
                cargo = scan.nextInt();
                switch (cargo) {
                    case 1:
                        gerente++;
                        break;
                    case 2:
                        atendente++;
                        break;
                    case 3:
                        acougueiro++;
                        break;
                    case 4:
                        secretaria++;
                        break;
                    case 5:
                        almoxarife++;
                        break;
                    case 6:
                        padeiro++;
                        break;
                    default:
                        System.out.println("Cargo inválido");
                        flag = true;
                }
                if (!flag) {
                    break;
                }
            }
        }
        System.out.println("Pessoas do sexo masculino: " + contM);
        System.out.println("Pessoas do sexo feminino: " + contF);
        System.out.println("Pessoas do cargo de gerente: " + gerente);
        System.out.println("Pessoas do cargo de atendente: " + atendente);
        System.out.println("Pessoas do cargo de açougueiro: " + acougueiro);
        System.out.println("Pessoas do cargo de secretária: " + secretaria);
        System.out.println("Pessoas do cargo de almoxarife: " + almoxarife);
        System.out.println("Pessoas do cargo de padeiro: " + padeiro);
    }
}
