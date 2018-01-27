package Modelo;

import java.io.Serializable;

public class Cidade extends Estado implements Serializable {

    private int codCidade;
    private String cidade;

    public int getCodCidade() {
        return codCidade;
    }

    public void setCodCidade(int codCidade) {
        this.codCidade = codCidade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
