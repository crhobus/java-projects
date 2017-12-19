package Exe8;

import java.util.Scanner;

import Excecao.ExceptionNumber;

public class Exe8Principal {

    private Scanner scan;

    public Exe8Principal() {
        scan = new Scanner(System.in);
        System.out.println("Saque de um caixa eletrônico");
        double valorQuantia;
        while (true) {
            try {
                valorQuantia = getQuantia();
                break;
            } catch (ExceptionNumber ex) {
                System.err.println(ex.getMessage());
            }
        }
        int qtdadeCem = 0, qtdadeCinquenta = 0, qtdadeVinte = 0, qtdadeDez = 0,
                qtdadeCinco = 0, qtdadeDois = 0, qtdadeUm = 0;
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
        System.out.println("Será disponibilizado\n" + saida);
    }

    private double getQuantia() throws ExceptionNumber {
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
