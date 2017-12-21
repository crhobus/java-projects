package Exe4AgenciaEmpregoPadraoObserver;

import java.util.ArrayList;
import java.util.List;

public class Candidato {

    private String nome, email;
    private List<String> areas = new ArrayList<String>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void addArea(String area) {
        areas.add(area);
    }

    public boolean isAreaInteresse(String areaInteresse) {
        for (String vet : areas) {
            if (vet.equalsIgnoreCase(areaInteresse)) {
                return true;
            }
        }
        return false;
    }
}
