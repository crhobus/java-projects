package View;

import java.util.Scanner;

public class Exe4 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Entre com os dados de um triângulo");
        System.out.println("Entre com a base");
        double base = scan.nextDouble();
        System.out.println("Entre com a altura");
        double altura = scan.nextDouble();

        System.out.println("Área do triângulo: " + (base * altura) / 2);
    }
}
