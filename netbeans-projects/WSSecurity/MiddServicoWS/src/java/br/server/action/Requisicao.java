package br.server.action;
/*
 * Document   : Requisicao.java
 * Created on : 24/09/2013, 21:56:15
 * Author     : Caio
 */

public class Requisicao {

    private long nrSeqSistema;
    private FilaRequisicoes filaRequisicoes;

    public Requisicao(long nrSeqSistema) {
        this.nrSeqSistema = nrSeqSistema;
        this.filaRequisicoes = new FilaRequisicoes();
    }

    public void inserir(long nrSeqRequiscao) throws Exception {
        if (filaRequisicoes.isCheio()) {
            throw new Exception("Limite de requisições excedido");
        }
        filaRequisicoes.inserir(nrSeqRequiscao);
    }

    public long retirar() throws Exception {
        if (filaRequisicoes.isVazio()) {
            throw new Exception("Não há requisições para este sistema");
        }
        return filaRequisicoes.retirar();
    }

    public long getNrSeqSistema() {
        return nrSeqSistema;
    }
}