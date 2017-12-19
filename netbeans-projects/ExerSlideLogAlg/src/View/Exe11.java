package View;

import java.util.Scanner;

public class Exe11 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Notas aluno");
        System.out.println("Entre com o nome");
        String nome = scan.nextLine();
        System.out.println("Entre com a primeira nota");
        double nota1 = scan.nextDouble();
        System.out.println("Entre com a segunda nota");
        double nota2 = scan.nextDouble();
        System.out.println("Entre com a terceira nota");
        double nota3 = scan.nextDouble();
        double media = (nota1 + nota2 + nota3) / 3;
        System.out.println("Nome aluno: " + nome);
        System.out.println("Média: " + media);
        if (media >= 8) {
            System.out.println("Aprovado");
        } else {
            System.out.println("Reprovado");
        }
    }
}
