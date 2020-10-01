package br.com.mod.curso.matricula;

import br.com.mod.curso.aluno.Aluno;

public class Matricula {

    private long nrMatricula;
    private Aluno aluno;

    public Matricula() {}

    public Matricula(long nrMatricula, Aluno aluno) {
        this.nrMatricula = nrMatricula;
        this.aluno = aluno;
    }

    public long getNrMatricula() {
        return nrMatricula;
    }

    public void setNrMatricula(long nrMatricula) {
        this.nrMatricula = nrMatricula;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        return "Matricula [nrMatricula=" + nrMatricula + ", aluno=" + aluno.toString() + "]";
    }

}
