package Switch;

import java.util.Scanner;

public class TestTeste {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Ente com um numero inteiro: ");
        int num = input.nextInt();
        Teste test = new Teste(num);
    }
}
