package ThreadLock;

public class DepositoThread extends Thread {

    private ContaBanco contaBanco;
    private double quantidade;

    public DepositoThread(ContaBanco contaBanco, double quantidade) {
        this.contaBanco = contaBanco;
        this.quantidade = quantidade;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < 100 && !isInterrupted(); i++) {
                contaBanco.deposito(quantidade);
                sleep(100);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
