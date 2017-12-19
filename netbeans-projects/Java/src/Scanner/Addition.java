package Scanner;

import java.util.Scanner;

public class Addition {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num1;
        int num2;
        int sum;
        System.out.print("Entre com o primeiro numero inteiro: ");
        num1 = input.nextInt();//pega o primeiro numero digitado na console
        System.out.print("Entre com o segundo numero inteiro: ");
        num2 = input.nextInt();//pega o segundo numero digitado na console
        sum = num1 + num2;
        System.out.printf("Soma é %d\n", sum);
    }
}
