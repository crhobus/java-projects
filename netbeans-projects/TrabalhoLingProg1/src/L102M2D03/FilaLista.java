package L102M2D03;

public class FilaLista {

    private NoLista ini;
    private NoLista fim;
    private int qtdade = 0;

    public FilaLista() {
        this.ini = null;
        this.fim = null;
    }

    public void insere(String info) {
        NoLista novo = new NoLista();
        novo.setInfo(info);
        novo.setProx(null);
        if (fim != null) {
            fim.setProx(novo);
        } else {
            ini = novo;
        }
        fim = novo;
        qtdade++;
    }

    public boolean vazio() {
        return ini == null && fim == null;
    }

    public void libera() {
        ini = null;
        fim = null;
    }

    public String xsPrimeiros(int v) {
        NoLista n = ini;
        String s = "LISTAGEM DOS " + v + " PRIMEIROS DA FILA\n";
        while (n != null && v != 0) {
            s += n.getInfo() + " ";
            n = n.getProx();
            v--;
        }
        return s;
    }

    public String xsUltimos(int v) {
        NoLista n = ini;
        String s = "LISTAGEM DOS " + v + " ULTIMOS DA FILA\n";
        int t = qtdade - v;
        int i = 0;
        while (n != null) {
            if (i >= t) {
                s += n.getInfo() + " ";
            }
            i++;
            n = n.getProx();
        }
        return s;
    }
}
