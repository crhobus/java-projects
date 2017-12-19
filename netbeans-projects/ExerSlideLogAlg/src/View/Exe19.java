package View;

import java.util.Scanner;

public class Exe19 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Conversão de temperatura - Celsius (C), Kelvin (K) e Fahrenheit (F)");
        System.out.println("Entre com uma temperatura");
        int temperatura = scan.nextInt();
        System.out.println("Tipo de temperatura:" + "\n1 - Celsius" + "\n2 - Kelvin" + "\n3 - Fahrenheit");
        System.out.println("Entre com o tipo de temperatura de entrada");
        int tipoEntrada = scan.nextInt();
        System.out.println("Entre com o tipo de temperatura de saida");
        int tipoSaida = scan.nextInt();
        if (tipoEntrada == 1 && tipoSaida == 3) {
            System.out.println("Conversão de Celsius para Fahrenheit: " + ((temperatura * 1.8) + 32));
        } else {
            if (tipoEntrada == 1 && tipoSaida == 2) {
                System.out.println("Conversão de Celsius para Kelvin: " + (temperatura + 273.15));
            } else {
                if (tipoEntrada == 2 && tipoSaida == 1) {
                    System.out.println("Conversão de Kelvin para Celsius: " + (temperatura - 273.15));
                } else {
                    if (tipoEntrada == 2 && tipoSaida == 3) {
                        System.out.println("Conversão de Kelvin para Fahrenheit: " + (temperatura * 1.8 - 459.67));
                    } else {
                        if (tipoEntrada == 3 && tipoSaida == 1) {
                            System.out.println("Conversão de Fahrenheit para Celsius: " + ((temperatura - 32) / 1.8));
                        } else {
                            if (tipoEntrada == 3 && tipoSaida == 2) {
                                System.out.println("Conversão de Fahrenheit para Kelvin: " + ((temperatura + 459.67) / 1.8));
                            } else {
                                System.out.println("Tipo inválido");
                            }
                        }
                    }
                }
            }
        }
    }
}
