package Arvore;

public class Arvore {

    private NoArvore raiz;

    public Arvore() {
        raiz = null;
    }

    public void inserirNo(int valor) {
        if (raiz == null) {
            raiz = new NoArvore(valor);
        } else {
            raiz.inserir(valor);
        }
    }

    public void preOrdem() {
        preOrdemAux(raiz);
    }

    private void preOrdemAux(NoArvore no) {
        if (no == null) {
            return;
        }
        System.out.printf("%d ", no.valor);
        preOrdemAux(no.noEsquerdo);
        preOrdemAux(no.noDireito);
    }

    public void ordem() {
        ordemAux(raiz);
    }

    private void ordemAux(NoArvore no) {
        if (no == null) {
            return;
        }
        ordemAux(no.noEsquerdo);
        System.out.printf("%d ", no.valor);
        ordemAux(no.noDireito);
    }

    public void posOrdem() {
        posOrdemAux(raiz);
    }

    private void posOrdemAux(NoArvore no) {
        if (no == null) {
            return;
        }
        posOrdemAux(no.noEsquerdo);
        posOrdemAux(no.noDireito);
        System.out.printf("%d ", no.valor);
    }
}
