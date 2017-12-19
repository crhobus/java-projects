package Exe9;

import java.util.Scanner;

public class Exe9Principal {

    public Exe9Principal() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Leitura de 3 valores e colocando em ordem crescente");
        System.out.println("Entre com o primeiro valor");
        int num1 = scan.nextInt();
        System.out.println("Entre com o segundo valor");
        int num2 = scan.nextInt();
        System.out.println("Entre com o terceiro valor");
        int num3 = scan.nextInt();
        Numero numero = new Numero(num1, num2, num3);
        System.out.println(numero.getValoresOrdCrescente());
    }
}
