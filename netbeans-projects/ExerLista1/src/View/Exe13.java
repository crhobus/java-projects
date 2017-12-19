package View;

import java.util.Scanner;

public class Exe13 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int hora;
        while (true) {
            System.out.println("Entre com a hora:");
            hora = scan.nextInt();
            if (hora >= 0 && hora <= 23) {
                break;
            } else {
                System.out.println("Hora inválida");
            }
        }
        if (hora >= 0 && hora <= 6) {
            System.out.println("Boa Madrugada");
        } else {
            if (hora >= 7 && hora <= 11) {
                System.out.println("Bom dia");
            } else {
                if (hora >= 12 && hora <= 18) {
                    System.out.println("Boa Tarde");
                } else {
                    System.out.println("Boa noite");
                }
            }
        }
    }
}
