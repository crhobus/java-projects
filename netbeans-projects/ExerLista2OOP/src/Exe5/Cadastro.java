package Exe5;

import java.util.ArrayList;
import java.util.List;

public class Cadastro {

    private List<Aluno> alunos = new ArrayList<Aluno>();

    public void addAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public boolean isVazio() {
        return alunos.isEmpty();
    }

    public String getResultados() {
        int masculino = 0, feminino = 0, parabens = 0, otimo = 0, bom = 0, satisfatorio = 0, recuperacao = 0, exame = 0;
        double maiorMedia = -1;
        for (Aluno aluno : alunos) {
            if (aluno.getMedia() > maiorMedia) {
                maiorMedia = aluno.getMedia();
            }
            if (aluno.getSexo().equalsIgnoreCase("M")) {
                masculino++;
            } else {
                feminino++;
            }
            if (aluno.getMedia() == 10) {
                parabens++;
            } else {
                if (aluno.getMedia() >= 9 && aluno.getMedia() <= 9.9) {
                    otimo++;
                } else {
                    if (aluno.getMedia() >= 8 && aluno.getMedia() <= 8.9) {
                        bom++;
                    } else {
                        if (aluno.getMedia() >= 7 && aluno.getMedia() <= 7.9) {
                            satisfatorio++;
                        } else {
                            if (aluno.getMedia() >= 5 && aluno.getMedia() <= 6.9) {
                                recuperacao++;
                            } else {
                                exame++;
                            }
                        }
                    }
                }
            }
        }
        return "Maior média de todos os alunos cadastrados: " + maiorMedia
                + "\nQuantidade de meninos: " + masculino
                + "\nQuantidade de meninas: " + feminino
                + "\n" + ((parabens * 100) / alunos.size()) + "% de alunos que tiraram 10-----------------------------------Parabéns"
                + "\n" + ((otimo * 100) / alunos.size()) + "% de alunos que tiraram média entre 9.0 e 9.9----------------Ótimo"
                + "\n" + ((bom * 100) / alunos.size()) + "% de alunos que tiraram média entre 8.0 e 8.9----------------Bom"
                + "\n" + ((satisfatorio * 100) / alunos.size()) + "% de alunos que tiraram média entre 7.0 e 7.9----------------Satisfatório"
                + "\n" + ((recuperacao * 100) / alunos.size()) + "% de alunos que tiraram média entre 5.0 e 6.9----------------Recuperação"
                + "\n" + ((exame * 100) / alunos.size()) + "% de alunos que tiraram média abaixo de 5--------------------Exame";
    }
}
