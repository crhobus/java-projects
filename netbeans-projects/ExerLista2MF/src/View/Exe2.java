package View;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Exe2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Jogo da forca");
        String palavra = JOptionPane.showInputDialog(null, "Entre com uma palavra", "Entrada", JOptionPane.INFORMATION_MESSAGE);
        if (palavra != null) {
            String tentativas = JOptionPane.showInputDialog(null, "Entre com o número de tentativas", "Entrada", JOptionPane.INFORMATION_MESSAGE);
            if (tentativas != null) {
                int numTentativas = Integer.parseInt(tentativas);//Número de tentativas para tentar certar a palavra
                char[] vetAux = new char[palavra.length()];//caracteres que serão exibidos na tela
                int[] indice;//indice onde se encontra a letra para ser substituida
                for (int i = 0; i < palavra.length(); i++) {
                    vetAux[i] = 'x';
                    System.out.print("x");
                }
                System.out.println();
                char letra;
                char[] vet;
                boolean encontrouLetra;//flag letra digitada encontrada
                boolean acertouForca = true;//flag verificar término
                for (int i = 0; i < numTentativas; i++) {
                    encontrouLetra = false;
                    indice = new int[palavra.length()];//novo vet de índice
                    System.out.println("Entre com uma letra, " + (i + 1) + "ª tentativa ");
                    letra = scan.next().charAt(0);//leitura somente uma letra
                    if (letra == '?') {//? não pode ser usada pois é usada para substituir letra encontrada na palavra
                        System.out.println("Letra não encontrada");
                        acertouForca = false;
                        continue;
                    }
                    vet = palavra.toCharArray();
                    for (int y = 0; y < vet.length; y++) {//verifica letra a letra
                        if (vet[y] == letra) {
                            vet[y] = '?';//substitui letra por ?
                            indice[y] = 1;//seta a posição a ser trocada
                            encontrouLetra = true;//encontrou uma letra
                        }
                    }
                    palavra = "";
                    for (int j = 0; j < vet.length; j++) {//atualiza palavra
                        palavra += vet[j];
                    }
                    if (!encontrouLetra) {
                        System.out.println("Letra não encontrada");
                    } else {
                        for (int y = 0; y < indice.length; y++) {//atualiza caracteres que serão exibidos na tela, substitui x pela letra detectada através índice setado
                            if (indice[y] == 1) {
                                vetAux[y] = letra;
                            }
                        }
                        acertouForca = true;
                        for (int y = 0; y < vetAux.length; y++) {//exibe na console com as respectivas letras atualizadas
                            System.out.print(vetAux[y]);
                            if (vetAux[y] == 'x') {//se encontrar um x ainda não terminou
                                acertouForca = false;
                            }
                        }
                        System.out.println();
                        if (acertouForca) {//fim
                            System.out.println("Você acertou");
                            break;
                        }
                    }
                }
                if (!acertouForca) {//tempo limite
                    System.out.println("Tempo limite esgotado, tente novamente");
                }
            }
        }
    }
}
