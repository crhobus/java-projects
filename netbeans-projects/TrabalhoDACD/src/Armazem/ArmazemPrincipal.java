package Armazem;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ArmazemPrincipal {

    private HashMap<String, Double> armazem;
    private Lock lock;

    public ArmazemPrincipal() {
        armazem = new HashMap<String, Double>();
        lock = new ReentrantLock();
    }

    public void insere(String chave, double valor) {
        lock.lock();
        try {
            armazem.put(chave, valor);
        } finally {
            lock.unlock();
        }
    }

    public Double leitura(String chave) {
        lock.lock();
        try {
            if (armazem.containsKey(chave)) {
                return armazem.get(chave);
            }
            return null;
        } finally {
            lock.unlock();
        }
    }
}
