package Modelo;

import java.io.Serializable;

public class Bairro extends Cep implements Serializable {

    private int codBairro;
    private String bairro;

    public int getCodBairro() {
        return codBairro;
    }

    public void setCodBairro(int codBairro) {
        this.codBairro = codBairro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
