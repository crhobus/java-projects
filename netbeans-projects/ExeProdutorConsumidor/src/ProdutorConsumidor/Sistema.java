package ProdutorConsumidor;

public class Sistema {

    public static void main(String[] args) {
        Fila fila = new Fila();
        Produtor produtor = new Produtor(fila);
        Consumidor consumidor = new Consumidor(fila);
        produtor.start();
        consumidor.start();
    }
}
