package View;

import java.util.Scanner;

public class Exe7 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Tradutor");
        System.out.println("1 - Português para Inglês");
        System.out.println("2 - Inglês para Portugues");
        System.out.println("Entre com uma das opções");
        int opcao = scan.nextInt();
        String palavra;
        switch (opcao) {
            case 1:
                System.out.println("Palavras - Português para Inglês");
                System.out.println("Cachorro - Tempo - Amor - Cidade - Feliz");
                System.out.println("Entre com uma das palavras");
                palavra = scan.next();
                if (palavra.equalsIgnoreCase("Cachorro")) {
                    System.out.println("Tradução: Dog");
                } else {
                    if (palavra.equalsIgnoreCase("Tempo")) {
                        System.out.println("Tradução: Time");
                    } else {
                        if (palavra.equalsIgnoreCase("Amor")) {
                            System.out.println("Tradução: Love");
                        } else {
                            if (palavra.equalsIgnoreCase("Cidade")) {
                                System.out.println("Tradução: City");
                            } else {
                                if (palavra.equalsIgnoreCase("Feliz")) {
                                    System.out.println("Tradução: Happy");
                                } else {
                                    System.err.println("Palavra inválida");
                                }
                            }
                        }
                    }
                }
                break;
            case 2:
                System.out.println("Palavras - Inglês para Portugues");
                System.out.println("Dog - Time - Love - City - Happy");
                System.out.println("Entre com uma das palavras");
                palavra = scan.next();
                if (palavra.equalsIgnoreCase("Dog")) {
                    System.out.println("Tradução: Cachorro");
                } else {
                    if (palavra.equalsIgnoreCase("Time")) {
                        System.out.println("Tradução: Tempo");
                    } else {
                        if (palavra.equalsIgnoreCase("Love")) {
                            System.out.println("Tradução: Amor");
                        } else {
                            if (palavra.equalsIgnoreCase("City")) {
                                System.out.println("Tradução: Cidade");
                            } else {
                                if (palavra.equalsIgnoreCase("Happy")) {
                                    System.out.println("Tradução: Feliz");
                                } else {
                                    System.err.println("Palavra inválida");
                                }
                            }
                        }
                    }
                }
                break;
            default:
                System.err.println("Opção inválida");
                break;
        }
    }
}
