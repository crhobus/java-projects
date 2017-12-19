package Exe24;

import java.util.Scanner;

public class Exe24Principal {

    public Exe24Principal() {
        Scanner scan = new Scanner(System.in);
        Calcula calcula = new Calcula();
        System.out.println("Cálculo custo do consumidor");
        System.out.println("Entre com o valor de fábrica do automóvel");
        System.out.println(calcula.getCustoConsumidor(scan.nextDouble()));
    }
}
