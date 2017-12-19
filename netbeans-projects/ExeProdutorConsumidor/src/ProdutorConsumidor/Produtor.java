package ProdutorConsumidor;

import java.util.Date;

public class Produtor extends Thread {

    private Fila fila;

    public Produtor(Fila fila) {
        this.fila = fila;
    }

    @Override
    public void run() {
        for (int i = 1; i < 1000; i++) {
            try {
                fila.insere(new Date().toString());
                sleep(30);
            } catch (Exception ex) {}
        }
    }
}
