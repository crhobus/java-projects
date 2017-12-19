package Exe19;

import java.util.Scanner;

public class Exe19Principal {

    public Exe19Principal() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Retângulo");
        System.out.println("Entre com a altura");
        double altura = scan.nextDouble();
        System.out.println("Entre com a largura");
        double largura = scan.nextDouble();
        Retangulo retangulo = new Retangulo(altura, largura);
        System.out.println(retangulo.getArea());
        System.out.println(retangulo.getPerimetro());
    }
}
