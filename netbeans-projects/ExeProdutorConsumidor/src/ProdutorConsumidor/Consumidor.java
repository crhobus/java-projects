package ProdutorConsumidor;

public class Consumidor extends Thread {

    private Fila fila;

    public Consumidor(Fila fila) {
        this.fila = fila;
    }

    @Override
    public void run() {
        for (int i = 1; i < 1000; i++) {
            try {
                fila.retira();
                this.sleep(60);
            } catch (Exception ex) {}
        }
    }
}
