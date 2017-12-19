package Exe16;

import java.util.Scanner;

public class Exe16Principal {

    public Exe16Principal() {
        Scanner scan = new Scanner(System.in);
        CalculaSistuacao calculaSistuacao = new CalculaSistuacao();
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
        int numFaltas = scan.nextInt();
        System.out.println(calculaSistuacao.getSistuacao(new Notas(nota1, nota2, nota3, nota4), numFaltas));
    }
}
