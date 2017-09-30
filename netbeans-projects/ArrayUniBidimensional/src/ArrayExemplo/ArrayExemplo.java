package ArrayExemplo;

public class ArrayExemplo {

    public static void main(String[] args) {
        int i, numeros[], somatorio, media, multip3, somaimp, menor = 999, quantosImpar = 0, posicao = -1;
        numeros = new int[11];
        for (i = 0; i < 11; i++) {
            numeros[i] = i * 5 + 8;
        }
        System.out.println("Veja o conteudo do vetor\n");
        for (i = 0; i < 11; i++) {
            System.out.println("A posição " + i + " contem " + numeros[i]);
        }
        somatorio = 0;
        for (i = 0; i < 11; i++) {
            somatorio += numeros[i];
        }
        media = somatorio / 10;
        System.out.println("\n");
        System.out.println("Somatorios do elementos: " + somatorio);
        System.out.println("Media dos Elementos = " + media);
        multip3 = 0;
        for (i = 0; i < 11; i++) {
            if ((numeros[i] % 3) == 0) {
                multip3++;
                somatorio += numeros[i];
            }
        }
        System.out.println("Muttiplos de 3: " + multip3);
        for (i = 0; i < 11; i++) {
            if ((numeros[i] % 3) == 0) {
                if ((numeros[i] % 2) == 0) {
                    if (numeros[i] < menor) {
                        menor = numeros[i];
                        posicao = i;
                        System.out.println("posição do numero é: " + posicao);
                        System.out.println("Para a posição do menor numero par e mutltiplo de 3, simuntaneamente: " + numeros[i]);
                    }

                }
            }
        }
        somaimp = 0;
        for (i = 0; i < 11; i++) {
            if ((numeros[i] % 2) != 0) {
                somaimp += numeros[i];
                quantosImpar++;
            }
        }

        System.out.println("Média Aritimética dos numeros ímpares: " + somaimp / quantosImpar);
    }
}
