package TabelaHash;

public class Aluno {

    private String nome;
    private int matricula;
    private float mediaGeral;
    private Aluno prox;

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public float getMediaGeral() {
        return mediaGeral;
    }

    public void setMediaGeral(float mediaGeral) {
        this.mediaGeral = mediaGeral;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Aluno getProx() {
        return prox;
    }

    public void setProx(Aluno prox) {
        this.prox = prox;
    }
}
