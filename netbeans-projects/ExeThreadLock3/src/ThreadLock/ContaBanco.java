package ThreadLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ContaBanco {

    private Lock lock = new ReentrantLock();
    private Condition temDinheiro = lock.newCondition();
    // O Condition funciona igual ao wait notifally do synchronized
    private double saldo;

    public ContaBanco(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void deposito(double valor) {
        lock.lock();
        try {
            saldo += valor;
            System.out.println("Saldo: " + getSaldo());
            temDinheiro.signal();
        } finally {// finaly garante que vai executar no final do metodo
            lock.unlock();
        }
    }

    public void saque(double valor) throws InterruptedException {
        lock.lock();
        try {
            while (saldo < valor) {
                temDinheiro.await();
            }
            saldo -= valor;
        } finally {// finaly garante que vai executar no final do metodo
            lock.unlock();
        }
    }

    public double getSaldo() {
        return saldo;
    }
}
