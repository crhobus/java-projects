package View;

import java.util.Scanner;

public class Exe26 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Conta de um bar");
        System.out.println("Entre com o total da conta");
        double valorTotal = scan.nextDouble();
        double pagar = valorTotal / 3;
        System.out.println("Valor da conta\tR$ " + valorTotal);
        System.out.println("Thiago\tR$ " + ((int) pagar) + ".0");
        System.out.println("Alberto\tR$ " + ((int) pagar) + ".0");
        System.out.println("André\tR$ " + (((int) pagar) + valorTotal % 3));
    }
}
