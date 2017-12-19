package View;

import java.util.Scanner;

public class Exe21 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Gerenciador de saque Caixa Eletrônico");
        System.out.println("Entre com a quantia a ser sacada");
        double valorQuantia = scan.nextDouble();
        int qtdadeCem = 0;
        int qtdadeCinquenta = 0;
        int qtdadeVinte = 0;
        int qtdadeDez = 0;
        int qtdadeCinco = 0;
        int qtdadeDois = 0;
        int qtdadeUm = 0;
        while (valorQuantia != 0) {
            if (valorQuantia >= 100) {
                valorQuantia -= 100;
                qtdadeCem++;
                continue;
            }
            if (valorQuantia >= 50) {
                valorQuantia -= 50;
                qtdadeCinquenta++;
                continue;
            }
            if (valorQuantia >= 20) {
                valorQuantia -= 20;
                qtdadeVinte++;
                continue;
            }
            if (valorQuantia >= 10) {
                valorQuantia -= 10;
                qtdadeDez++;
                continue;
            }
            if (valorQuantia >= 5) {
                valorQuantia -= 5;
                qtdadeCinco++;
                continue;
            }
            if (valorQuantia >= 2) {
                valorQuantia -= 2;
                qtdadeDois++;
                continue;
            }
            if (valorQuantia >= 1) {
                valorQuantia -= 1;
                qtdadeUm++;
                continue;
            }
        }
        String saida = "";
        if (qtdadeCem != 0) {
            saida += qtdadeCem + " de RS 100,00\n";
        }
        if (qtdadeCinquenta != 0) {
            saida += qtdadeCinquenta + " de RS 50,00\n";
        }
        if (qtdadeVinte != 0) {
            saida += qtdadeVinte + " de RS 20,00\n";
        }
        if (qtdadeDez != 0) {
            saida += qtdadeDez + " de RS 10,00\n";
        }
        if (qtdadeCinco != 0) {
            saida += qtdadeCinco + " de RS 5,00\n";
        }
        if (qtdadeDois != 0) {
            saida += qtdadeDois + " de RS 2,00\n";
        }
        if (qtdadeUm != 0) {
            saida += qtdadeUm + " de RS 1,00\n";
        }
        System.out.println("Distribuição das notas\n" + saida);
    }
}
