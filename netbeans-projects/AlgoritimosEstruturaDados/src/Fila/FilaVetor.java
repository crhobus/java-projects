package Fila;

public class FilaVetor implements Fila {

    private int n;
    private int ini;
    private int tam;
    private int vet[];

    public FilaVetor(int tam) {
        this.vet = new int[tam];
        this.tam = tam;
        this.ini = 0;
        this.n = 0;
    }

    public FilaVetor concatena(FilaVetor f2) throws Exception {
        FilaVetor f3 = new FilaVetor(this.tam + f2.tam);
        int i = ini;
        int fim1 = (ini + n) % tam;
        while (i != fim1) {
            f3.insere(vet[i]);
            i = (i + 1) % this.tam;
        }
        int j = f2.ini;
        int fim2 = (f2.ini + f2.n) % f2.tam;
        while (j != fim2) {
            f3.insere(f2.vet[j]);
            j = (j + 1) % f2.tam;
        }
        return f3;
    }

    public FilaVetor merge(FilaVetor f2) throws Exception {
        FilaVetor f3 = new FilaVetor(this.tam + f2.tam);
        int fim1 = (ini + n) % tam;
        int fim2 = (f2.ini + f2.n) % f2.tam;
        int i = ini;
        int j = f2.ini;
        while (i != fim1 && j != fim2) {
            f3.insere(vet[i]);
            f3.insere(f2.vet[j]);
            i = (i + 1) % this.tam;
            j = (j + 1) % f2.tam;
        }
        while (i != fim1) {
            f3.insere(vet[i]);
            i = (i + 1) % this.tam;
        }
        while (j != fim2) {
            f3.insere(f2.vet[j]);
            j = (j + 1) % f2.tam;
        }
        return f3;
    }

    @Override
    public String toString() {
        int fim;
        String s = "";
        fim = (ini + n) % tam;
        for (int i = ini; i != fim; i++) {
            s += vet[i] + " ";
        }
        return s;
    }

    public void insere(int v) throws Exception {
        int fim;
        if (n == tam) {
            throw new Exception("ERRO: a capacidade da fila estourou!");
        } else {
            fim = (ini + n) % tam;
            this.vet[fim] = v;
            this.n++;
        }
    }

    public int retira() throws Exception {
        int v;
        if (n == 0) {
            throw new Exception("ERRO: fila vazia!");
        } else {
            v = this.vet[ini];
            ini = (ini + 1) % tam;
            this.n--;
            return v;
        }
    }

    public boolean vazia() {
        if (n == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void libera() {
        this.n = 0;
        this.ini = 0;
    }

    public FilaVetor copia() throws Exception {
        FilaVetor novaFila = new FilaVetor(this.tam);
        int fim = (ini + n) % tam;
        int inicio = ini;
        while (inicio != fim) {
            novaFila.insere(vet[inicio]);
            inicio++;
        }
        return novaFila;
    }

    public FilaVetor copiaInvertido() throws Exception {
        FilaVetor novaFila = new FilaVetor(this.tam);
        int fim = (ini + n) % tam;
        int inicio = ini;
        while (inicio != fim) {
            novaFila.insere(vet[fim - 1]);
            fim--;
        }
        return novaFila;
    }

    public FilaVetor filtraMenores(int x) throws Exception {
        FilaVetor novaFila = new FilaVetor(this.tam);
        int fim = (ini + n) % tam;
        int inicio = ini;
        while (inicio != fim) {
            if (vet[inicio] > x) {
                novaFila.insere(vet[inicio]);
            }
            inicio++;
        }
        return novaFila;
    }
}
