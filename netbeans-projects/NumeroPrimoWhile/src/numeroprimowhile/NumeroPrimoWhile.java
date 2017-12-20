package numeroprimowhile;

public class NumeroPrimoWhile {

    public static void main(String[] args) {
        int numero = 7669, aux;
        boolean ehprimo;
        aux = numero - 1;
        ehprimo = true;
        while (aux > 1) {
            if ((numero % aux) == 0) {
                ehprimo = false;
                aux = 1;
            }
            aux--;
        }
        if (ehprimo) {
            System.out.println(numero + " eh primo ");
        } else {
            System.out.println(numero + " não eh primo ");
        }
    }
}
