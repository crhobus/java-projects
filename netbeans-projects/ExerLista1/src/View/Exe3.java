package View;

import java.util.Scanner;

public class Exe3 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Lanchonete");
        System.out.println("N° Pedido\tPedido\t\t\t\t\tValor");
        System.out.println("1\t\tHambúrguer + Suco de Laranja\t\tR$ 5,00");
        System.out.println("2\t\tSanduíche natural + Suco de Uva\t\tR$ 4,50");
        System.out.println("3\t\tPrato do dia\t\t\t\tR$ 8,00");
        System.out.println("4\t\tPizza\t\t\t\t\tR$ 12,00");
        System.out.println("5\t\tLasanha\t\t\t\t\tR$ 16,50");
        System.out.println("6\t\tPão de queijo\t\t\t\tR$ 1,00");
        System.out.println("7\t\tBolo\t\t\t\t\tR$ 2,50");
        System.out.println("Entre com número do pedido");
        int numPedido = scan.nextInt();
        double troco = 0;
        if (numPedido >= 1 && numPedido <= 7) {
            System.out.println("Entre com o pagamento");
            double valPago = scan.nextDouble();
            System.out.println("\nNúmero do pedido: " + numPedido);
            switch (numPedido) {
                case 1:
                    System.out.println("Valor do pedido: R$ 5,00");
                    if (valPago >= 5) {
                        troco = valPago - 5;
                    } else {
                        System.err.println("Valor pago abaixo do valor do pedido");
                    }
                    break;
                case 2:
                    System.out.println("Valor do pedido: 4,50");
                    if (valPago >= 4.5) {
                        troco = valPago - 4.5;
                    } else {
                        System.err.println("Valor pago abaixo do valor do pedido");
                    }
                    break;
                case 3:
                    System.out.println("Valor do pedido: 8,00");
                    if (valPago >= 8) {
                        troco = valPago - 8;
                    } else {
                        System.err.println("Valor pago abaixo do valor do pedido");
                    }
                    break;
                case 4:
                    System.out.println("Valor do pedido: 12,00");
                    if (valPago >= 12) {
                        troco = valPago - 12;
                    } else {
                        System.err.println("Valor pago abaixo do valor do pedido");
                    }
                    break;
                case 5:
                    System.out.println("Valor do pedido: 16,50");
                    if (valPago >= 16.5) {
                        troco = valPago - 16.5;
                    } else {
                        System.err.println("Valor pago abaixo do valor do pedido");
                    }
                    break;
                case 6:
                    System.out.println("Valor do pedido: 1,00");
                    if (valPago >= 1) {
                        troco = valPago - 1;
                    } else {
                        System.err.println("Valor pago abaixo do valor do pedido");
                    }
                    break;
                case 7:
                    System.out.println("Valor do pedido: 2,50");
                    if (valPago >= 2.5) {
                        troco = valPago - 2.5;
                    } else {
                        System.err.println("Valor pago abaixo do valor do pedido");
                    }
                    break;
            }
            if (troco != 0) {
                System.out.println("Troco fornecido: R$ " + troco);
            } else {
                System.out.println("Nenhum troco fornecido");
            }
        } else {
            System.err.println("Número do pedido inválido");
        }
    }
}
