package Exe23;

import java.util.Scanner;

public class Exe23Principal {

    public Exe23Principal() {
        Loja loja = new Loja();
        loja.addProduto(new Produto(100, "Calça Jeans Feminina", 54));
        loja.addProduto(new Produto(101, "Camiseta Feminina", 12));
        loja.addProduto(new Produto(102, "Calça Jeans Masculina", 79));
        loja.addProduto(new Produto(103, "Regata Masculina", 15));
        loja.addProduto(new Produto(104, "Bermuda Masculina", 30));
        System.out.println("Loja de roupas");
        System.out.println(loja.getProdutos());
        pedido(loja);
    }

    private void pedido(Loja loja) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Entre com o código do produto ou informe -1 pra sair");
            int cod = scan.nextInt();
            if (cod == -1) {
                break;
            } else {
                Produto produto = loja.getProduto(cod);
                if (produto != null) {
                    System.out.println("Entre com a quantidade");
                    int qtdade = scan.nextInt();
                    loja.setQtdade(qtdade);
                } else {
                    System.out.println("Produto não encontrado");
                }
            }
        }
        System.out.println("Conta Final: R$ " + loja.getContaFinal());
        while (true) {
            System.out.println("Entre com o valor a ser pago");
            double valPago = scan.nextDouble();
            if (loja.pagarConta(valPago)) {
                break;
            }
        }
    }
}
