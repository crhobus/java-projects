package Fila;

public class FilaLista implements Fila {

    private NoLista ini;
    private NoLista fim;

    public FilaLista() {
        this.ini = null;
        this.fim = null;
    }

    @Override
    public String toString() {
        NoLista n = ini;
        String s = " ";
        while (n != null) {
            s += n.getInfo() + " ";
            n = n.getProx();
        }
        return s;
    }

    public void insere(int v) throws Exception {
        NoLista novo = new NoLista();
        novo.setInfo(v);
        novo.setProx(null);
        if (fim != null) {
            fim.setProx(novo);
        } else {
            ini = novo;

        }
        fim = novo;
    }

    public int retira() throws Exception {
        int v;
        if (vazia()) {
            throw new Exception("ERRO: fila vazia!");
        } else {
            v = ini.getInfo();
            ini = ini.getProx();
            if (ini == null) {
                fim = null;
            }
            return v;
        }
    }

    public boolean vazia() {
        if (ini == null & fim == null) {
            return true;
        } else {
            return false;
        }
    }

    public void libera() {
        ini = null;
        fim = null;
    }
}
