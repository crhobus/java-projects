package com.utils.java8.cap5Ordenacao.comparatorsComoLambda;

import com.utils.java8.cap2Lambda.Usuario;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import static java.util.Comparator.comparing;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/**
 *
 * @author crhobus
 */
public class Sistema {

    public static void main(String[] args) {
        Usuario user1 = new Usuario("NomeD", 150);
        Usuario user2 = new Usuario("NomeB", 200);
        Usuario user3 = new Usuario("NomeF", 250);
        Usuario user4 = new Usuario("NomeA", 110);
        Usuario user5 = new Usuario("NomeC", 160);
        Usuario user6 = new Usuario("NomeE", 220);

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(user1);
        usuarios.add(user2);
        usuarios.add(user3);
        usuarios.add(user4);
        usuarios.add(user5);
        usuarios.add(user6);

        Comparator<Usuario> comparator = new Comparator<Usuario>() {
            @Override
            public int compare(Usuario u1, Usuario u2) {
                return u1.getNome().compareTo(u2.getNome());
            }
        };
        Collections.sort(usuarios, comparator);

        usuarios.forEach(u -> System.out.println(u.getNome()));

        System.out.println("\n-----------------------------------------\n");

        comparator = (u1, u2) -> u1.getNome().compareTo(u2.getNome());
        Collections.sort(usuarios, comparator);
        usuarios.forEach(u -> System.out.println(u.getNome()));

        System.out.println("\n-----------------------------------------\n");

        Collections.sort(usuarios, (u1, u2) -> u1.getNome().compareTo(u2.getNome()));
        usuarios.forEach(u -> System.out.println(u.getNome()));

        System.out.println("\n-----------------------------------------\n");

        //util.List possui um sort
        usuarios.sort((u1, u2) -> u1.getNome().compareTo(u2.getNome()));
        usuarios.forEach(u -> System.out.println(u.getNome()));

        System.out.println("\n-----------------------------------------\n");

        comparator = Comparator.comparing(u -> u.getNome());
        usuarios.sort(comparator);
        usuarios.forEach(u -> System.out.println(u.getNome()));

        System.out.println("\n-----------------------------------------\n");

        usuarios.sort(Comparator.comparing(u -> u.getNome()));
        usuarios.forEach(u -> System.out.println(u.getNome()));

        System.out.println("\n-----------------------------------------\n");

        usuarios.sort(comparing(u -> u.getNome()));
        usuarios.forEach(u -> System.out.println(u.getNome()));

        System.out.println("\n-----------------------------------------\n");

        List<String> palavras = Arrays.asList("palavra2", "palavra3", "palavra1");
        Collections.sort(palavras);
        palavras.forEach(s -> System.out.println(s));

        System.out.println("\n-----------------------------------------\n");

        palavras.sort(Comparator.naturalOrder());
        palavras.forEach(s -> System.out.println(s));

        System.out.println("\n-----------------------------------------\n");

        palavras.sort(Comparator.reverseOrder());
        palavras.forEach(s -> System.out.println(s));

        System.out.println("\n-----------------------------------------\n");

        Function<Usuario, String> extraiNome = u -> u.getNome();
        comparator = Comparator.comparing(extraiNome);
        usuarios.sort(comparator);
        usuarios.forEach(u -> System.out.println(u.getNome()));

        System.out.println("\n-----------------------------------------\n");

        usuarios.sort(comparing(u -> u.getPontos()));
        usuarios.forEach(u -> System.out.println(u.getNome()));

        System.out.println("\n-----------------------------------------\n");

        Function<Usuario, Integer> extraiPontos = u -> u.getPontos();
        comparator = Comparator.comparing(extraiPontos);
        usuarios.sort(comparator);
        usuarios.forEach(u -> System.out.println(u.getNome()));

        System.out.println("\n-----------------------------------------\n");

        ToIntFunction<Usuario> extraiPontos2 = u -> u.getPontos();
        comparator = Comparator.comparingInt(extraiPontos2);
        usuarios.sort(comparator);
        usuarios.forEach(u -> System.out.println(u.getNome()));

        System.out.println("\n-----------------------------------------\n");

        usuarios.sort(Comparator.comparingInt(u -> u.getPontos()));
        usuarios.forEach(u -> System.out.println(u.getNome()));
    }
}
