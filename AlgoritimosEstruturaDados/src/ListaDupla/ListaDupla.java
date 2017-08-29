package ListaDupla;

public class ListaDupla {

    private NoListaDupla prim;

    public ListaDupla() {
        prim = null;
    }

    public void insere(int v) {
        NoListaDupla novo = new NoListaDupla();
        novo.setInfo(v);
        novo.setProx(prim);
        novo.setAnt(null);
        if (prim != null) {
            prim.setAnt(novo);
        }
        this.prim = novo;
    }

    public void imprime() {
        NoListaDupla n = prim;
        while (n != null) {
            System.out.print(n.getInfo() + " ");
            n = n.getProx();
        }
    }

    @Override
    public String toString() {
        NoListaDupla n = prim;
        String imprime = "";
        while (n != null) {
            imprime += n.getInfo() + " ";
        }
        return imprime;
    }

    public boolean vazia() {
        if (prim == null) {
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

    public void libera() {
        prim = null;
    }

    public boolean igual(ListaDupla i) {
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

    public void insereFim(int v) {
        NoListaDupla p = ultimo();
        NoListaDupla novo = new NoListaDupla();
        novo.setInfo(v);
        novo.setProx(null);
        novo.setAnt(null);
        if (p == null) {
            prim = novo;
        } else {
            p.setProx(novo);
            novo.setAnt(p);
        }
    }

    public ListaDupla merge(ListaDupla l2) {
        NoListaDupla p1 = this.prim;
        NoListaDupla p2 = l2.prim;
        ListaDupla l3 = new ListaDupla();

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

    public ListaDupla separa(int n) {
        ListaDupla l2 = new ListaDupla();
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
}
