package com.utils.java8.cap2Lambda;

import java.util.function.Consumer;

/**
 *
 * @author crhobus
 */
public class Mostrador implements Consumer<Usuario> {

    @Override
    public void accept(Usuario u) {
        System.out.println(u.getNome());
    }
}
