package ProdConsumidor;

public class AddVariaveisExpressao extends Thread {

    private BufferVariaveisExpressao buffer;
    private String[] variaveis;

    public AddVariaveisExpressao(BufferVariaveisExpressao buffer, String[] variaveis) {
        this.buffer = buffer;
        this.variaveis = variaveis;
    }

    @Override
    public void run() {
        int n = variaveis.length;
        while (n != 0) {
            try {
                sleep(20);
            } catch (InterruptedException ex) {
            }
            n = buffer.addVariavel();
        }
    }
}
