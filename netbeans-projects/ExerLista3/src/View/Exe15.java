package View;

import java.util.Random;
import java.util.Scanner;

public class Exe15 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        int[] megaSena = new int[2];
        int[] aposta = new int[2];
        int cont = 0;
        System.out.println("Mega Sena com 2 valores");
        for (int i = 0; i < megaSena.length; i++) {
            megaSena[i] = random.nextInt(4);
            while (true) {
                System.out.println("Entre com a " + (i + 1) + "ª aposta 0 - 3");
                aposta[i] = scan.nextInt();
                if (aposta[i] >= 0 && aposta[i] <= 3) {
                    break;
                } else {
                    System.out.println("Aposta inválida, entre novamente");
                }
            }
            if (megaSena[i] == aposta[i]) {
                cont++;
            }
        }
        if (cont == 2) {
            System.out.println("Você está milionário!");
        } else {
            System.out.println("Tente outra vez..");
        }
    }
}
