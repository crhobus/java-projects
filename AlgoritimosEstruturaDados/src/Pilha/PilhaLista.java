package Pilha;

public class PilhaLista implements Pilha {

    private NoLista topo;

    public PilhaLista() {
        this.topo = null;
    }

    @Override
    public String toString() {
        NoLista n = topo;
        String s = "";
        while (n != null) {
            s += n.getInfo() + " ";
            n = n.getProx();
        }
        return s;
    }

    public void push(float v) throws Exception {
        NoLista novo = new NoLista();
        novo.setInfo(v);
        novo.setProx(topo);
        this.topo = novo;
    }

    public float pop() throws Exception {
        if (topo == null) {
            throw new Exception("Erro: Lista Vazia");
        } else {
            float v = this.topo.getInfo();
            this.topo = this.topo.getProx();
            return v;
        }
    }

    public float top() throws Exception {
        if (topo == null) {
            throw new Exception("Erro: Lista Vazia");
        } else {
            return topo.getInfo();
        }
    }

    public boolean vazia() {
        if (topo == null) {
            return true;
        } else {
            return false;
        }
    }

    public void libera() {
        topo = null;
    }

    public Pilha copia() throws Exception {
        PilhaLista f3 = new PilhaLista();
        NoLista lista = f3.topo;
        NoLista p = topo;
        f3.topo = this.topo;
        while (lista != null) {
            f3.push(lista.getInfo());
            lista = p.getProx();
        }
        return f3;
    }

    public PilhaLista copiaInvertido() throws Exception {
        PilhaLista novaPilha = new PilhaLista();
        NoLista n = topo;
        while (n != null) {
            novaPilha.push(n.getInfo());
            n = n.getProx();
        }
        return novaPilha;
    }

    public PilhaLista filtraMenores(float x) throws Exception {
        NoLista p = topo;
        PilhaLista l3 = new PilhaLista();
        if (vazia()) {
            return null;
        }
        while (p != null) {
            if (p.getInfo() > x) {
                l3.push(p.getInfo());
            }
            p = p.getProx();
        }
        l3.copiaInvertido();
        return l3;
    }
}
