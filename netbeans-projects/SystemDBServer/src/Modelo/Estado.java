package Modelo;

import java.io.Serializable;

public class Estado extends Regiao implements Serializable {

    private int codEstado;
    private String estado;

    public int getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(int codEstado) {
        this.codEstado = codEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
