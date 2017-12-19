package Exe1;

public class Numero {

    private int[] numeros;
    private int cont = 0;

    public Numero(int tamanho) {
        numeros = new int[tamanho];
    }

    public void setNumero(int numero) {
        if (cont < numeros.length) {
            numeros[cont] = numero;
            cont++;
        } else {
            System.out.println("Tamanho limite de espaço esgotado");
        }
    }

    public int getMenor() {
        int menor = numeros[0];
        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i] < menor) {
                menor = numeros[i];
            }
        }
        return menor;
    }
}
