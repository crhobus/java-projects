package Exe3;

import java.util.Scanner;

public class Exe3Principal {

    public Exe3Principal() {
        Scanner scan = new Scanner(System.in);
        Lanchonete lanchonete = new Lanchonete();
        System.out.println("Lanchonete");
        lanchonete.addPedido(new ItemPedido(1, "Hambúrguer + Suco de Laranja", 5));
        lanchonete.addPedido(new ItemPedido(2, "Sanduíche natural + Suco de Uva", 4.5));
        lanchonete.addPedido(new ItemPedido(3, "Prato do dia", 8));
        lanchonete.addPedido(new ItemPedido(4, "Pizza", 12));
        lanchonete.addPedido(new ItemPedido(5, "Lasanha", 16.5));
        lanchonete.addPedido(new ItemPedido(6, "Pão de queijo", 1));
        lanchonete.addPedido(new ItemPedido(7, "Bolo", 2.5));
        System.out.println("Tabela de pedidos:");
        System.out.println(lanchonete.getPedido());
        System.out.println("\nEntre com o número do pedido");
        int numPedido = scan.nextInt();
        System.out.println("Entre com o pagamento");
        double pagto = scan.nextDouble();
        System.out.println(lanchonete.getItemPedido(numPedido, pagto));
    }
}
