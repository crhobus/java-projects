package View;

import java.util.Scanner;

public class Exe22 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Calculo Velocidade Média");
        System.out.println("Entre com o espaço percorrido em metros");
        double espaco = scan.nextDouble();
        System.out.println("Tempo utilizado para percorrer o espaço informado em segundos");
        double tempo = scan.nextDouble();
        System.out.println("Velocidade Média " + (espaco / tempo) + " m/s");
    }
}
