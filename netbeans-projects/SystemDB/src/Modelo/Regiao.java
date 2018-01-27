package Modelo;

import java.io.Serializable;

public class Regiao extends Pais implements Serializable {

    private int codRegiao;
    private String regiao;

    public int getCodRegiao() {
        return codRegiao;
    }

    public void setCodRegiao(int codRegiao) {
        this.codRegiao = codRegiao;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }
}
