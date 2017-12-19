package View;

import java.util.Scanner;

public class Exe17 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Entre com um número");
        int num = scan.nextInt();
        System.out.println("Antecessor " + (num - 1));
        System.out.println("Sucessor " + (num + 1));
    }
}
