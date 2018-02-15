package br.client.middleware.action.dados;
/*
 * Document   : RequisicaoLista.java
 * Created on : 28/09/2013, 14:30:25
 * Author     : Caio
 */

public class RequisicaoLista extends Requisicao {

    private Lista lista;

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }
}