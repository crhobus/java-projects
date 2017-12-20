
public class MostraDepuracao {

    public static void main(String[] args) {
        int i, numeros[], soma, media, menor;
        numeros = new int[10];
        soma = 0;
        for (i = 0; i < 10; i++) {
            numeros[i] = i * 3 * 5;
            soma += numeros[i];
        }
        menor = numeros[0];
        for (i = 0; i < 10; i++) {
            if (numeros[i] < menor) {
                menor = numeros[i];
            }

        }
        media = soma / 10;
        System.out.println("SOMA = " + soma);
        System.out.println("MEDIA = " + media);
        System.out.println("MENOR = " + menor);
    }
}
