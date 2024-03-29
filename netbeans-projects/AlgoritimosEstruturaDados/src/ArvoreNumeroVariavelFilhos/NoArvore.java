package ArvoreNumeroVariavelFilhos;

public class NoArvore {

    private int info;
    private NoArvore prim;
    private NoArvore prox;

    public NoArvore(int info) {
        this.info = info;
        prim = null;
        prox = null;
    }

    public NoArvore(int info, NoArvore sa) {
        this.info = info;
        prox = sa.prim;
        prim = sa;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public NoArvore getPrim() {
        return prim;
    }

    public void setPrim(NoArvore prim) {
        this.prim = prim;
    }

    public NoArvore getProx() {
        return prox;
    }

    public void setProx(NoArvore prox) {
        this.prox = prox;
    }

    @Override
    public String toString() {
        return info + "";
    }
}
