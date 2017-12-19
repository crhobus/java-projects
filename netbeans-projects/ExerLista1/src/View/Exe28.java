package View;

import java.util.Scanner;

public class Exe28 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Classificação do usuário");
        System.out.println("Entre com o nome");
        String nome = scan.nextLine();
        System.out.println("Entre com a idade");
        int idade = scan.nextInt();
        if (idade < 0) {
            System.out.println("Idade inválida");
        } else {
            String classificacao;
            if (idade <= 4) {
                classificacao = "bebê";
            } else {
                if (idade >= 5 && idade <= 11) {
                    classificacao = "criança";
                } else {
                    if (idade >= 12 && idade <= 14) {
                        classificacao = "adolecente";
                    } else {
                        if (idade >= 15 && idade <= 30) {
                            classificacao = "jovem";
                        } else {
                            if (idade >= 31 && idade <= 45) {
                                classificacao = "Maduro";
                            } else {
                                if (idade >= 46 && idade <= 60) {
                                    classificacao = "Adulto";
                                } else {
                                    if (idade >= 61 && idade <= 75) {
                                        classificacao = "Idoso I";
                                    } else {
                                        if (idade >= 76 && idade <= 90) {
                                            classificacao = "Idoso II";
                                        } else {
                                            classificacao = "Idoso III";
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("Nome: " + nome + ", Idade: " + idade + ", classificação: " + classificacao);
        }
    }
}
