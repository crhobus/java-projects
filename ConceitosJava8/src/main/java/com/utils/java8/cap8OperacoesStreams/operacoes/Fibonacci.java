package com.utils.java8.cap8OperacoesStreams.operacoes;

import java.util.function.IntSupplier;

/**
 *
 * @author crhobus
 */
public class Fibonacci implements IntSupplier {

    private int anterior = 0;
    private int proximo = 1;

    @Override
    public int getAsInt() {
        proximo = proximo + anterior;
        anterior = proximo - anterior;
        return anterior;
    }
}
