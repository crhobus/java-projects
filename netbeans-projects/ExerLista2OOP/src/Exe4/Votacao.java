package Exe4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Votacao {

    private List<Candidato> candidatos = new ArrayList<Candidato>(5);
    private int qtdade;

    public Votacao() {
        candidatos.add(new Candidato(0, "José Serra"));
        candidatos.add(new Candidato(0, "Dilma"));
        candidatos.add(new Candidato(0, "Lula"));
        candidatos.add(new Candidato(0, "Geraldo Alkmin"));
        candidatos.add(new Candidato(0, "Nulo"));
    }

    public void votar(int opcao) {
        qtdade++;
        candidatos.get(opcao - 1).setNumVotos(candidatos.get(opcao - 1).getNumVotos() + 1);
    }

    public String getCandidatos() {
        return "Candidatos:\n1 - José Serra\n2 - Dilma\n3 - Lula\n4 - Geraldo Alkmin\n5 - Nulo";
    }

    public String getResultadoVotacao() {
        if (qtdade != 0) {
            Collections.sort(candidatos, new Comparador());
            String saida = "";
            if (candidatos.get(4).getNumVotos() == candidatos.get(3).getNumVotos()) {
                saida = "Houve empate";
            } else {
                saida = "Vencedor: " + candidatos.get(4).getNome();
            }
            saida += "\n" + ((candidatos.get(4).getNumVotos() * 100) / qtdade) + "% " + candidatos.get(4).getNome() + "\n"
                    + ((candidatos.get(3).getNumVotos() * 100) / qtdade) + "% " + candidatos.get(3).getNome() + "\n"
                    + ((candidatos.get(2).getNumVotos() * 100) / qtdade) + "% " + candidatos.get(2).getNome() + "\n"
                    + ((candidatos.get(1).getNumVotos() * 100) / qtdade) + "% " + candidatos.get(1).getNome() + "\n"
                    + ((candidatos.get(0).getNumVotos() * 100) / qtdade) + "% " + candidatos.get(0).getNome();
            return saida;
        }
        return "Eleições não realizada";
    }
}
