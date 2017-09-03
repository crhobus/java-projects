package com.utils.java8.cap2Lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author crhobus
 */
public class Sistema {

    public static void main(String[] args) {
        Usuario user1 = new Usuario("Nome1", 150);
        Usuario user2 = new Usuario("Nome2", 200);
        Usuario user3 = new Usuario("Nome3", 250);

        List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

        for (Usuario u : usuarios) {
            System.out.println(u.getNome());
        }

        System.out.println("\n-----------------------------------------\n");

        Mostrador mostrador = new Mostrador();
        usuarios.forEach(mostrador);

        System.out.println("\n-----------------------------------------\n");

        Consumer<Usuario> mostrador2 = new Consumer<Usuario>() {
            @Override
            public void accept(Usuario u) {
                System.out.println(u.getNome());
            }
        };
        usuarios.forEach(mostrador2);

        System.out.println("\n-----------------------------------------\n");

        usuarios.forEach(new Consumer<Usuario>() {
            @Override
            public void accept(Usuario u) {
                System.out.println(u.getNome());
            }
        });

        System.out.println("\n-----------------------------------------\n");

        Consumer<Usuario> mostrador3 = (Usuario u) -> {
            System.out.println(u.getNome());
        };
        usuarios.forEach(mostrador3);

        System.out.println("\n-----------------------------------------\n");

        Consumer<Usuario> mostrado4 = u -> {
            System.out.println(u.getNome());
        };
        usuarios.forEach(mostrado4);

        System.out.println("\n-----------------------------------------\n");

        Consumer<Usuario> mostrador5 = u -> System.out.println(u.getNome());
        usuarios.forEach(mostrador5);

        System.out.println("\n-----------------------------------------\n");

        usuarios.forEach(u -> System.out.println(u.getNome()));

        //-----------------------------------------
        usuarios.forEach(u -> u.tornaModerador());
    }
}
