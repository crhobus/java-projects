package br.com.mod.cadastro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import br.com.mod.curso.aluno.Aluno;
import br.com.mod.curso.matricula.Matricula;

public class Matricular {

    private final static List<Matricula> matriculas = new ArrayList<>();
    private final AtomicLong geradorNrMatricula = new AtomicLong();

    public long add(Aluno aluno)  {
        Matricula matricula = new Matricula(geradorNrMatricula.incrementAndGet(), aluno);

        matriculas.add(matricula);

        return matricula.getNrMatricula();
    }

    public void update(long nrMatricula, Aluno aluno) {
        matriculas.stream().filter(f -> f.getNrMatricula() == nrMatricula).forEach(m -> {
            m.setAluno(aluno);
        });
    }

    public Optional<Aluno> getMatricula(long nrMatricula) {
        return matriculas.stream().filter(f -> f.getNrMatricula() == nrMatricula).findFirst().map(Matricula::getAluno);
    }

    public boolean delete(long nrMatricula) {
        return matriculas.removeIf(f -> f.getNrMatricula() == nrMatricula);
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }
}
