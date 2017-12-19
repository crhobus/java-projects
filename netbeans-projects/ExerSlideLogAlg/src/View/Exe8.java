package View;

import java.util.Scanner;

public class Exe8 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Funcionário");
        System.out.println("Entre com o nome");
        String nome = scan.nextLine();
        System.out.println("Entre com o número de horas Trabalhadas");
        int numHorasTrab = scan.nextInt();
        System.out.println("Entre com o número de dependentes");
        int numDependantes = scan.nextInt();

        double salario = numHorasTrab * 10;
        double descINSS = salario * 0.085;
        double descIR = salario * 0.05;

        System.out.println("Nome: " + nome);
        System.out.println("Salário Bruto: " + salario);
        System.out.println("Desconto INSS: " + descINSS);
        System.out.println("Desconto IR: " + descIR);
        System.out.println("Gratificação: " + (numDependantes * 20));
        System.out.println("Salário líquido: " + ((salario - descINSS - descIR) + (numDependantes * 20)));
    }
}
