package Exe18;

import java.util.Scanner;

public class Exe18principal {

    public Exe18principal() {
        Scanner scan = new Scanner(System.in);
        Controle controle = new Controle();
        controle.addProduto(new Produto("Calça", 150));
        controle.addProduto(new Produto("Camisa", 300));
        controle.addProduto(new Produto("Casaco", 250));
        controle.addProduto(new Produto("Meias", 30));
        controle.addProduto(new Produto("Paletó", 450));
        System.out.println(controle.getProdutos());
        System.out.println("Forma de pagamento");
        System.out.println("[ A Vista  A Prazo 30 dias  A Prazo 60 dias ]");
        System.out.println("Entre com o nome do produto");
        String produto = scan.nextLine();
        System.out.println("Entre com a forma de pagamento");
        String formaPagto = scan.nextLine();
        System.out.println(controle.getPagamento(produto, formaPagto));
    }
}
