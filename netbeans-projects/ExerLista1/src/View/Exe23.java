package View;

import java.util.Scanner;

public class Exe23 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        final String[] produto = {"Calça Jeans Feminina", "Camiseta Feminina", "Calça Jeans Masculina", "Regata Masculina", "Bermuda Masculina"};
        final int[] codigo = {100, 101, 102, 103, 104};
        final double[] preco = {54, 12, 79, 15, 30};
        System.out.println("Produto\t\t\tCódigo\tPreço");
        for (int i = 0; i < produto.length; i++) {
            System.out.println(produto[i] + "\t" + codigo[i] + "\t" + preco[i]);
        }
        double contaFinal = 0;
        while (true) {
            System.out.println("Entre com o código do produto ou informe -1 pra sair");
            int cod = scan.nextInt();
            if (cod == -1) {
                break;
            } else {
                int posicao;
                boolean encontrado = false;
                for (posicao = 0; posicao < codigo.length; posicao++) {
                    if (cod == codigo[posicao]) {
                        encontrado = true;
                        break;
                    }
                }
                if (encontrado) {
                    System.out.println("Entre com a quantidade");
                    int qtdade = scan.nextInt();
                    contaFinal += qtdade * preco[posicao];
                } else {
                    System.out.println("Entre com um código válido");
                }
            }
        }
        System.out.println("Conta Final: R$ " + contaFinal);
        while (true) {
            System.out.println("Entre com o valor a ser pago");
            double valPago = scan.nextDouble();
            if (valPago < contaFinal) {
                System.out.println("Entre com o pagamento correto");
            } else {
                System.out.println("Troco: R$ " + (valPago - contaFinal));
                break;
            }
        }
    }
}
