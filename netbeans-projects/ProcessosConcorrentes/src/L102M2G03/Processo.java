package L102M2G03;

import java.util.Random;

public class Processo extends Thread {

    protected Armazem armazemColocar;
    protected Armazem armazemRetirar;
    protected Random random = new Random();
    private int identificador;
    protected boolean flagTerminar;
    protected int produto;
    protected int tempo;
    protected String status;
    protected int total;
    protected PanelPrincipal panelPrincipal;

    public Processo(int id, Armazem armazemAnterior, Armazem armazemProximo) {
        identificador = id;
        this.armazemRetirar = armazemAnterior;
        this.armazemColocar = armazemProximo;
        flagTerminar = false;
        start();
    }

    public void terminar() {
        setStatus("FINALIZADO");
        flagTerminar = true;
    }

    public void setPanel(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIdentificador() {
        return identificador;
    }

    public int getProduto() {
        return produto;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
