package ThreadLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ContaBanco {

    private ReadWriteLock lock = new ReentrantReadWriteLock();// consegue travar e pode definir para gravar ou leitura
    // se tem uma thread no writeLock nenhuma outra thread entra em outra writeLock
    // se tem uma thread no writeLock nenhuma outra thread entra em outra readLock
    // se tem uma thread no readLock nenhuma outra thread entra em outra writeLock
    // se tem uma trhead no readLock outra thread pode entrar em outra readLock
    private double saldo;

    public ContaBanco(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void deposito(double valor) {
        lock.writeLock().lock();
        try {
            saldo += valor;
            System.out.println("Saldo: " + getSaldo());
        } finally {// finaly garante que vai executar no final do metodo
            lock.writeLock().unlock();
        }
    }

    public void saque(double valor) {
        lock.writeLock().lock();
        try {
            saldo -= valor;
        } finally {// finaly garante que vai executar no final do metodo
            lock.writeLock().unlock();
        }
    }

    public double getSaldo() {
        return saldo;
    }
}
