package br.com.baseDados.estrutura.dados;

public class IndiceDados {

    private int cdTabela, registroAtivo;
    private long posicaoDado;

    public int getCdTabela() {
        return cdTabela;
    }

    public void setCdTabela(int cdTabela) {
        this.cdTabela = cdTabela;
    }

    public long getPosicaoDado() {
        return posicaoDado;
    }

    public void setPosicaoDado(long posicaoDado) {
        this.posicaoDado = posicaoDado;
    }

    public int getRegistroAtivo() {
        return registroAtivo;
    }

    public void setRegistroAtivo(int registroAtivo) {
        this.registroAtivo = registroAtivo;
    }
}