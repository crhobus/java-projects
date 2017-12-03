package ClinicaVeterinaria;

public class Especie {

    private int id;
    private String descricao;

    public Especie() {
    }

    public Especie(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
