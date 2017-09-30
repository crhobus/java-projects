package Pilha;

public class NoLista {

    private float info;
    private NoLista prox;

    public float getInfo() {
        return info;
    }

    public void setInfo(float info) {
        this.info = info;
    }

    public NoLista getProx() {
        return prox;
    }

    public void setProx(NoLista prox) {
        this.prox = prox;
    }

    @Override
    public String toString() {
        return info + "";
    }
}
