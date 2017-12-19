package View;

import java.util.Scanner;

public class Exe19 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Retângulo");
        System.out.println("Entre com a altura");
        double altura = scan.nextDouble();
        System.out.println("Entre com a largura");
        double largura = scan.nextDouble();
        System.out.println("Área: " + (largura * altura));
        System.out.println("Peímetro: " + ((largura * 2) + (altura * 2)));
    }
}
