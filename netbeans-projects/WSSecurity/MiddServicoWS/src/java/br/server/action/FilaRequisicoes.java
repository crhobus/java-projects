package br.server.action;
/*
 * Document   : FilaRequisicoes.java
 * Created on : 24/09/2013, 21:40:12
 * Author     : Caio
 */

public class FilaRequisicoes {

    private int tam;
    private int ini;
    private int fim;
    private int qtd;
    private long[] armSeqRequisicoes;

    public FilaRequisicoes() {
        this.tam = 100;
        this.ini = 0;
        this.fim = 0;
        this.qtd = 0;
        this.armSeqRequisicoes = new long[tam];
    }

    public void inserir(long nrSeqRequiscao) {
        armSeqRequisicoes[fim] = nrSeqRequiscao;
        fim = (fim + 1) % tam;
        qtd++;
    }

    public long retirar() {
        long l = armSeqRequisicoes[ini];
        ini = (ini + 1) % tam;
        qtd--;
        return l;
    }

    public boolean isVazio() {
        return qtd == 0;
    }

    public boolean isCheio() {
        return qtd == tam;
    }
}