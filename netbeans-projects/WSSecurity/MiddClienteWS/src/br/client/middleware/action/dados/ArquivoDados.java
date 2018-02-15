package br.client.middleware.action.dados;
/*
 * Document   : ArquivoDados.java
 * Created on : 29/09/2013, 15:32:24
 * Author     : Caio
 */

import java.io.File;

public class ArquivoDados extends Requisicao {

    private File arquivo;

    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }
}