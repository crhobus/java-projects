package Exe1;

import java.util.Scanner;

public class Exe1Principal {

    public Exe1Principal() {
        Scanner scan = new Scanner(System.in);
        Numero numero = new Numero(3);
        System.out.println("Leitura de 3 valores");
        System.out.println("Entre com o primeiro valor");
        numero.setNumero(scan.nextInt());
        System.out.println("Entre com o segundo valor");
        numero.setNumero(scan.nextInt());
        System.out.println("Entre com o terceiro valor");
        numero.setNumero(scan.nextInt());
        System.out.println("O menor valor é: " + numero.getMenor());
    }
}
