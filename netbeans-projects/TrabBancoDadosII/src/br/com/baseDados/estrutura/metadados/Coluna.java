package br.com.baseDados.estrutura.metadados;

public class Coluna {

    private int cdColuna, tipo, tamanho, precisao;
    private String nmColuna;
    private boolean notNull;

    public int getCdColuna() {
        return cdColuna;
    }

    public void setCdColuna(int cdColuna) {
        this.cdColuna = cdColuna;
    }

    public String getNmColuna() {
        return nmColuna;
    }

    public void setNmColuna(String nmColuna) {
        this.nmColuna = nmColuna;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public int getPrecisao() {
        return precisao;
    }

    public void setPrecisao(int precisao) {
        this.precisao = precisao;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
