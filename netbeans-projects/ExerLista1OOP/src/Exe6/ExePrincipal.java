package Exe6;

import java.util.Scanner;

public class ExePrincipal {

    public ExePrincipal() {
        Scanner scan = new Scanner(System.in);
        Salario salario = new Salario();
        System.out.println("Salário do usuário");
        System.out.println("Entre com o número de horas trabalhadas");
        salario.setNumHoras(scan.nextInt());
        System.out.println("Entre com o valor das horas");
        salario.setValorHora(scan.nextDouble());
        System.out.println("Entre com o número de horas extras (50%) trabalhadas");
        salario.setNumHorasExtras50(scan.nextInt());
        System.out.println("Entre com o número de horas extras (100%) trabalhadas");
        salario.setNumHorasExtras100(scan.nextInt());
        System.out.println("Salário bruto: R$ " + salario.getSalario());
    }
}
