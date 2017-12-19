package View;

import java.util.Scanner;

public class Exe9 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[][] jogoVelha = new String[3][3];
        System.out.println("Jogo da velha");
        System.out.println("Entre com o nome de um jogador");
        String jog1 = scan.nextLine();
        System.out.println("Entre com o nome do outro jogador");
        String jog2 = scan.nextLine();
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int y = 0; y < 3; y++) {
                jogoVelha[i][y] = "-";
                System.out.print(jogoVelha[i][y] + "  ");
            }
            System.out.print("\n");
        }
        while (true) {
            if (estadoJogar(jog1, 1, jogoVelha, scan, "x")) {
                break;
            }
            if (estadoJogar(jog2, 2, jogoVelha, scan, "o")) {
                break;
            }
        }
    }

    private static boolean estadoJogar(String jog, int prox, String[][] jogoVelha, Scanner scan, String tipoJogada) {
        System.out.println("\nJogador da vez " + jog);
        jogada(scan, jogoVelha, prox);
        if (venceu(jogoVelha, tipoJogada)) {
            System.out.println("\nVencedor: " + jog);
            return true;
        }
        if (trancou(jogoVelha)) {
            System.out.println("\nJogo trancado, não houve vencedor");
            return true;
        }
        return false;
    }

    private static boolean trancou(String[][] jogoVelha) {
        boolean trancou = true;
        for (int i = 0; i < 3; i++) {
            for (int y = 0; y < 3; y++) {
                if (jogoVelha[i][y].equals("-")) {
                    trancou = false;
                }
            }
        }
        if (trancou) {
            return true;
        }
        return false;
    }

    private static boolean venceu(String[][] jogoVelha, String tipoJogada) {
        if (jogoVelha[0][0].equals(tipoJogada) && jogoVelha[0][1].equals(tipoJogada) && jogoVelha[0][2].equals(tipoJogada)) {
            return true;
        }
        if (jogoVelha[1][0].equals(tipoJogada) && jogoVelha[1][1].equals(tipoJogada) && jogoVelha[1][2].equals(tipoJogada)) {
            return true;
        }
        if (jogoVelha[2][0].equals(tipoJogada) && jogoVelha[2][1].equals(tipoJogada) && jogoVelha[2][2].equals(tipoJogada)) {
            return true;
        }
        if (jogoVelha[0][0].equals(tipoJogada) && jogoVelha[1][0].equals(tipoJogada) && jogoVelha[2][0].equals(tipoJogada)) {
            return true;
        }
        if (jogoVelha[0][1].equals(tipoJogada) && jogoVelha[1][1].equals(tipoJogada) && jogoVelha[2][1].equals(tipoJogada)) {
            return true;
        }
        if (jogoVelha[0][2].equals(tipoJogada) && jogoVelha[1][2].equals(tipoJogada) && jogoVelha[2][2].equals(tipoJogada)) {
            return true;
        }
        if (jogoVelha[0][0].equals(tipoJogada) && jogoVelha[1][1].equals(tipoJogada) && jogoVelha[2][2].equals(tipoJogada)) {
            return true;
        }
        if (jogoVelha[2][0].equals(tipoJogada) && jogoVelha[1][1].equals(tipoJogada) && jogoVelha[0][2].equals(tipoJogada)) {
            return true;
        }
        return false;
    }

    private static void jogada(Scanner scan, String[][] jogoVelha, int jog) {
        int linha, coluna;
        while (true) {
            System.out.println("\nPosição jogada");
            while (true) {
                System.out.print("Informe a linha: ");
                linha = scan.nextInt();
                if (linha >= 1 && linha <= 3) {
                    break;
                } else {
                    System.out.println("Linha inválida, entre novamente");
                }
            }
            while (true) {
                System.out.print("Informe a coluna: ");
                coluna = scan.nextInt();
                if (coluna >= 1 && coluna <= 3) {
                    break;
                } else {
                    System.out.println("Coluna inválida, entre novamente");
                }
            }
            if (!jogoVelha[linha - 1][coluna - 1].equals("x") && !jogoVelha[linha - 1][coluna - 1].equals("o")) {
                break;
            }
            System.out.println("Posição inválida, entre novamente");
        }
        if (jog == 1) {
            jogoVelha[linha - 1][coluna - 1] = "x";
        } else {
            jogoVelha[linha - 1][coluna - 1] = "o";
        }
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int y = 0; y < 3; y++) {
                System.out.print(jogoVelha[i][y] + "  ");
            }
            System.out.print("\n");
        }
    }
}
