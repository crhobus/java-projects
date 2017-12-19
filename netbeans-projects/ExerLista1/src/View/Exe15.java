package View;

import java.util.Scanner;

public class Exe15 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Entre com o nome do autor:");
        System.out.println("Monteiro Lobato");
        System.out.println("Clarisse Lispector");
        System.out.println("Fernando Pessoa");
        String nome = scan.nextLine();
        if (nome.equalsIgnoreCase("Monteiro Lobato")) {
            System.out.println("Nome: Monteiro Lobato");
            System.out.println("Profissão: Escritor");
            System.out.println("Idade: 80");
        } else {
            if (nome.equalsIgnoreCase("Clarisse Lispector")) {
                System.out.println("Nome: Clarisse Lispector");
                System.out.println("Profissão: Escritora");
                System.out.println("Idade: 72");
            } else {
                if (nome.equalsIgnoreCase("Fernando Pessoa")) {
                    System.out.println("Nome: Fernando Pessoa");
                    System.out.println("Profissão: Poeta");
                    System.out.println("Idade: 79");
                } else {
                    System.out.println("nome não existe");
                }
            }
        }
    }
}
