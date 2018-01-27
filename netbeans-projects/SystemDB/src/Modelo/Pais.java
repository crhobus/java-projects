package Modelo;

import java.io.Serializable;

public class Pais implements Serializable {

    private int codPais;
    private String pais;

    public int getCodPais() {
        return codPais;
    }

    public void setCodPais(int codPais) {
        this.codPais = codPais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
