package View;

import java.util.Scanner;

public class Exe10 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Comparador de nome");
        System.out.println("Entre com o nome");
        String nome = scan.next();
        if (nome.equalsIgnoreCase("Caio")) {
            System.out.println("NOME CORRETO");
        } else {
            System.out.println("NOME INCORRETO");
        }
    }
}
