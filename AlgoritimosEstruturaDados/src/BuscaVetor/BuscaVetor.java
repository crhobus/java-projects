package BuscaVetor;

public class BuscaVetor {

    public int busca(int vet[], int elem) {
        int n = vet.length;
        for (int i = 0; i < n - 1; i++) {
            if (elem == vet[i]) {
                return i;
            }
        }
        return -1;
    }

    public int buscaLinearOrdenada(int vet[], int elem) {
        int n = vet.length;
        for (int i = 0; i < n - 1; i++) {
            if (elem == vet[i]) {
                return i;
            } else {
                if (elem < vet[i]) {
                    return -1;
                }
            }
        }
        return -1;
    }
}
