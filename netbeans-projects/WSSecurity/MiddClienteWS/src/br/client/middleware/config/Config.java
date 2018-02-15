package br.client.middleware.config;
/*
 * Document   : Config.java
 * Created on : 22/09/2013, 10:42:17
 * Author     : Caio
 */

public class Config {

    private String nmUsuario;
    private String dsSenha;
    private static Config config;

    private Config() {
    }

    public static Config getInstancie() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public String getDsSenha() {
        return dsSenha;
    }

    public void setDsSenha(String dsSenha) {
        this.dsSenha = dsSenha;
    }
}