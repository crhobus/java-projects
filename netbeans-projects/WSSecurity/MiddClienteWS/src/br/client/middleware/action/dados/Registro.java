package br.client.middleware.action.dados;
/*
 * Document   : Registro.java
 * Created on : 23/09/2013, 20:40:15
 * Author     : Caio
 */

import java.util.HashMap;
import java.util.Map;

public class Registro {

    private Map<String, Object> info;

    public Registro() {
        this.info = new HashMap<>();
    }

    public void addInfo(String chave, Object valor) {
        this.info.put(chave, valor);
    }

    public void removeInfo(String chave) {
        this.info.remove(chave);
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }
}