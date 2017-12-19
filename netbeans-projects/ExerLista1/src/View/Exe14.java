package View;

import java.util.Scanner;

public class Exe14 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Informe o peso:");
        double peso = scan.nextDouble();
        System.out.println("Informe a altura:");
        double altura = scan.nextDouble();
        double imc = peso / (altura * altura);
        if (imc < 17) {
            System.out.println("Muito abaixo do peso");
        } else {
            if (imc >= 17 && imc <= 18.49) {
                System.out.println("Abaixo do peso");
            } else {
                if (imc >= 18.5 && imc <= 24.99) {
                    System.out.println("Peso normal");
                } else {
                    if (imc >= 25 && imc <= 29.99) {
                        System.out.println("Acima do peso");
                    } else {
                        if (imc >= 30 && imc <= 34.99) {
                            System.out.println("Obesidade I");
                        } else {
                            if (imc >= 35 && imc <= 39.99) {
                                System.out.println("Obesidade II (severa)");
                            } else {
                                System.out.println("Obesidade III (mórbida)");
                            }
                        }
                    }
                }
            }
        }
    }
}
