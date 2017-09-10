package com.utils.java8.cap11UsandoOsConceitos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author crhobus
 */
public class Compra {

    private List<Produto> produtos;
    private LocalDateTime data;
    private Cliente cliente;

    public Compra(List<Produto> produtos, LocalDateTime data, Cliente cliente) {
        this.produtos = Collections.unmodifiableList(produtos);
        this.data = data;
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "[Compra: "
                + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + " " + cliente
                + " " + produtos
                + "]";
    }
}
