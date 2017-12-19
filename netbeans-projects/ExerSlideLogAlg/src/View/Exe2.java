package View;

import java.util.Scanner;

public class Exe2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String linha = "";
        System.out.println("Dados de uma pessoa");
        System.out.println("Entre com o nome");
        String nome = scan.nextLine();
        System.out.println("Entre com a idade");
        int idade = scan.nextInt();
        System.out.println("Entre com o sexo");
        String sexo = scan.next();
        System.out.println("Entre com o peso");
        double peso = scan.nextDouble();
        System.out.println("Entre com a altura");
        double altura = scan.nextDouble();
        System.out.println("Entre com a profissão");
        if (linha != null) {
            scan.nextLine();
        }
        linha = scan.nextLine();
        String profissao = linha;
        System.out.println("Entre com telefone");
        String telefone = scan.nextLine();

        System.out.println("\nNome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Sexo: " + sexo);
        System.out.println("Peso: " + peso);
        System.out.println("Altura: " + altura);
        System.out.println("Profissão: " + profissao);
        System.out.println("Telefone" + telefone);
    }
}
