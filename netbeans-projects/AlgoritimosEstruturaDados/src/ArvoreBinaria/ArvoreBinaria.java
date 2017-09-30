package ArvoreBinaria;

public class ArvoreBinaria {

    private NoArvoreBinaria raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public NoArvoreBinaria insere(int v, NoArvoreBinaria esq, NoArvoreBinaria dir) {
        NoArvoreBinaria no = new NoArvoreBinaria(v, esq, dir);
        raiz = no;
        return no;
    }

    public boolean vazia() {
        return (raiz == null);
    }

    @Override
    public String toString() {
        return imprimePre(raiz);
    }

    private String imprimePre(NoArvoreBinaria no) {
        String s = new String("");
        s = s + "< ";
        if (no != null) {
            s = s + no.getInfo();
            s = s + imprimePre(no.getEsq());
            s = s + imprimePre(no.getDir());
        }
        s = s + ">";
        return s;
    }

    public String imprimeSim() {
        return imprimeSim(raiz);
    }

    private String imprimeSim(NoArvoreBinaria no) {
        String s = new String("");
        s = s + "< ";
        if (no != null) {
            s = s + imprimeSim(no.getEsq());
            s = s + no.getInfo();
            s = s + imprimeSim(no.getDir());
        }
        s = s + ">";
        return s;
    }

    public String imprimePos() {
        return imprimePos(raiz);
    }

    private String imprimePos(NoArvoreBinaria no) {
        String s = new String("");
        s = s + "< ";
        if (no != null) {
            s = s + imprimePos(no.getEsq());
            s = s + imprimePos(no.getDir());
            s = s + no.getInfo();
        }
        s = s + ">";
        return s;
    }

    public boolean pertence(int info) {
        return pertence(raiz, info);
    }

    private boolean pertence(NoArvoreBinaria no, int info) {
        if (no == null) {
            return false;
        } else {
            return (no.getInfo() == info) || pertence(no.getEsq(), info) || pertence(no.getDir(), info);
        }
    }

    private int pares(NoArvoreBinaria no) {
        int par = 0, pares = 0;
        if (no == null) {
            return pares;
        } else {
            par = no.getInfo() % 2;
            if (par == 0) {
                pares++;
            }
        }
        pares = pares + pares(no.getEsq());
        pares = pares + pares(no.getDir());
        return pares;
    }

    public int pares() {
        return pares(this.raiz);
    }

    private int folhas(NoArvoreBinaria no) {
        int cont = 0;
        if (no == null) {
            return cont;
        }
        if (no.getDir() == null && no.getEsq() == null) {
            return cont + 1;
        }
        cont = cont + folhas(no.getEsq());
        cont = cont + folhas(no.getDir());
        return cont;
    }

    public int folhas() {
        return folhas(this.raiz);
    }

    private boolean igual(NoArvoreBinaria a, NoArvoreBinaria b) {
        if (a == null && b == null) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        if (igual(a.getDir(), b.getDir()) && igual(a.getEsq(), b.getEsq()) && a.getInfo() == b.getInfo()) {
            return true;
        }
        return false;
    }

    public boolean igual(ArvoreBinaria a) {
        return igual(a.raiz, this.raiz);
    }

    private NoArvoreBinaria copia(ArvoreBinaria a, NoArvoreBinaria no) {
        if (no == null) {
            return null;
        }
        NoArvoreBinaria novoNo;
        novoNo = a.insere(no.getInfo(), copia(a, no.getEsq()), copia(a, no.getDir()));
        return novoNo;
    }

    public ArvoreBinaria copia() {
        ArvoreBinaria novo = new ArvoreBinaria();
        novo.raiz = copia(novo, this.raiz);
        return novo;
    }

    private int maiorSomaCaminhoAux(NoArvoreBinaria no) {
        int max = 0;
        NoArvoreBinaria p = no;
        if (p.getDir() != null) {
            int x = maiorSomaCaminhoAux(p.getDir());
            if (x > max) {
                max = x;
            }
        }
        if (p.getEsq() != null) {
            int x = maiorSomaCaminhoAux(p.getEsq());
            if (x > max) {
                max = x;
            }
        }
        max += no.getInfo();
        return max;
    }

    public int maiorSomaCaminho() {
        return maiorSomaCaminhoAux(this.raiz);
    }
}
