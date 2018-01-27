package Modelo;

import java.io.Serializable;

public class Cep extends Cidade implements Serializable {

    private int codCep;
    private String cep;

    public int getCodCep() {
        return codCep;
    }

    public void setCodCep(int codCep) {
        this.codCep = codCep;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
