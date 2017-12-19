package View;

import java.util.Scanner;

public class Exe3 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Sistema de médias");
        System.out.println("Entre com o nome do aluno");
        String nome = scan.nextLine();
        System.out.println("Entre com a primeira nota");
        double nota1 = scan.nextDouble();
        System.out.println("Entre com a segunda nota");
        double nota2 = scan.nextDouble();
        System.out.println("Entre com a terceira nota");
        double nota3 = scan.nextDouble();
        System.out.println("Nome: " + nome);
        System.out.println("Média: " + (nota1 + nota2 + nota3) / 3);
    }
}
