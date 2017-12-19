package View;

import java.util.Scanner;

public class Exe14 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double[] vet = new double[5];
        System.out.println("Soma casas decimais de um vetor");
        for (int i = 0; i < vet.length; i++) {
            System.out.println("Entre com o " + (i + 1) + "° elemento");
            vet[i] = scan.nextDouble();
        }
        double total = 0;
        String str;
        System.out.println("\nVetor:");
        for (int i = 0; i < vet.length; i++) {
            System.out.println("Valor:(" + (i + 1) + ") : " + vet[i]);
            str = Double.toString(vet[i]).substring(Double.toString(vet[i]).lastIndexOf("."));
            str = "0" + str;
            total += Double.parseDouble(str);
        }
        System.out.println("\nTotal: " + total);
    }
}
