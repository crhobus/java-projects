package Exe10;

import java.util.Scanner;

public class Exe10Principal {

    public Exe10Principal() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Comparador de nome");
        System.out.println("Entre com o nome");
        Nome n = new Nome("Caio");
        System.out.println(n.getVerificaIgualdade(scan.next()));
    }
}
