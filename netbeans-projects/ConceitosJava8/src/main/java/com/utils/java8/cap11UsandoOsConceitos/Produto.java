package com.utils.java8.cap11UsandoOsConceitos;

import java.math.BigDecimal;
import java.nio.file.Path;

/**
 *
 * @author crhobus
 */
public class Produto {

    private String nome;
    private Path arquivo;
    private BigDecimal preco;

    public Produto(String nome, Path arquivo, BigDecimal preco) {
        this.nome = nome;
        this.arquivo = arquivo;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public Path getArquivo() {
        return arquivo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
