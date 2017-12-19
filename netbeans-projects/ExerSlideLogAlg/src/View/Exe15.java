package View;

import java.util.Scanner;

public class Exe15 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Quantidade de dias de vida");
        System.out.println("Entre com o nome");
        String nome = scan.nextLine();
        System.out.println("Entre com a idade");
        int idade = scan.nextInt();
        System.out.println(nome + ", você ja viveu " + (idade * 365));
    }
}
