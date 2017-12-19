package Exe28;

import java.util.Scanner;

public class Exe28Principal {

    public Exe28Principal() {
        Scanner scan = new Scanner(System.in);
        Classificacao classificacao = new Classificacao();
        System.out.println("Classificação do usuário");
        System.out.println("Entre com o nome");
        String nome = scan.nextLine();
        System.out.println("Entre com a idade");
        int idade = scan.nextInt();
        System.out.println(classificacao.getClassificacao(nome, idade));
    }
}
