package FatorialIterativo;

public class Fatorial {

    private long fatorial(long numero) {
        long resultado = 1;
        for (long i = numero; i >= 1; i--) {
            resultado *= i;
        }
        return resultado;
    }

    public void mostrarFatoriais() {
        for (int i = 0; i <= 20; i++) {
            System.out.printf("%d! = %d\n", i, fatorial(i));
        }
    }
}
