package View;

import java.util.Scanner;

public class Exe5 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String nome, linha = null, sexo;
        int contM = 0, contF = 0, qtdade = 0, qtdateMedia1 = 0, qtdateMedia2 = 0,
                qtdateMedia3 = 0, qtdateMedia4 = 0, qtdateMedia5 = 0, qtdateMedia6 = 0;
        double maiorMedia = -1, media = 0;
        double[] notas = new double[4];
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
            qtdade++;
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
            for (int i = 0; i < notas.length; i++) {
                while (true) {
                    System.out.println("Entre com a " + (i + 1) + "ª nota");
                    notas[i] = scan.nextDouble();
                    if (notas[i] >= 0 && notas[i] <= 10) {
                        break;
                    } else {
                        System.out.println("Nota inválida");
                    }
                }
            }
            media = (notas[0] + notas[1] + notas[2] + notas[3]) / 4;
            if (media > maiorMedia) {
                maiorMedia = media;
            }
            if (media == 10) {
                qtdateMedia1++;
            } else {
                if (media >= 9 && media <= 9.9) {
                    qtdateMedia2++;
                } else {
                    if (media >= 8 && media <= 8.9) {
                        qtdateMedia3++;
                    } else {
                        if (media >= 7 && media <= 7.9) {
                            qtdateMedia4++;
                        } else {
                            if (media >= 5 && media <= 6.9) {
                                qtdateMedia5++;
                            } else {
                                qtdateMedia6++;
                            }
                        }
                    }
                }
            }
            System.out.println("Nome do aluno: " + nome + ", média: " + media);
        }
        if (qtdade != 0) {
            System.out.println("Maior média de todos os alunos cadastrados: " + maiorMedia);
            System.out.println("Quantidade de meninos: " + contM);
            System.out.println("Quantidade de meninas: " + contF);
            System.out.println(((qtdateMedia1 * 100) / qtdade) + "% de alunos que tiraram 10-----------------------------------Parabéns");
            System.out.println(((qtdateMedia2 * 100) / qtdade) + "% de alunos que tiraram média entre 9.0 e 9.9----------------Ótimo");
            System.out.println(((qtdateMedia3 * 100) / qtdade) + "% de alunos que tiraram média entre 8.0 e 8.9----------------Bom");
            System.out.println(((qtdateMedia4 * 100) / qtdade) + "% de alunos que tiraram média entre 7.0 e 7.9----------------Satisfatório");
            System.out.println(((qtdateMedia5 * 100) / qtdade) + "% de alunos que tiraram média entre 5.0 e 6.9----------------Recuperação");
            System.out.println(((qtdateMedia6 * 100) / qtdade) + "% de alunos que tiraram média abaixo de 5--------------------Exame");
        }
    }
}
