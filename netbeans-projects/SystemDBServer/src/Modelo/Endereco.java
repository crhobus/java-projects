package Modelo;

import java.io.Serializable;

public class Endereco extends Bairro implements Serializable {

    private int codEndereco;
    private String endereco;

    public int getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(int codEndereco) {
        this.codEndereco = codEndereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
