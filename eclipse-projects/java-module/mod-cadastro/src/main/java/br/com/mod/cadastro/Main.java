package br.com.mod.cadastro;

import java.time.LocalDate;
import java.util.Optional;

import br.com.mod.curso.aluno.Aluno;

public class Main {

    public static void main(String[] args) {

        Aluno aluno1 = new Aluno(1, "Teste 1", "123.456.789-00", LocalDate.of(1990, 1, 1));
        Aluno aluno2 = new Aluno(1, "Teste 2", "123.456.789-00", LocalDate.of(1991, 1, 1));
        Aluno aluno3 = new Aluno(1, "Teste 3", "123.456.789-00", LocalDate.of(1992, 1, 1));
        Aluno aluno4 = new Aluno(1, "Teste 4", "123.456.789-00", LocalDate.of(1993, 1, 1));
        Aluno aluno5 = new Aluno(1, "Teste 5", "123.456.789-00", LocalDate.of(1994, 1, 1));

        System.out.println("Add");
        Matricular matricular = new Matricular();
        long matricula1 = matricular.add(aluno1);
        long matricula2 = matricular.add(aluno2);
        long matricula3 = matricular.add(aluno3);
        long matricula4 = matricular.add(aluno4);
        long matricula5 = matricular.add(aluno5);

        System.out.println("Update");
        aluno2.setNome("Teste 2 XY");
        matricular.update(matricula2, aluno2);

        Optional<Aluno> aluno2Opt = matricular.getMatricula(matricula3);
        if (aluno2Opt.isPresent()) {
            System.out.println("Get: " + aluno2Opt.get().toString());
        }

        System.out.println("Delete");
        matricular.delete(matricula3);

        System.out.println("Listar");
        matricular.getMatriculas().forEach(matricula -> {
            System.out.println(matricula);
        });
    }

}
