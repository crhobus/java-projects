package com.utils.java8.cap2Lambda;

/**
 *
 * @author crhobus
 */
public class Usuario {

    private String nome;
    private int pontos;
    private boolean moderador;

    public Usuario() {
        this.moderador = false;
    }

    public Usuario(String nome) {
        this.nome = nome;
        this.moderador = false;
    }

    public Usuario(String nome, int pontos) {
        this.nome = nome;
        this.pontos = pontos;
        this.moderador = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void tornaModerador() {
        this.moderador = true;
    }

    public boolean isModerador() {
        return moderador;
    }

    @Override
    public String toString() {
        return "Usuario: " + nome + " - Pontos: " + pontos + " - Moderador: " + (moderador ? "S" : "N");
    }
}
