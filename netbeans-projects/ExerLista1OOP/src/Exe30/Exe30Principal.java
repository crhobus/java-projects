package Exe30;

import java.util.Scanner;

public class Exe30Principal {

    public Exe30Principal() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Triangulo");
        System.out.println("Entre com o primeiro lado");
        double lado1 = scan.nextDouble();
        System.out.println("Entre com o segundo lado");
        double lado2 = scan.nextDouble();
        System.out.println("Entre com o primeiro lado");
        double lado3 = scan.nextDouble();
        Triangulo triangulo = new Triangulo(lado1, lado2, lado3);
        System.out.println(triangulo.getTriangulo());
    }
}
