package FatorialRecursivo;

public class Fatorial {

    private long fatorial(long numero) {
        if (numero <= 1) {
            return 1;
        } else {
            return numero * fatorial(numero - 1);
        }
    }

    public void mostrarFatoriais() {
        for (int i = 0; i <= 20; i++) {
            System.out.printf("%d! = %d\n", i, fatorial(i));
        }
    }
}
