package com.utils.java8.cap8OperacoesStreams.distinct;

import com.utils.java8.cap2Lambda.Usuario;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author crhobus
 */
public class Grupo {

    private Set<Usuario> usuarios = new HashSet<>();

    public void add(Usuario u) {
        usuarios.add(u);
    }

    public Set<Usuario> getUsuarios() {
        return Collections.unmodifiableSet(this.usuarios);
    }
}
