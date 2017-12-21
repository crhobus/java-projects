package Exe4Deposito;

import java.util.*;

public class Deposito {

    private int qtdadeAtual = 0;
    private int qtdadeCritica = 0;
    private int qtdadeMax = 0;
    private ArrayList<Listener> listener = new ArrayList<Listener>();

    public Deposito(int qtdadeCritica, int qtdadeMax) {
        this.qtdadeCritica = qtdadeCritica;
        this.qtdadeMax = qtdadeMax;
    }

    public void add(int qtdade) throws Exception {
        if (qtdadeAtual + qtdade > qtdadeMax) {
            int x = qtdadeMax - qtdadeAtual;
            qtdadeAtual = qtdadeMax;
            notifFoiAdd(x);
            throw new Exception("Tentativa de armazenar mais que o permitido");
        } else {
            qtdadeAtual += qtdade;
        }
        notifFoiAdd(qtdade);
        notifMudouQtdade();
    }

    public int retirar(int qtdade) {
        if (qtdadeAtual - qtdade < 0) {
            qtdade = qtdadeAtual;
            qtdadeAtual = 0;
        } else {
            qtdadeAtual -= qtdade;
        }
        if (qtdadeAtual <= qtdadeCritica) {
            notifChegouCritica();
        }
        notificaFoiretirado(qtdade);
        notifMudouQtdade();
        return qtdade;
    }

    public void addListener(Listener i) {
        listener.add(i);
    }

    public void retiraListener(Listener i) {
        listener.remove(i);
    }

    private void notifMudouQtdade() {
        for (Listener ld : listener) {
            ld.qtdadeAtual(qtdadeAtual);
        }
    }

    private void notificaFoiretirado(int qtdade) {
        for (Listener ld : listener) {
            ld.qtdadeRetirada(qtdade);
        }
    }

    private void notifChegouCritica() {
        for (Listener ld : listener) {
            ld.qtdadeCritica();
        }
    }

    private void notifFoiAdd(int x) {
        for (Listener ld : listener) {
            ld.qtdadeAdd(x);
        }
    }
}
