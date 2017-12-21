package FibonacciRecursivo;

public class Fibonacci {

    private long fibonacci(long numero) {
        if (numero == 0 || numero == 1) {
            return numero;
        } else {
            return fibonacci(numero - 1) + fibonacci(numero - 2);
        }
    }

    public void mostrarFibonacci() {
        for (int i = 0; i <= 10; i++) {
            System.out.printf("Fibonacci de %d é: %d\n", i, fibonacci(i));
        }
    }
}
