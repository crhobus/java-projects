package View;

import java.util.Scanner;

public class Exe3 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[][] matriz = new String[4][2];
        matriz[0][0] = "Arroz";
        matriz[1][0] = "Feijão";
        matriz[2][0] = "Trigo";
        matriz[3][0] = "Sal";
        matriz[0][1] = "5.30";
        matriz[1][1] = "6.00";
        matriz[2][1] = "3.20";
        matriz[3][1] = "2.50";
        double valor = 0;
        System.out.println("Sistema de compras");
        System.out.println("Produto\t\tPreço unitário");
        for (int i = 0; i < 4; i++) {
            for (int y = 0; y < 2; y++) {
                System.out.print(matriz[i][y] + "\t\t");
            }
            System.out.println();
        }
        System.out.println("Entre com um produto");
        String produto = scan.nextLine();
        System.out.println("Entre com a quantidade");
        int qtdade = scan.nextInt();
        boolean encontrado = false;
        for (int i = 0; i < 4; i++) {
            for (int y = 0; y < 2; y++) {
                if (matriz[i][y].equalsIgnoreCase(produto)) {
                    encontrado = true;
                    valor = qtdade * Double.parseDouble(matriz[i][y + 1]);
                    encontrado = true;
                }
            }
        }
        if (encontrado) {
            System.out.println("Valor da compra R$ " + valor);
        } else {
            System.out.println("Produto não encontrado");
        }
    }
}
