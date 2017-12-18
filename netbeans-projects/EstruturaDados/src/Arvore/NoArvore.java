package Arvore;

public class NoArvore {

    NoArvore noEsquerdo;
    NoArvore noDireito;
    int valor;

    public NoArvore(int noValor) {
        this.valor = noValor;
        noEsquerdo = noDireito = null;
    }

    public void inserir(int inserirValor) {
        if (inserirValor < valor) {
            if (noEsquerdo == null) {
                noEsquerdo = new NoArvore(inserirValor);
            } else {
                noEsquerdo.inserir(inserirValor);
            }
        } else {
            if (inserirValor > valor) {
                if (noDireito == null) {
                    noDireito = new NoArvore(inserirValor);
                } else {
                    noDireito.inserir(inserirValor);
                }
            }
        }
    }
}
