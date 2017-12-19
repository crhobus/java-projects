package View;

import java.util.Random;
import java.util.Scanner;

public class Exe3 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        int num = random.nextInt(101);
        int entrada, numPalpites = 0;
        System.out.println(num);
        System.out.println("Tente acertar o número gerado pelo computador");
        while (true) {
            System.out.println("Informe um número de 0 - 100 ou digite - 1 para sair");
            entrada = scan.nextInt();
            if (entrada == -1) {
                break;
            } else {
                if (entrada >= 0 && entrada <= 100) {
                    numPalpites++;
                    if (entrada == num) {
                        break;
                    } else {
                        if (num >= entrada - 10 && num <= entrada + 10) {
                            System.out.println("Está perto");
                        } else {
                            if (num >= entrada - 20 && num <= entrada + 20) {
                                System.out.println("Está longe");
                            } else {
                                System.out.println("Ihhhhh! Muito longe, cara");
                            }
                        }
                    }
                } else {
                    System.out.println("Palpite inválido");
                }
            }
        }
        if (entrada != -1) {
            System.out.println("Número de palpites: " + numPalpites);
            if (numPalpites == 1) {
                System.out.println("Nossa! Acertou na mosca");
            } else {
                if (numPalpites <= 5) {
                    System.out.println("Parabéns! Você é bom de adivinhação");
                } else {
                    if (numPalpites <= 10) {
                        System.out.println("Muito bom. Continue assim");
                    } else {
                        if (numPalpites <= 20) {
                            System.out.println("Mm... Podia ser melhor. Continue tentando");
                        } else {
                            System.out.println("Meio lerdo você, hein?");
                        }
                    }
                }
            }
        }
    }
}
