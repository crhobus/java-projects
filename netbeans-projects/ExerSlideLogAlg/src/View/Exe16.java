package View;

import java.util.Scanner;

public class Exe16 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Notas aluno");
        System.out.println("Entre com a primeira nota");
        double nota1 = scan.nextDouble();
        System.out.println("Entre com a segunda nota");
        double nota2 = scan.nextDouble();
        System.out.println("Entre com a terceira nota");
        double nota3 = scan.nextDouble();
        double media = ((nota1 * 2) + (nota2 * 3) + (nota3 * 5)) / 10;
        System.out.println("Média: " + media);
    }
}
