package com.utils.java8.cap6References.methodReferencesInstanciaArgumentos;

import com.utils.java8.cap2Lambda.Usuario;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author crhobus
 */
public class Sistema {

    public static void main(String[] args) {
        Usuario user1 = new Usuario("User 1", 150);
        Usuario user2 = new Usuario("User 2", 120);
        Usuario user3 = new Usuario("User 3", 190);

        List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

        usuarios.forEach(System.out::println);
    }
}
