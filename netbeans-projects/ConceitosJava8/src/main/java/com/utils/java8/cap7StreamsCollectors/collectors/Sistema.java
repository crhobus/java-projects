package com.utils.java8.cap7StreamsCollectors.collectors;

import com.utils.java8.cap2Lambda.Usuario;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author crhobus
 */
public class Sistema {

    public static void main(String[] args) {
        Usuario user1 = new Usuario("Nome1", 50);
        Usuario user2 = new Usuario("Nome2", 150);
        Usuario user3 = new Usuario("Nome3", 80);
        Usuario user4 = new Usuario("Nome4", 200);
        Usuario user5 = new Usuario("Nome5", 130);
        Usuario user6 = new Usuario("Nome6", 140);
        Usuario user7 = new Usuario("Nome7", 10);
        Usuario user8 = new Usuario("Nome8", 20);
        Usuario user9 = new Usuario("Nome9", 160);
        Usuario user10 = new Usuario("Nome10", 230);
        Usuario user11 = new Usuario("Nome11", 400);
        Usuario user12 = new Usuario("Nome12", 500);
        Usuario user13 = new Usuario("Nome13", 600);
        Usuario user14 = new Usuario("Nome14", 630);
        Usuario user15 = new Usuario("Nome15", 520);
        Usuario user16 = new Usuario("Nome16", 125);
        Usuario user17 = new Usuario("Nome17", 350);
        Usuario user18 = new Usuario("Nome18", 450);
        Usuario user19 = new Usuario("Nome19", 720);
        Usuario user20 = new Usuario("Nome20", 900);
        Usuario user21 = new Usuario("Nome21", 15);
        Usuario user22 = new Usuario("Nome22", 70);
        Usuario user23 = new Usuario("Nome23", 40);
        Usuario user24 = new Usuario("Nome24", 190);
        Usuario user25 = new Usuario("Nome25", 360);
        Usuario user26 = new Usuario("Nome26", 420);
        Usuario user27 = new Usuario("Nome27", 510);
        Usuario user28 = new Usuario("Nome28", 820);
        Usuario user29 = new Usuario("Nome29", 720);
        Usuario user30 = new Usuario("Nome30", 620);

        List<Usuario> usuarios = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12, user13, user14,
                user15, user16, user17, user18, user19, user20, user21, user22, user23, user24, user25, user26, user27, user28, user29, user30);

        usuarios.sort(Comparator.comparing(Usuario::getPontos).reversed());
        usuarios.subList(0, 10).forEach(Usuario::tornaModerador);
        usuarios.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        Collections.sort(usuarios, new Comparator<Usuario>() {
            @Override
            public int compare(Usuario u1, Usuario u2) {
                return u1.getPontos() - u2.getPontos();
            }
        });
        Collections.reverse(usuarios);
        List<Usuario> top10 = usuarios.subList(0, 10);
        for (Usuario u : top10) {
            u.tornaModerador();
        }
        for (Usuario u : top10) {
            System.out.println(u);
        }

        System.out.println("\n-----------------------------------------\n");

        for (Usuario usuario : usuarios) {
            if (usuario.getPontos() > 100) {
                usuario.tornaModerador();
            }
        }
        for (Usuario u : usuarios) {
            System.out.println(u);
        }

        System.out.println("\n-----------------------------------------\n");

        Stream<Usuario> stream = usuarios.stream();
        stream.filter(u -> {
            return u.getPontos() > 100;
        });

        System.out.println("\n-----------------------------------------\n");

        stream = usuarios.stream();
        stream.filter(u -> u.getPontos() > 100);

        usuarios.stream().filter(u -> u.getPontos() > 100);
    }
}
