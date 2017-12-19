package View;

import java.util.Scanner;

public class Exe2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] provaProf = new String[5];
        String[] provaAluno = new String[5];
        String nome;
        int numAcerto;
        System.out.println("Entre com o gabarito do professor para as questões de 1 a 5: alternativas: a/b/c/d/e");
        for (int i = 0; i < provaProf.length; i++) {
            while (true) {
                System.out.println("Informe a alternativa para questao " + (i + 1));
                provaProf[i] = scan.nextLine();
                if (provaProf[i].equalsIgnoreCase("a") || provaProf[i].equalsIgnoreCase("b") || provaProf[i].equalsIgnoreCase("c") || provaProf[i].equalsIgnoreCase("d") || provaProf[i].equalsIgnoreCase("e")) {
                    break;
                } else {
                    System.out.println("Alternativa inválida");
                }
            }
        }
        while (true) {
            System.out.println("Entre com o nome do aluno ou digite sair para finalizar");
            nome = scan.nextLine();
            if (nome.equalsIgnoreCase("sair")) {
                break;
            }
            for (int i = 0; i < provaAluno.length; i++) {
                while (true) {
                    System.out.println("Informe a alternativa para questao " + (i + 1));
                    provaAluno[i] = scan.nextLine();
                    if (provaAluno[i].equalsIgnoreCase("a") || provaAluno[i].equalsIgnoreCase("b") || provaAluno[i].equalsIgnoreCase("c") || provaAluno[i].equalsIgnoreCase("d") || provaAluno[i].equalsIgnoreCase("e")) {
                        break;
                    } else {
                        System.out.println("Alternativa inválida");
                    }
                }
            }
            numAcerto = 0;
            for (int i = 0; i < provaProf.length; i++) {
                if (provaProf[i].equalsIgnoreCase(provaAluno[i])) {
                    numAcerto++;
                }
            }
            System.out.println("Número de acertos: " + numAcerto + ", nota: " + (numAcerto * 2));
        }
    }
}
