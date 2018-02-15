package br.server.control.action.session;

import java.io.Serializable;

/*
 * Document   : Permissao.java
 * Created on : 08/06/2013, 16:57:31
 * Author     : Caio
 */
public class Permissao implements Serializable {

    private String dsPermissao;

    public String getDsPermissao() {
        return dsPermissao;
    }

    public void setDsPermissao(String dsPermissao) {
        this.dsPermissao = dsPermissao;
    }
}