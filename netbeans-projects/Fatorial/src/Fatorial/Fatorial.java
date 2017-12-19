package Fatorial;


public class Fatorial {

    public static void main(String[] args) {
        int num = 6, fatorial, aux;
        fatorial = 1;
        aux = 1;
        while (aux <= num) {
            fatorial = fatorial * aux;
            aux++;
        }
        System.out.println("Fatorial de " + num);
        System.out.println(" = " + fatorial);
    }
}
