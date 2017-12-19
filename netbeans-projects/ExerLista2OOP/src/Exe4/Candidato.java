package Exe4;

public class Candidato {

    private int numVotos;
    private String nome;

    public Candidato(int numVotos, String nome) {
        this.numVotos = numVotos;
        this.nome = nome;
    }

    public int getNumVotos() {
        return numVotos;
    }

    public void setNumVotos(int numVotos) {
        this.numVotos = numVotos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
