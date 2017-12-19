package View;

import java.util.Scanner;

public class Exe11 {

    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        System.out.println("Cálculo multa");
        System.out.println("Entre com a velocidade máxima permitiva em uma avenida");
        int velMaxPer = sca.nextInt();
        System.out.println("Entre com a velocidade que o motorista estava dirigindo nela");
        int velDirigindo = sca.nextInt();
        if (velDirigindo > velMaxPer && velDirigindo <= velMaxPer + 10) {
            System.out.println("Multa a pagar: R$ 50,00");
        } else {
            if (velDirigindo >= velMaxPer + 11 && velDirigindo <= velMaxPer + 30) {
                System.out.println("Multa a pagar: R$ 100,00");
            } else {
                if (velDirigindo >= velMaxPer + 31) {
                    System.out.println("Multa a pagar: R$ 200,00");
                } else {
                    System.out.println("Bom motorista");
                }
            }
        }
    }
}
