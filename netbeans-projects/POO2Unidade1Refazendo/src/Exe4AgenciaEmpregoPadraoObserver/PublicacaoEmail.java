package Exe4AgenciaEmpregoPadraoObserver;

import java.util.ArrayList;
import java.util.List;

public class PublicacaoEmail implements VagaListener {

    private List<Candidato> candidatos = new ArrayList<Candidato>();

    public void addCandidato(Candidato candidato) {
        candidatos.add(candidato);
    }

    public void removeCandidato(Candidato candidato) {
        candidatos.remove(candidato);
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public void novaVaga(Vaga vaga) {
        for (Candidato candidato : candidatos) {
            if (candidato.isAreaInteresse(vaga.getArea())) {
                System.out.printf("Publicação de vaga enviada por e-mail para %s na Area: %s, Empresa %s, Salario %.2f\nEmail: %s\n", candidato.getNome(), vaga.getArea(), vaga.getEmpresa(), vaga.getSalario(), candidato.getEmail());
            }
        }
    }
}
