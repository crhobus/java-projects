package com.utils.java8.cap4DefaultMethods.removeIf;

import com.utils.java8.cap2Lambda.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author crhobus
 */
public class Sistema {

    public static void main(String[] args) {
        Usuario user1 = new Usuario("Nome1", 150);
        Usuario user2 = new Usuario("Nome2", 200);
        Usuario user3 = new Usuario("Nome3", 250);

        Predicate<Usuario> predicado = new Predicate<Usuario>() {
            @Override
            public boolean test(Usuario u) {
                return u.getPontos() > 160;
            }
        };

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(user1);
        usuarios.add(user2);
        usuarios.add(user3);

        usuarios.removeIf(predicado);
        usuarios.forEach(u -> System.out.println(u.getNome()));

        System.out.println("\n-----------------------------------------\n");

        Usuario user4 = new Usuario("Nome4", 110);
        Usuario user5 = new Usuario("Nome5", 160);
        Usuario user6 = new Usuario("Nome6", 220);

        List<Usuario> usuarios2 = new ArrayList<>();
        usuarios2.add(user4);
        usuarios2.add(user5);
        usuarios2.add(user6);

        usuarios2.removeIf(u -> u.getPontos() > 160);
        usuarios2.forEach(u -> System.out.println(u.getNome()));
    }
}
