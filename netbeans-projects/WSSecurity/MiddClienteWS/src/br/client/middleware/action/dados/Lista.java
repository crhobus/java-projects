package br.client.middleware.action.dados;
/*
 * Document   : Lista.java
 * Created on : 23/09/2013, 20:45:12
 * Author     : Caio
 */

import java.util.ArrayList;
import java.util.List;

public class Lista {

    private List<Registro> registros;

    public Lista() {
        this.registros = new ArrayList<>();
    }

    public void addRegistro(Registro registro) {
        this.registros.add(registro);
    }

    public void removeRegistro(Registro registro) {
        this.registros.remove(registro);
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }
}