package br.server.action;
/*
 * Document   : Arquivo.java
 * Created on : 28/09/2013, 18:25:02
 * Author     : Caio
 */

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Arquivo {

    private DataHandler binaryData;
    private String cdRegra;
    private String cdPermissao;
    private String nmUsuario;
    private String nmArquivo;
    private String dsDirArquivoServidor;

    @XmlMimeType("application/octet-stream")
    public DataHandler getBinaryData() {
        return binaryData;
    }

    public void setBinaryData(DataHandler binaryData) {
        this.binaryData = binaryData;
    }

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

    public String getNmArquivo() {
        return nmArquivo;
    }

    public void setNmArquivo(String nmArquivo) {
        this.nmArquivo = nmArquivo;
    }

    public String getDsDirArquivoServidor() {
        return dsDirArquivoServidor;
    }

    public void setDsDirArquivoServidor(String dsDirArquivoServidor) {
        this.dsDirArquivoServidor = dsDirArquivoServidor;
    }
}