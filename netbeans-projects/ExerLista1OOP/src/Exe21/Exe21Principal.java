package Exe21;

import java.util.Scanner;

public class Exe21Principal {

    public Exe21Principal() {
        Scanner scan = new Scanner(System.in);
        ContaHotel contaHotel = new ContaHotel();
        System.out.println("Cálculo conta hotel");
        System.out.println(contaHotel.getOpcoes());
        System.out.println("Entre com uma das opções");
        int opcao = scan.nextInt();
        System.out.println("Entre com a quantidade de dias");
        int qtdadeDias = scan.nextInt();
        System.out.println(contaHotel.getValorPagar(opcao, qtdadeDias));
    }
}
