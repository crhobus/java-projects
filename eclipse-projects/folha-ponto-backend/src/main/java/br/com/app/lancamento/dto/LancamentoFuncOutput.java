package br.com.app.lancamento.dto;

import java.io.Serializable;

public class LancamentoFuncOutput implements Serializable {

    private static final long serialVersionUID = 1L;

    private String data;

    private String descricao;

    private String localizacao;

    private TipoEnum tipo;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public TipoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnum tipo) {
        this.tipo = tipo;
    }

}
