package ListaDuplaCircular;

import ListaDupla.NoListaDupla;

public class ListaDuplaCircular {

    private NoListaDupla prim;

    public ListaDuplaCircular() {
        prim = null;
    }

    public void insere(int v) {
        NoListaDupla novo = new NoListaDupla();
        novo.setInfo(v);
        novo.setProx(prim);
        if (prim != null) {
            novo.setAnt(prim.getAnt());
            prim.setAnt(novo);
        }
        this.prim = novo;
    }

    public void insereFim(int v) {
        NoListaDupla p = ultimo();
        NoListaDupla novo = new NoListaDupla();
        novo.setInfo(v);
        if (p == null) {
            prim = novo;
        } else {
            p.setProx(novo);
            novo.setAnt(p);
        }
    }

    public void retira(int v) {
        NoListaDupla p = busca(v);
        if (p == null) {
            return;
        }
        if (prim == p) {
            this.prim = p.getProx();
        } else {
            p.getAnt().setProx(p.getProx());
        }
        if (p.getProx() != null) {
            p.getProx().setAnt(p.getAnt());
        }
    }

    public String toString() {
        NoListaDupla n = prim;
        String imprime = "";
        while (n != null) {
            imprime += n.getInfo() + " ";
        }
        return imprime;
    }

    public boolean vazia() {
        NoListaDupla p = prim;
        if (p == null) {
            return true;
        } else {
            return false;
        }
    }

    public NoListaDupla busca(int v) {
        NoListaDupla p = prim;
        while (p != null) {
            if (p.getInfo() == v) {
                return p;
            }
            p = p.getProx();
        }
        return null;
    }

    public NoListaDupla buscaIndice(int i) {
        NoListaDupla p = prim;
        int contador = 0;
        while (contador != i && p != null) {
            p = p.getProx();
            contador++;
        }
        return p;
    }

    public void libera() {
        prim = null;
    }

    public boolean igual(ListaDuplaCircular i) {
        NoListaDupla p1 = this.prim;
        NoListaDupla p2 = i.prim;
        while ((p1 != null) & (p2 != null)) {
            if (p1.getInfo() != p2.getInfo()) {
                return false;
            }
            p1 = p1.getProx();
            p2 = p2.getProx();
        }
        if (p1 == p2) {
            return true;
        } else {
            return false;
        }
    }

    public ListaDuplaCircular merge(ListaDuplaCircular l2) {
        NoListaDupla p1 = this.prim;
        NoListaDupla p2 = l2.prim;
        ListaDuplaCircular l3 = new ListaDuplaCircular();

        while (p1 != null && p2 != null) {
            l3.insereFim(p1.getInfo());
            l3.insereFim(p2.getInfo());
            p1 = p1.getProx();
            p2 = p2.getProx();
        }
        while (p1 != null) {
            l3.insereFim(p1.getInfo());
            p1 = p1.getProx();
        }
        while (p2 != null) {
            l3.insereFim(p2.getInfo());
            p2 = p2.getProx();
        }
        return l3;
    }

    public ListaDuplaCircular separa(int n) {
        ListaDuplaCircular l2 = new ListaDuplaCircular();
        NoListaDupla p = busca(n);
        while (p != null) {
            l2.prim = p.getProx();
            p.setProx(null);
            if (l2.prim != null) {
                l2.prim.setAnt(null);
                return l2;
            }
            p = p.getProx();
        }
        return l2;
    }

    public void imprime() {
        NoListaDupla p = prim;
        while (p != null) {
            System.out.print(p.getInfo() + " ");
            p = p.getProx();
        }
    }

    public NoListaDupla ultimo() {
        NoListaDupla p = prim;
        if (prim == null) {
            return null;
        } else {
            while (p.getProx() != null) {
                p = p.getProx();
            }
        }
        return p;
    }
}
