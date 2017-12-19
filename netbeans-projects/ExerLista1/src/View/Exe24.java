package View;

import java.util.Scanner;

public class Exe24 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Entre com o valor de fábrica do automóvel");
        double valor = scan.nextDouble();
        if (valor < 12000) {
            System.out.println("Distribuidor 5%: " + (valor * 0.05));
            System.out.println("Impostos isento");
            System.out.println("Custo para o consumidor: R$ " + (valor * 1.05));
        } else {
            if (valor >= 12000 && valor <= 25000) {
                System.out.println("Distribuidor 10%: " + (valor * 0.1));
                System.out.println("Impostos 15%: " + (valor * 0.15));
                System.out.println("Custo para o consumidor: R$ " + (valor * 1.25));
            } else {
                System.out.println("Distribuidor 15%: " + (valor * 0.15));
                System.out.println("Impostos 20%: " + (valor * 0.2));
                System.out.println("Custo para o consumidor: R$ " + (valor * 1.35));
            }
        }
    }
}
