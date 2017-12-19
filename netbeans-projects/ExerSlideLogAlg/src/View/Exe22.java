package View;

import java.util.Scanner;

public class Exe22 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Calculo moedas");
        System.out.println("Entre com as moedas de 1 centavo");
        double um = scan.nextInt();
        System.out.println("Entre com as moedas de 5 centavos");
        double cinco = scan.nextInt();
        System.out.println("Entre com as moedas de 10 centavos");
        double dez = scan.nextInt();
        System.out.println("Entre com as moedas de 25 centavos");
        double vinteCinco = scan.nextInt();
        System.out.println("Entre com as moedas de 50 centavos");
        double cinquenta = scan.nextInt();
        System.out.println("Entre com as moedas de 1 real");
        double umReal = scan.nextInt();
        double moedas = (um + (cinco * 5) + (dez * 10) + (vinteCinco * 25) + (cinquenta * 50)) / 100;
        System.out.println("Valor Total: R$ " + (moedas + umReal));
    }
}
