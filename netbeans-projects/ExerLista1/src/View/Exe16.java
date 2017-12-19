package View;

import java.util.Scanner;

public class Exe16 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Sistema cadastro de notas e faltas");
        System.out.println("Entre com a primeira nota");
        double nota1 = scan.nextDouble();
        System.out.println("Entre com a segunda nota");
        double nota2 = scan.nextDouble();
        System.out.println("Entre com a terceira nota");
        double nota3 = scan.nextDouble();
        System.out.println("Entre com a quarta nota");
        double nota4 = scan.nextDouble();
        System.out.println("Entre com o números de falta");
        int faltas = scan.nextInt();
        if (faltas > 15) {
            System.out.println("Reprovado por faltas");
        } else {
            if (nota1 > 10 || nota2 > 10 || nota3 > 10 || nota4 > 10) {
                System.out.println("Entre com notas válidas");
            } else {
                double media = (nota1 + nota2 + nota3 + nota4) / 4;
                if (media <= 5) {
                    System.out.println("Média: " + media + ", situação: Reprovado");
                } else {
                    if (media >= 5.1 && media <= 6.9) {
                        System.out.println("Média: " + media + ", situação: Em exame");
                    } else {
                        if (media >= 7 && media <= 8.9) {
                            System.out.println("Média: " + media + ", situação: Bom");
                        } else {
                            if (media >= 9 && media <= 9.9) {
                                System.out.println("Média: " + media + ", situação: Ótimo");
                            } else {
                                System.out.println("Média: " + media + ", situação: Parabéns");
                            }
                        }
                    }
                }
            }
        }
    }
}
