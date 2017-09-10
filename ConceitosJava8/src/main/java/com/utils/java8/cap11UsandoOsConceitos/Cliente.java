package com.utils.java8.cap11UsandoOsConceitos;

/**
 *
 * @author crhobus
 */
public class Cliente {

    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
