package ColecaoMatriz;

public class UsaColecaoMatriz {

    public static void main(String[] args) {
        ColecaoMatriz meu = new ColecaoMatriz(100);
        ColecaoMatriz teu = new ColecaoMatriz(100);
        int carega = 4;
        boolean matriz_normal = true;
        boolean matriz_transposta = false;
        int fatorial, aux;
        int ordenar_linha = 2;
        System.out.println("Objeto MEU:");
        System.out.println("A Matriz foi GERADA: " + meu.carregar(4));
        meu.carregar(carega);
        meu.mostrar(matriz_normal);
        meu.mostrar(matriz_transposta);
        meu.ordenar(ordenar_linha);
        meu.mostrar(matriz_transposta);
        System.out.println("Um novo elemento foi colocado nesta posição: = " + meu.set(1, 1, 8));
        meu.mostrar(matriz_normal);
        meu.mostrar(matriz_transposta);
        System.out.println("O elemento do conjunto retornado é: = " + meu.get(1, 2));
        System.out.println("O menor valor da MATRIZ:  = " + meu.menor());
        fatorial = 1;
        for (aux = meu.menor(); aux > 1; aux--) {
            fatorial = fatorial * aux;
        }
        System.out.println("Fatorial do menor elemento da matriz: " + fatorial);
        System.out.println("Soma da MATRIZ:  = " + meu.somatorio());
        System.out.println("A soma desta linha da MATRIZ:  = " + meu.somalinha(2));
        System.out.println("A soma desta coluna da MATRIZ:  = " + meu.somacoluna(0));
        System.out.println("A soma da diagonal desta MATRIZ:  = " + meu.somadiagonal());

        System.out.println("\n");
        System.out.println("Objeto TEU:");
        System.out.println("A Matriz foi GERADA: " + teu.carregar(4));
        meu.carregar(carega);
        meu.mostrar(matriz_normal);
        meu.mostrar(matriz_transposta);
        meu.ordenar(ordenar_linha);
        meu.mostrar(matriz_transposta);
        System.out.println("Um novo elemento foi colocado nesta posição: = " + teu.set(1, 1, 70));
        teu.mostrar(matriz_normal);
        teu.mostrar(matriz_transposta);
        System.out.println("O elemento do conjunto retornado é: = " + teu.get(1, 2));
        System.out.println("O menor valor da MATRIZ:  = " + teu.menor());
        System.out.println("Soma da MATRIZ:  = " + teu.somatorio());
        System.out.println("A soma desta linha da MATRIZ:  = " + teu.somalinha(2));
        System.out.println("A soma desta coluna da MATRIZ:  = " + teu.somacoluna(0));
        System.out.println("A soma da diagonal desta MATRIZ:  = " + teu.somadiagonal());
    }
}
