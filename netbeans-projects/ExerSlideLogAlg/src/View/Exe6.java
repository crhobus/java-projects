package View;

import java.util.Scanner;

public class Exe6 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Cálculo preço final automóvel: impostos 15% e porcentagem revendedor 25%");
        System.out.println("Entre com o nome do automóvel");
        String nomeAuto = scan.nextLine();
        System.out.println("Entre com o preço de fábrica");
        double precoFabrica = scan.nextDouble();

        System.out.println("Nome automóvel: " + nomeAuto);
        System.out.println("Preço final: " + (precoFabrica * 1.40));
    }
}
