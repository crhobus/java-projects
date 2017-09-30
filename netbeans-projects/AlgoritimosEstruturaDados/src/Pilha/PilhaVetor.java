package Pilha;

public class PilhaVetor implements Pilha {

    private float vet[];
    private int n, tam;

    public PilhaVetor(int tam) {
        this.vet = new float[tam];
        this.tam = tam;
        this.n = 0;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i != n; i++) {
            s += vet[i];
        }
        return s;
    }

    public void push(float v) throws Exception {
        if (n == tam) {
            throw new Exception("ERRO: a capacidade da pilha estourou!");
        } else {
            this.vet[n] = v;
            this.n++;
        }
    }

    public float pop() throws Exception {
        float v;
        if (this.vazia()) {
            throw new Exception("ERRO: Pilha vazia!");
        } else {
            v = this.vet[n - 1];
            this.n--;
            return v;
        }
    }

    public float top() throws Exception {
        if (vet == null) {
            throw new Exception("Erro: Pilha Vazia");
        } else {
            return vet[n - 1];
        }
    }

    public boolean vazia() {
        if (vet == null) {
            return true;
        } else {
            return false;
        }
    }

    public void libera() {
        vet = null;
    }
}
