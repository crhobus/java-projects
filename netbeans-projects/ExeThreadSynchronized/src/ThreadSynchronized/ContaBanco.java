package ThreadSynchronized;
//só faz sentido usar o wait com o synchronized
public class ContaBanco {

    private double saldo;

    public ContaBanco(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized void deposito(double valor) {//apenas uma thread só entra no método depósito por vez
        saldo += valor;
        System.out.println("Saldo: " + getSaldo());
        notifyAll();//acorda todas as threads
    }

    public synchronized void saque(double valor) {//apenas uma thread só entra no método saque por vez
        while (saldo < valor) {//wait sempre implementar com while, sempre no começo
            try {
                wait();//para dormir a thread
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        saldo -= valor;
        System.out.println("Saldo: " + getSaldo());
    }

    public double getSaldo() {
        return saldo;
    }
}
