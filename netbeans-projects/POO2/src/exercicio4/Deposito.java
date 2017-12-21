package exercicio4;

import java.util.ArrayList;

public class Deposito {

    private int qtdadeAtual;
    private int qtdadeCritica;
    private int qtdadeMax;
    private ArrayList<ListenerDeposito> arrayListener;
    private static Deposito deposito;

    public Deposito(int qtdadeMax, int qtdadeCritica) {
        this.arrayListener = new ArrayList<ListenerDeposito>();
        this.qtdadeMax = qtdadeMax;
        this.qtdadeCritica = qtdadeCritica;
        this.qtdadeAtual = 0;
    }

    public void add(int qtd) throws Exception {
        if (qtdadeAtual + qtd <= qtdadeMax) {
            qtdadeAtual = qtdadeAtual + qtd;
            notifFoiAdd(qtd);
        } else {
            int i = qtdadeMax - qtdadeAtual;
            qtdadeAtual = qtdadeMax;
            notifFoiAdd(i);
            throw new Exception("Tentativa de armazenar mais que o permitido");
        }
        notifMudouQtdade(qtdadeAtual);
    }

    public void retirar(int qtd) {
        if (qtd < qtdadeAtual) {
            qtdadeAtual = qtdadeAtual - qtd;
        } else {
            qtd = qtdadeAtual;
            qtdadeAtual = 0;
        }
        notifMudouQtdade(qtdadeAtual);
        notifFoiRemovido(qtd);
        if (qtdadeAtual <= qtdadeCritica) {
            notifQtdCritica();
        }
    }

    public void addListener(ListenerDeposito l) {
        arrayListener.add(l);
    }

    private void notifQtdCritica() {
        for (ListenerDeposito l : arrayListener) {
            l.chegouCritico();
        }
    }

    private void notifMudouQtdade(int qtd) {
        for (ListenerDeposito l : arrayListener) {
            l.foiModificadoqtdAtual(qtd);
        }
    }

    private void notifFoiAdd(int i) {
        for (ListenerDeposito l : arrayListener) {
            l.foiAdd(i);
        }
    }

    private void notifFoiRemovido(int i) {
        for (ListenerDeposito l : arrayListener) {
            l.foiRet(i);
        }
    }

    public static Deposito getInstancia(int qtdadeMax, int qtdadeCritica) {
        if (deposito == null) {
            deposito = new Deposito(qtdadeMax, qtdadeCritica);
        }
        return deposito;
    }
}
