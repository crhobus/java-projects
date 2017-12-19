package View;

import java.util.Scanner;

public class Exe7 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String linha = "";
        System.out.println("Informações funcionário");
        System.out.println("Entre com o nome");
        String nome = scan.nextLine();
        System.out.println("Entre com a idade");
        int idade = scan.nextInt();
        System.out.println("Entre com o cargo");
        if (linha != null) {
            scan.nextLine();
        }
        linha = scan.nextLine();
        String cargo = linha;
        System.out.println("Entre com o salário bruto");
        double salarioBruto = scan.nextDouble();
        double salTotal = salarioBruto + (salarioBruto * 0.38);
        double salLiquido = (salTotal - (salTotal * 0.15)) + (salarioBruto * 0.20);
        System.out.println("Nome: " + nome + ", Idade: " + idade + ", Cargo: " + cargo);
        System.out.println("Salário Bruto: " + salarioBruto);
        System.out.println("Salário Líquido: " + salLiquido);
    }
}
