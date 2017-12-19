package View;

import java.util.Scanner;

public class Exe30 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Triangulo");
        System.out.println("Entre com o primeiro lado");
        double lado1 = scan.nextDouble();
        System.out.println("Entre com o segundo lado");
        double lado2 = scan.nextDouble();
        System.out.println("Entre com o primeiro lado");
        double lado3 = scan.nextDouble();
        if (lado1 < lado2 + lado3 && lado2 < lado1 + lado3 && lado3 < lado1 + lado2) {
            if (lado1 == lado2 && lado1 == lado3) {
                System.out.println("Triangulo eqüilátero");
            } else {
                if (lado1 == lado2 || lado1 == lado3 || lado2 == lado3) {
                    System.out.println("Triangulo isósceles");
                } else {
                    System.out.println("Triangulo escaleno");
                }
            }
        } else {
            System.out.println("Não é um triângulo");
        }
    }
}
