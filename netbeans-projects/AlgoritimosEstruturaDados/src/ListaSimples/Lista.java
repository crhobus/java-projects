package ListaSimples;

public class Lista {

    private NoLista prim;

    public Lista() {
        prim = null;
    }

    public void insere(int v) {
        NoLista novo = new NoLista();
        novo.setInfo(v);
        novo.setProx(prim);
        this.prim = novo;
    }

    public void insereIndice(int v, int i) {
        NoLista novo = new NoLista();
        novo.setInfo(v);
        if (prim == null) {
            novo.setProx(null);
            prim = novo;
            return;
        }
        NoLista p = prim;
        NoLista ant = null;
        int contador = 0;
        while (p != null && contador < i) {
            ant = p;
            p = p.getProx();
            contador++;
        }
        if (ant == null) {
            novo.setProx(p);
            prim = novo;
            return;
        }
        ant.setProx(novo);
        novo.setProx(p);
    }

    public void imprime() {
        NoLista n = prim;
        while (n != null) {
            System.out.print(n.getInfo() + " ");
            n = n.getProx();
        }
    }

    public void imprimeInv() {
        listaImprirInv(prim);
    }

    public void listaImprirInv(NoLista l) {
        if (l != null) {
            listaImprirInv(l.getProx());
            System.out.print(l.getInfo() + " ");
        }
    }

    @Override
    public String toString() {
        NoLista p = prim;
        String imprime = "";
        while (p != null) {
            imprime += p.getInfo() + " ";
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

    public NoLista busca(int v) {
        NoLista p = prim;
        while (p != null) {
            if (p.getInfo() == v) {
                return p;
            }
            p = p.getProx();
        }
        return null;
    }

    public int comprimento() {
        int i = 0;
        for (NoLista p = prim; p != null; p = p.getProx()) {
            i++;
        }
        return i;
    }

    public NoLista ultimo() {
        NoLista p = prim;
        while (p != null) {
            if (p.getProx() == null) {
                return p;
            }
            p = p.getProx();
        }
        return null;
    }

    public void retira(int v) {
        NoLista n = null;
        NoLista p = prim;
        while ((p != null) & (p.getInfo() != v)) {
            n = p;
            p = p.getProx();
        }
        if (p == null) {
            return;
        }
        if (n == null) {
            this.prim = p.getProx();
        } else {
            n.setProx(p.getProx());
        }
    }

    public void libera() {
        prim = null;
    }

    public void insereOrdenado(int v) {
        NoLista n = null;
        NoLista novo;
        NoLista p = prim;
        while ((p != null) & (p.getInfo() < v)) {
            n = p;
            p = p.getProx();
        }
        novo = new NoLista();
        novo.setInfo(v);
        if (n == null) {
            novo.setProx(prim);
            prim = novo;
        } else {
            novo.setProx(n.getProx());
            n.setProx(novo);
        }
    }

    public boolean igual(Lista i) {
        NoLista p1 = this.prim;
        NoLista p2 = i.prim;
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

    public void moveNo(Lista l2) {
        if (l2.prim == null) {
            return;
        }
        NoLista p = l2.prim.getProx();
        l2.prim.setProx(this.prim);
        this.prim = l2.prim;
        l2.prim = p;
    }

    /*public DuasListas separaAlt() { 
    DuasListas dl = new DuasListas(); 
    while(this.prim != null) { 
    dl.getL1().moveNo(this);
    dl.getL2().moveNo(this); 
    }
    }*/
    public void imprimeRecursivo() {
        imprimeRecursivoAux(prim);
    }

    public void imprimeRecursivoAux(NoLista i) {
        if (i != null) {
            System.out.print(i.getInfo() + " ");
            imprimeRecursivoAux(i.getProx());
        }
    }

    public void retiraRecursivo(int v) {
        prim = retiraRecursivoAux(prim, v);
    }

    public NoLista retiraRecursivoAux(NoLista i, int v) {
        if (i != null) {
            if (i.getInfo() == v) {
                i = i.getProx();
            } else {
                i.setProx(retiraRecursivoAux(i.getProx(), v));
            }
        }
        return i;
    }

    public boolean igualRecursivo(Lista i) {
        return igualRecursivoAux(this.prim, i.prim);
    }

    public boolean igualRecursivoAux(NoLista i1, NoLista i2) {
        if (i1 == null & i2 == null) {
            return true;
        } else {
            if (i1 == null || i2 == null) {
                return false;
            } else {
                return ((i1.getInfo() == i2.getInfo()) & igualRecursivoAux(i1.getProx(), i2.getProx()));
            }
        }
    }
}
