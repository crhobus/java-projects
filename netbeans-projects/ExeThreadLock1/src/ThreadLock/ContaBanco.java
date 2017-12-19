package ThreadLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ContaBanco {

    private Lock lock = new ReentrantLock();// basicamente tem a mesma função do synchronized
    // A thread entra no lock do depósito somente quando a outra sair do unlock
    // do saque, e assim vice versa
    private double saldo;

    public ContaBanco(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void deposito(double valor) {
        lock.lock();
        try {
            saldo += valor;
            System.out.println("Saldo: " + getSaldo());
        } finally {// finaly garante que vai executar no final do metodo
            lock.unlock();
        }
    }

    public void saque(double valor) {
        lock.lock();
        try {
            saldo -= valor;
        } finally {// finaly garante que vai executar no final do metodo
            lock.unlock();
        }
    }

    public double getSaldo() {
        return saldo;
    }
}
