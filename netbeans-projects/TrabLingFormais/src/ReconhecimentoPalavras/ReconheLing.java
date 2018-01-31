package ReconhecimentoPalavras;

public class ReconheLing {

    private int linha;
    private String resultado, palavra, reconhecimento;

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getReconhecimento() {
        return reconhecimento;
    }

    public void setReconhecimento(String reconhecimento) {
        this.reconhecimento = reconhecimento;
    }
}
