package ThreadLock;

public class Sistema {

    public static void main(String[] args) {
        ContaBanco conta = new ContaBanco(0);
        DepositoThread deposito = new DepositoThread(conta, 10);
        SaqueThread saque = new SaqueThread(conta, 10);
        deposito.start();// dispara a Thread
        saque.start();
    }
}
