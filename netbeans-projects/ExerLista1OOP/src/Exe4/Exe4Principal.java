package Exe4;

import java.util.Scanner;

public class Exe4Principal {

    public Exe4Principal() {
        Scanner scan = new Scanner(System.in);
        Moeda moeda = new Moeda();
        System.out.println("Calculo moedas");
        System.out.println("Entre com as moedas de 1 centavo");
        moeda.setUmCentavo(scan.nextInt());
        System.out.println("Entre com as moedas de 5 centavos");
        moeda.setCincoCentavos(scan.nextInt());
        System.out.println("Entre com as moedas de 10 centavos");
        moeda.setDezCentavos(scan.nextInt());
        System.out.println("Entre com as moedas de 25 centavos");
        moeda.setVinteCincoCentavos(scan.nextInt());
        System.out.println("Entre com as moedas de 50 centavos");
        moeda.setCinquentaCentavos(scan.nextInt());
        System.out.println("Entre com as moedas de 1 real");
        moeda.setUmReal(scan.nextInt());
        System.out.println("Valor Total: R$ " + moeda.valorEconomizado());
    }
}
