package Exe14;

import java.util.Scanner;

public class Exe14Principal {

    public Exe14Principal() {
        Scanner scan = new Scanner(System.in);
        CalculoIMC calculoIMC = new CalculoIMC();
        System.out.println("Cálculo IMC");
        System.out.println("Informe o peso:");
        double peso = scan.nextDouble();
        System.out.println("Informe a altura:");
        double altura = scan.nextDouble();
        System.out.println(calculoIMC.getImc(peso, altura));
    }
}
