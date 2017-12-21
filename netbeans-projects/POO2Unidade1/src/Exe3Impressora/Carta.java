package Exe3Impressora;

public class Carta implements Folha {

    private int linhaAtual;

    public boolean imprimir(String texto) {
        int colunaAtual = 0;
        for (int i = 1; i <= texto.length(); i++) {
            System.out.print(texto.charAt(i - 1));
            colunaAtual++;
            if (colunaAtual == 8) {
                System.out.print("\n");
                colunaAtual = 0;
                linhaAtual++;
                if (linhaAtual == 10) {
                    return true;
                }
            }
        }
        System.out.println();
        return false;
    }
}
