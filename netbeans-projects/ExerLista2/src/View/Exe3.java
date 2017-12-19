package View;

import java.util.Scanner;

public class Exe3 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int opcao, excelente = 0, otimo = 0, bom = 0, regular = 0, ruim = 0, idade, idade1 = 0, idade2 = 0, idade3 = 0, qtdade = 0;
        boolean invalido;
        String resp;
        System.out.println("Enquete sobre o filme Alice no país das maravilhas");
        while (true) {
            System.out.println("digite 's' para realizar a enquete ou 'n' para encerrar");
            resp = scan.next();
            if (resp.equalsIgnoreCase("s")) {
                while (true) {
                    invalido = false;
                    System.out.println("Entre com uma das opções");
                    System.out.println("1 - Excelente");
                    System.out.println("2 - Ótimo");
                    System.out.println("3 - Bom");
                    System.out.println("4 - regular");
                    System.out.println("5 - ruim");
                    opcao = scan.nextInt();
                    switch (opcao) {
                        case 1:
                            excelente++;
                            break;
                        case 2:
                            otimo++;
                            break;
                        case 3:
                            bom++;
                            break;
                        case 4:
                            regular++;
                            break;
                        case 5:
                            ruim++;
                            break;
                        default:
                            System.out.println("Opção inválida");
                            invalido = true;
                            break;
                    }
                    if (!invalido) {
                        break;
                    }
                }
                while (true) {
                    System.out.println("Informe a idade");
                    idade = scan.nextInt();
                    if (idade < 0) {
                        System.out.println("Idade inválida");
                    } else {
                        if (idade >= 5 && idade <= 12) {
                            idade1++;
                        } else {
                            if (idade >= 13 && idade <= 17) {
                                idade2++;
                            } else {
                                if (idade >= 18 && idade <= 50) {
                                    idade3++;
                                }
                            }
                        }
                        break;
                    }
                }
                qtdade++;
            } else {
                if (resp.equalsIgnoreCase("n")) {
                    break;
                } else {
                    System.out.println("Opção inválida");
                }
            }
        }
        if (qtdade != 0) {
            System.out.println(((excelente * 100) / qtdade) + "% de excelente");
            System.out.println(((otimo * 100) / qtdade) + "% de ótimo");
            System.out.println(((bom * 100) / qtdade) + "% de bom");
            System.out.println(((regular * 100) / qtdade) + "% de regular");
            System.out.println(((ruim * 100) / qtdade) + "% de ruim");
            System.out.println("Faixa etária:");
            System.out.println("crianças de 5 á 12 anos = " + idade1);
            System.out.println("adolescente: 13 á 17 anos = " + idade2);
            System.out.println("adulto: 18 á 50 anos = " + idade3);
        }
    }
}
