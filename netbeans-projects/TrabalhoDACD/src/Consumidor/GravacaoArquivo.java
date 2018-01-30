package Consumidor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Semaphore;

public class GravacaoArquivo extends Thread {

    private Semaphore semaforo;
    private File arq;
    private PrintWriter saida;

    public GravacaoArquivo(Semaphore semaforo, File arq) {
        this.semaforo = semaforo;
        this.arq = arq;
    }

    public void gravarLinha(String linha) {
        saida.println(linha);
    }

    public void fecharArquivo() {
        saida.close();
    }

    public PrintWriter isArqSaida() {
        return saida;
    }

    @Override
    public void run() {
        try {
            try {
                sleep(5);
            } catch (InterruptedException ex) {
            }
            while (!semaforo.tryAcquire());
            saida = new PrintWriter(new BufferedWriter(new FileWriter(arq)));
            semaforo.release();
        } catch (IOException ex) {
        }
    }
}
