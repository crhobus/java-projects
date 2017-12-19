package Exe9;

import java.util.Scanner;

import Excecao.ExceptionNumber;

public class Exe9Principal {

    private Scanner scan;

    public Exe9Principal() {
        scan = new Scanner(System.in);
        System.out.println("Programa papelaria, calculo desconto");
        System.out.println("Entre com o nome do produto");
        String produto = scan.nextLine();
        System.out.println("Entre com o valor do produto");
        double valor;
        while (true) {
            try {
                valor = valorProduto();
                break;
            } catch (ExceptionNumber ex) {
                System.err.println(ex.getMessage());
            }
        }
        System.out.println("\nProduto:. " + produto);
        System.out.println("Preço:. R$ " + valor);
        System.out.println("Promoção: " + produto + "\n-----------------------");
        double desconto;
        for (int i = 0; i < 10; i++) {
            desconto = (valor - (((i + 1) * 5) * (valor / 100)));
            System.out.println((i + 1) + " X R$ " + desconto + " = R$ " + ((i + 1) * desconto));
        }
    }

    private double valorProduto() throws ExceptionNumber {
        try {
            System.out.println("Entre com a quantia solicitada");
            double quantia = Double.parseDouble(scan.next());
            if (quantia > 0) {
                return quantia;
            }
            throw new ExceptionNumber("Quantia socicitada inválida");
        } catch (NumberFormatException ex) {
            throw new ExceptionNumber("Entre com um valor válido");
        }
    }
}
