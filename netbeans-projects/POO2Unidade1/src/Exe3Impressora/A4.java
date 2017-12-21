package Exe3Impressora;

public class A4 implements Folha {

    private int linhaAtual;

    public int getLinhaAtual() {
        return linhaAtual;
    }

    public void setLinhaAtual(int linhaAtual) {
        this.linhaAtual = linhaAtual;
    }

    public boolean imprimir(String texto) {
        int colunaAtual = 0;
        for (int i = 1; i <= texto.length(); i++) {
            System.out.print(texto.charAt(i - 1));
            colunaAtual++;
            if (colunaAtual == 10) {
                System.out.print("\n");
                colunaAtual = 0;
                linhaAtual++;
                if (linhaAtual == 7) {
                    return true;
                }
            }
        }
        System.out.println();
        return false;
    }
}
