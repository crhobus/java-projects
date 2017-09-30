package com.utils.java8.cap6References.methodReferences;

import com.utils.java8.cap2Lambda.Usuario;
import java.util.ArrayList;
import java.util.Comparator;
import static java.util.Comparator.comparing;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 *
 * @author crhobus
 */
public class Sistema {

    public static void main(String[] args) {
        Usuario user1 = new Usuario("NomeA", 150);
        Usuario user2 = new Usuario("NomeC", 300);
        Usuario user3 = new Usuario("NomeB", 250);
        Usuario user4 = new Usuario("NomeD", 300);
        Usuario user5 = new Usuario("NomeF", 300);
        Usuario user6 = new Usuario("NomeE", 400);
        Usuario user7 = new Usuario("NomeG", 350);
        Usuario user8 = null;

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(user1);
        usuarios.add(user2);
        usuarios.add(user3);
        usuarios.add(user4);
        usuarios.add(user5);
        usuarios.add(user6);
        usuarios.add(user7);

        usuarios.forEach(u -> u.tornaModerador());

        //-----------------------------------------
        usuarios.forEach(Usuario::tornaModerador);

        //-----------------------------------------
        Consumer<Usuario> tornaModerador = Usuario::tornaModerador;
        usuarios.forEach(tornaModerador);

        //-----------------------------------------
        usuarios.sort(Comparator.comparing(u -> u.getNome()));

        //-----------------------------------------
        usuarios.sort(Comparator.comparing(Usuario::getNome));

        //-----------------------------------------
        Function<Usuario, String> byName = Usuario::getNome;
        usuarios.sort(comparing(byName));
        usuarios.forEach(u -> System.out.println(u.getNome()));

        System.out.println("\n-----------------------------------------\n");

        usuarios.sort(Comparator.comparingInt(u -> u.getPontos()));

        //-----------------------------------------
        usuarios.sort(Comparator.comparingInt(Usuario::getPontos));
        usuarios.forEach(u -> System.out.println(u.getNome()));

        System.out.println("\n-----------------------------------------\n");
        Comparator<Usuario> c = Comparator.comparingInt(Usuario::getPontos).thenComparing(Usuario::getNome);
        usuarios.sort(c);
        usuarios.forEach(u -> System.out.println(u.getNome()));

        System.out.println("\n-----------------------------------------\n");
        usuarios.sort(Comparator.comparingInt(Usuario::getPontos).thenComparing(Usuario::getNome));
        usuarios.forEach(u -> System.out.println(u.getNome()));

        usuarios.add(user8);

        System.out.println("\n-----------------------------------------\n");
        usuarios.sort(Comparator.nullsLast(Comparator.comparing(Usuario::getNome)));

        System.out.println("\n-----------------------------------------\n");
        usuarios.sort(Comparator.nullsFirst(Comparator.comparing(Usuario::getNome)));

        System.out.println("\n-----------------------------------------\n");
        usuarios.sort(Comparator.nullsLast(Comparator.comparing(Usuario::getPontos).reversed()));
    }
}
