package Exe17;

import java.util.Scanner;

public class Exe17Principal {

    public Exe17Principal() {
        Scanner scan = new Scanner(System.in);
        Calcula calcula = new Calcula();
        System.out.println("Antecessor e Sucessor");
        System.out.println("Entre com um número");
        int num = scan.nextInt();
        System.out.println(calcula.getCalculo(num));
    }
}
