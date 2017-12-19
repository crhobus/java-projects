package View;

import java.util.Scanner;

public class Exe9 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Programa papelaria, calculo desconto");
        System.out.println("Entre com o nome do produto");
        String produto = scan.nextLine();
        System.out.println("Entre com o valor do produto");
        double valor = scan.nextDouble();
        System.out.println("\nProduto:. " + produto);
        System.out.println("Preço:. R$ " + valor);
        System.out.println("Promoção: " + produto + "\n-----------------------");
        double desconto;
        for (int i = 0; i < 10; i++) {
            desconto = (valor - (((i + 1) * 5) * (valor / 100)));
            System.out.println((i + 1) + " X R$ " + desconto + " = R$ " + ((i + 1) * desconto));
        }
    }
}
