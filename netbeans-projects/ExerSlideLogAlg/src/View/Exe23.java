package View;

import java.util.Scanner;

public class Exe23 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Entre com um número de até 3 díditos");
        int num = scan.nextInt();
        int centena = (int) num / 100;
        int dezena = (int) (num - (centena * 100)) / 10;
        int unidade = (int) (num - (centena * 100) - dezena * 10);
        System.out.println("Valor\t" + num);
        System.out.println("Centena\t" + centena);
        System.out.println("Dezena\t" + dezena);
        System.out.println("Unidade\t" + unidade);
    }
}
