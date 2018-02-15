package br.client.middleware.action.dados;
/*
 * Document   : Requisicao.java
 * Created on : 27/09/2013, 22:10:42
 * Author     : Caio
 */

public abstract class Requisicao {

    private String cdRegra;
    private String cdPermissao;
    private String nmUsuario;

    public String getCdRegra() {
        return cdRegra;
    }

    public void setCdRegra(String cdRegra) {
        this.cdRegra = cdRegra;
    }

    public String getCdPermissao() {
        return cdPermissao;
    }

    public void setCdPermissao(String cdPermissao) {
        this.cdPermissao = cdPermissao;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }
}