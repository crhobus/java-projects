package View;

import java.util.Scanner;

public class Exe18 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        final String[] nome = {"Calça", "Camisa", "Casaco", "Meias", "Paletó"};
        final int[] valor = {150, 300, 250, 30, 450};
        System.out.println("Produtos");
        System.out.print("[ ");
        for (int i = 0; i < nome.length; i++) {
            System.out.print(nome[i] + "  ");
        }
        System.out.println("]");
        System.out.println("Forma de pagamento");
        System.out.println("[ A Vista  A Prazo 30 dias  A Prazo 60 dias ]");
        System.out.println("Entre com o nome do produto");
        String produto = scan.nextLine();
        System.out.println("Entre com a forma de pagamento");
        String formaPagto = scan.nextLine();
        boolean encontrado = false;
        int posicao = 0;
        for (int i = 0; i < nome.length; i++) {
            if (nome[i].equalsIgnoreCase(produto)) {
                encontrado = true;
                posicao = i;
                break;
            }
        }
        if (encontrado) {
            if (formaPagto.equalsIgnoreCase("A Vista")) {
                System.out.println("Produto: " + nome[posicao] + ", Valor: " + valor[posicao]);
            } else {
                if (formaPagto.equalsIgnoreCase("A Prazo 30 dias")) {
                    System.out.println("Produto: " + nome[posicao] + ", Valor: " + (valor[posicao] * 1.1));
                } else {
                    if (formaPagto.equalsIgnoreCase("A Prazo 60 dias")) {
                        System.out.println("Produto: " + nome[posicao] + ", Valor: " + (valor[posicao] * 1.2));
                    } else {
                        System.out.println("Forma Pagamento inválida, entre novamente");
                    }
                }
            }
        } else {
            System.out.println("Produto não encontrado");
        }
    }
}
