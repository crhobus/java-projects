package NumerosInteiros;

public class Sistema {

    public static void main(String[] args) {
        NumerosInteiros grupo1 = new NumerosInteiros(4, 193);
        NumerosInteiros grupo2 = new NumerosInteiros(12345, 22346);
        int volta;

        System.out.println("Conta Numeros Primos Grupo 1 = " + grupo1.contaprimo());
        volta = grupo1.menorPrimo();
        System.out.println("Fatorial Menor Primos = " + grupo1.fatorial(volta));
        System.out.println("Multiplos de 3 e 5 = " + grupo2.mult3e5());
        System.out.println("Conta Numeros Primos grupo 2 = " + grupo2.contaprimo());
    }
}
