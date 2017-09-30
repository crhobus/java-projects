package BuscaBinaria;

public class BuscaBinariaMain {

    public static void main(String[] args) {
        BuscaBinaria buscaBinaria = new BuscaBinaria(10);
        int vetor[] = new int[10];
        vetor[0] = 2;
        vetor[1] = 3;
        vetor[2] = 5;
        vetor[3] = 43;
        vetor[4] = 87;
        vetor[5] = 88;
        vetor[6] = 94;
        vetor[7] = 99;
        vetor[8] = 900;
        vetor[9] = 911;
        System.out.print("Conteúdo do vetor 2: ");
        for (int y = 0; y < vetor.length; y++) {
            System.out.print(vetor[y] + " ");
        }
        System.out.println();

        long tempoBuscaBinaria = System.currentTimeMillis();
        System.out.println("Busca vetor no vetor: " + buscaBinaria.buscaBinaria(vetor, 911));
        System.out.println("Tempo de execução Busca Binária : " + (System.currentTimeMillis() - tempoBuscaBinaria));

        System.out.println();

        long tempoBuscaBinariaRecursiva = System.currentTimeMillis();
        System.out.println("Busca vetor no vetor: " + buscaBinaria.buscaBinariaRecursiva(vetor, 0, 9, 43));
        System.out.println("Tempo de execução Busca Binária Recursiva : " + (System.currentTimeMillis() - tempoBuscaBinariaRecursiva));
    }
}
