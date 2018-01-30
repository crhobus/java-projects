package ProdConsumidor;

import Armazem.ArmazemPrincipal;

public class LeituraArmazemDir1 extends Thread {

    private BufferVariaveisExpressao buffer;
    private ArmazemPrincipal armazemProd;
    private String[] variaveis;

    public LeituraArmazemDir1(BufferVariaveisExpressao armazem, String[] variaveis, ArmazemPrincipal armazemProd) {
        this.buffer = armazem;
        this.variaveis = variaveis;
        this.armazemProd = armazemProd;
    }

    @Override
    public void run() {
        for (int i = 0; i < variaveis.length; i++) {
            try {
                sleep(100);
            } catch (InterruptedException ex) {
            }
            buffer.set(variaveis[i], armazemProd);
        }
    }
}
