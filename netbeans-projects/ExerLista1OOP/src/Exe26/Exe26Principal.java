package Exe26;

import java.util.Scanner;

public class Exe26Principal {

    public Exe26Principal() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Perguntas e Respostas");
        System.out.println("1 - Qual é o seu nome?");
        String nome = scan.nextLine();
        System.out.println("2 - Qual cidade você nasceu?");
        String cidadeNasc = scan.nextLine();
        System.out.println("3 - Sabe dizer oi em outra língua?");
        String lingua = scan.nextLine();
        System.out.println("4 - Quanto é 2+2?");
        String soma = scan.nextLine();
        System.out.println("5 - Qual a Capital de Santa Catarina?");
        String capital = scan.nextLine();
        System.out.println("\nRespostas");
        System.out.println("1 - " + nome);
        System.out.println("2 - " + cidadeNasc);
        System.out.println("3 - " + lingua);
        System.out.println("4 - " + soma);
        System.out.println("5 - " + capital);
    }
}
