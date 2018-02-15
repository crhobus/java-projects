package br.client.middleware.action.dados;
/*
 * Document   : RequisicaoRegistro.java
 * Created on : 28/09/2013, 14:32:02
 * Author     : Caio
 */

public class RequisicaoRegistro extends Requisicao {

    private Registro registro;

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }
}