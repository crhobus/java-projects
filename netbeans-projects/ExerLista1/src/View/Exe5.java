package View;

import java.util.Scanner;

public class Exe5 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int cidade, estado;
        System.out.println("Entre com o numero do estado");
        System.out.println("1 - Santa Catarina");
        System.out.println("2 - São Paulo");
        System.out.println("3 - Rio de Janeiro");
        estado = scan.nextInt();
        switch (estado) {
            case 1:
                System.out.println("Entre com o numero a cidade");
                System.out.println("1 - Indaial");
                System.out.println("2 - Blumenau");
                System.out.println("3 - Navegantes");
                cidade = scan.nextInt();
                switch (cidade) {
                    case 1:
                        System.out.println("População: 70 mil");
                        System.out.println("Principal Festa: FIMI - aniversário de indaial");
                        System.out.println("IDH: 12");
                        break;
                    case 2:
                        System.out.println("População: 300 mil");
                        System.out.println("Principal Festa: October Fest");
                        System.out.println("IDH: 10");
                        break;
                    case 3:
                        System.out.println("População: 65 mil");
                        System.out.println("Principal Festa: Carnaval");
                        System.out.println("IDH: 9");
                        break;
                    default:
                        System.out.println("Cidade Inválida");
                        break;
                }
                break;
            case 2:
                System.out.println("Entre com o número da cidade");
                System.out.println("1 - São Paulo");
                System.out.println("2 - Campinas");
                System.out.println("3 - Santos");
                cidade = scan.nextInt();
                switch (cidade) {
                    case 1:
                        System.out.println("População: 10 milhões");
                        System.out.println("Principal Festa: Carnaval");
                        System.out.println("IDH: 20");
                        break;
                    case 2:
                        System.out.println("População: 2 milhões");
                        System.out.println("Principal Festa: Rodeio");
                        System.out.println("IDH: 15");
                        break;
                    case 3:
                        System.out.println("População: 900 mil");
                        System.out.println("Principal Festa: Carnaval");
                        System.out.println("IDH: 9");
                        break;
                    default:
                        System.out.println("Cidade Inválida");
                        break;
                }
                break;
            case 3:
                System.out.println("Entre com o numero a cidade");
                System.out.println("1 - Rio de Janeiro");
                System.out.println("2 - Macaé");
                System.out.println("3 - Niteroi");
                cidade = scan.nextInt();
                switch (cidade) {
                    case 1:
                        System.out.println("População: 7 milhões");
                        System.out.println("Principal Festa: Carnaval");
                        System.out.println("IDH: 20");
                        break;
                    case 2:
                        System.out.println("População: 400 mil");
                        System.out.println("Principal Festa: Carnaval");
                        System.out.println("IDH: 18");
                        break;
                    case 3:
                        System.out.println("População: 500 mil");
                        System.out.println("Principal Festa: Carnaval");
                        System.out.println("IDH: 7");
                        break;
                    default:
                        System.out.println("Cidade Inválida");
                        break;
                }
                break;
            default:
                System.out.println("Estado Inválido");
                break;
        }
    }
}
