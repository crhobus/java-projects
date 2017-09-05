package com.utils.java8.cap8OperacoesStreams;

import com.utils.java8.cap2Lambda.Usuario;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author crhobus
 */
public class Sistema3 {

    public static void main(String[] args) {
        Usuario user1 = new Usuario("Nome1", 50);
        Usuario user2 = new Usuario("Nome2", 150);
        Usuario user3 = new Usuario("Nome3", 80);

        Grupo englishSpeakers = new Grupo();
        englishSpeakers.add(user1);
        englishSpeakers.add(user2);

        Grupo spanishSpeakers = new Grupo();
        spanishSpeakers.add(user2);
        spanishSpeakers.add(user3);

        List<Grupo> groups = Arrays.asList(englishSpeakers, spanishSpeakers);

        groups.stream()
                .flatMap(g -> g.getUsuarios().stream())
                .distinct()
                .forEach(System.out::println);
    }
}
