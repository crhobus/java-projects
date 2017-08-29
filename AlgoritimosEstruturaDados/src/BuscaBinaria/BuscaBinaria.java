package BuscaBinaria;

public class BuscaBinaria {

    private int n;

    public BuscaBinaria(int n) {
        this.n = n;
    }

    public int buscaBinaria(int vet[], int elem) {
        int ini = 0;
        int fim = n - 1;
        int meio;
        while (ini <= fim) {
            meio = (ini + fim) / 2;
            if (elem < vet[meio]) {
                fim = meio - 1;
            } else {
                if (elem > vet[meio]) {
                    ini = meio + 1;
                } else {
                    return meio;
                }
            }
        }
        return -1;
    }

    public int buscaBinariaRecursiva(int vet[], int ini, int fim, int elem) {
        if (ini < fim) {
            int meio = ini + (fim - ini) / 2;
            if (elem < vet[meio]) {
                return buscaBinariaRecursiva(vet, ini, meio, elem);
            } else {
                if (elem > vet[meio]) {
                    return buscaBinariaRecursiva(vet, meio + 1, fim, elem);
                } else {
                    return meio;
                }
            }
        }
        return -1;
    }
}
