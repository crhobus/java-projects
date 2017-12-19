package Exe6;

import java.util.ArrayList;
import java.util.List;

public class Cadastro {

    private List<Competidor> competidores = new ArrayList<Competidor>();

    public void addCompetidor(Competidor competidor) {
        competidores.add(competidor);
    }

    public boolean isVazio() {
        return competidores.isEmpty();
    }

    public String getResultados() {
        int masculino = 0, feminino = 0, infantilA = 0, infantilB = 0,
                juvenilA = 0, juvenilB = 0, master = 0, adultoA = 0, adultoB = 0;
        for (Competidor competidor : competidores) {
            if (competidor.getSexo().equalsIgnoreCase("M")) {
                masculino++;
            } else {
                feminino++;
            }
            if (competidor.getIdade() >= 7 && competidor.getIdade() <= 9) {
                infantilA++;
            } else {
                if (competidor.getIdade() >= 10 && competidor.getIdade() <= 11) {
                    infantilB++;
                } else {
                    if (competidor.getIdade() >= 12 && competidor.getIdade() <= 14) {
                        juvenilA++;
                    } else {
                        if (competidor.getIdade() >= 15 && competidor.getIdade() <= 17) {
                            juvenilB++;
                        } else {
                            if (competidor.getIdade() >= 18 && competidor.getIdade() <= 22) {
                                master++;
                            } else {
                                if (competidor.getIdade() >= 23 && competidor.getIdade() <= 27) {
                                    adultoA++;
                                } else {
                                    adultoB++;
                                }
                            }
                        }
                    }
                }
            }
        }
        String str = "Quantidade 7 á 9 – Infantil A: " + infantilA
                + "\nQuantidade 10 á 11 – Infantil B: " + infantilB
                + "\nQuantidade 12 á 14 – Juvenil A: " + juvenilA
                + "\nQuantidade 15 á 17 – Juvenil B: " + juvenilB
                + "\nQuantidade 18 á 22 – Master: " + master
                + "\nQuantidade 23 á 27 – Adulto A: " + adultoA
                + "\nQuantidade 28 á 30 – Adulto B: " + adultoB
                + "\nEstão participando " + masculino + " homens"
                + "\nEstão participando " + feminino + " mulheres";
        if (masculino == feminino) {
            str += "\nHá o mesmo tanto de homens e mulheres";
        } else {
            if (feminino > masculino) {
                str += "\nHá mais mulheres do que homens";
            } else {
                str += "\nHá mais homens do que mulheres";
            }
        }
        return str;
    }
}
