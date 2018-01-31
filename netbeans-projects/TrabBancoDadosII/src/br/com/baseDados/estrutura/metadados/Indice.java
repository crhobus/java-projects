package br.com.baseDados.estrutura.metadados;

public class Indice {

    private int cdIndice, nrColunas;
    private String nmIndice;
    private int[] cdColunaIndiceTabela;

    public int[] getCdColunaIndiceTabela() {
        return cdColunaIndiceTabela;
    }

    public void setCdColunaIndiceTabela(int[] cdColunaIndiceTabela) {
        this.cdColunaIndiceTabela = cdColunaIndiceTabela;
    }

    public int getCdIndice() {
        return cdIndice;
    }

    public void setCdIndice(int cdIndice) {
        this.cdIndice = cdIndice;
    }

    public String getNmIndice() {
        return nmIndice;
    }

    public void setNmIndice(String nmIndice) {
        this.nmIndice = nmIndice;
    }

    public int getNrColunas() {
        return nrColunas;
    }

    public void setNrColunas(int nrColunas) {
        this.nrColunas = nrColunas;
    }
}
