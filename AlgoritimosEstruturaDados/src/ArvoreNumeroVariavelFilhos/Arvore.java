package ArvoreNumeroVariavelFilhos;

public class Arvore {

    private NoArvore raiz;

    public Arvore() {
        raiz = null;
    }

    public NoArvore criaNo(int info) {
        NoArvore novo = new NoArvore(info);
        raiz = novo;
        return novo;
    }

    public void insereFilho(NoArvore pai, NoArvore filho) {
        filho.setProx(pai.getPrim());
        pai.setPrim(filho);
        raiz = pai;
    }

    @Override
    public String toString() {
        return imprime(raiz);
    }

    private String imprime(NoArvore no) {
        String s = new String("");
        s = s + "<";
        s = s + no.getInfo();
        NoArvore p = no.getPrim();
        while (p != null) {
            s = s + imprime(p);
            p = p.getProx();
        }
        s = s + ">";
        return s;
    }

    public boolean pertence(int info) {
        return pertence(raiz, info);
    }

    private boolean pertence(NoArvore no, int info) {
        if (no.getInfo() == info) {
            return true;
        } else {
            NoArvore p = no.getPrim();
            while (p != null) {
                if (pertence(p, info)) {
                    return true;
                }
                p = p.getProx();
            }
        }
        return false;
    }

    public int altura() {
        return altura(raiz);
    }

    private int altura(NoArvore no) {
        int altmax = -1;
        NoArvore p = no.getPrim();
        while (p != null) {
            int h = altura(p);
            if (h > altmax) {
                altmax = h;
            }
            p = p.getProx();
        }
        return altmax + 1;
    }

    public int pares() {
        return pares(this.raiz);
    }

    private int pares(NoArvore no) {
        int pares = 0;
        if (no == null) {
            return pares;
        } else {
            if (no.getInfo() % 2 == 0) {
                pares++;
            }
        }
        NoArvore p = no.getPrim();
        while (p != null) {
            pares += pares(p);
            p = p.getProx();
        }
        return pares;
    }

    public int folhas() {
        return folhas(this.raiz);
    }

    private int folhas(NoArvore no) {
        int cont = 0;
        if (no == null) {
            return cont;
        }
        if (no.getPrim() == null) {
            return 1;
        }
        NoArvore p = no.getPrim();
        while (p != null) {
            cont += folhas(p);
            p = p.getProx();
        }
        return cont;
    }

    public boolean igual(Arvore a) {
        return igual(raiz, a.raiz);
    }

    private boolean igual(NoArvore a, NoArvore b) {
        boolean igual = false;
        if (a.getInfo() == b.getInfo()) {
            igual = true;
        }
        NoArvore p = a.getPrim();
        NoArvore q = b.getPrim();
        while (p != null && q != null) {
            if (!igual(p, q)) {
                return false;
            }
            p = p.getProx();
            q = q.getProx();
        }
        return igual;
    }

    public Arvore copia() {
        return copia(raiz);
    }

    private Arvore copia(NoArvore no) {
        if (no == null) {
            return null;
        } else {
            Arvore nova = new Arvore();
            nova.raiz = no;
            return nova;
        }
    }
}
