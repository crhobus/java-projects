package com.utils.java8.cap7StreamsCollectors.stream;

import com.utils.java8.cap2Lambda.Usuario;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import java.util.stream.IntStream;
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

        usuarios.stream()
                .filter(u -> u.getPontos() > 100);

        usuarios.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        Stream<Usuario> stream = usuarios.stream().filter(u -> u.getPontos() > 100);

        stream.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .forEach(Usuario::tornaModerador);

        usuarios.stream()
                .filter(u -> u.isModerador())
                .forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        usuarios.stream()
                .filter(Usuario::isModerador)
                .forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        List<Usuario> userMaior100 = new ArrayList<>();
        usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .forEach(u -> userMaior100.add(u));

        userMaior100.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        List<Usuario> user2Maior100 = new ArrayList<>();
        usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .forEach(user2Maior100::add);

        user2Maior100.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        Supplier<ArrayList<Usuario>> supplier = ArrayList::new;
        BiConsumer<ArrayList<Usuario>, Usuario> accumulator = ArrayList::add;
        BiConsumer<ArrayList<Usuario>, ArrayList<Usuario>> combiner = ArrayList::addAll;

        List<Usuario> user3Maior100 = usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .collect(supplier, accumulator, combiner);

        user3Maior100.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        List<Usuario> user4Maior100 = usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        user4Maior100.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        List<Usuario> user5Maior100 = usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .collect(Collectors.toList());

        user5Maior100.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        List<Usuario> user6Maior100 = usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .collect(toList());

        user6Maior100.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        Set<Usuario> user7Maior100 = usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .collect(toSet());

        user7Maior100.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        Set<Usuario> user8Maior100 = usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .collect(Collectors.toCollection(HashSet::new));

        user8Maior100.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        List<Integer> pontos = new ArrayList<>();
        usuarios.forEach(u -> pontos.add(u.getPontos()));

        pontos.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        List<Integer> pontos2 = usuarios.stream()
                .map(u -> u.getPontos())
                .collect(toList());

        pontos2.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        List<Integer> pontos3 = usuarios.stream()
                .map(Usuario::getPontos)
                .collect(toList());

        pontos3.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        Stream<Integer> stream2 = usuarios.stream()
                .map(Usuario::getPontos);

        stream2.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        IntStream stream3 = usuarios.stream()
                .mapToInt(Usuario::getPontos);

        stream3.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        double pontuacaoMedia = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .average()
                .getAsDouble();

        System.out.println(pontuacaoMedia);

        //versão antiga
        double soma = 0;
        for (Usuario u : usuarios) {
            soma += u.getPontos();
        }
        double pontuacaoMedia2 = soma / usuarios.size();
        System.out.println(pontuacaoMedia2);

        System.out.println("\n-----------------------------------------\n");

        OptionalDouble media = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .average();
        double pontuacaoMedia3 = media.orElse(0.0);
        System.out.println(pontuacaoMedia3);

        //ou
        double pontuacaoMedia4 = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .average()
                .orElse(0.0);
        System.out.println(pontuacaoMedia4);

        //versão antiga
        double soma2 = 0;
        for (Usuario u : usuarios) {
            soma2 += u.getPontos();
        }
        double pontuacaoMedia5;
        if (usuarios.isEmpty()) {
            pontuacaoMedia5 = 0;
        } else {
            pontuacaoMedia5 = soma2 / usuarios.size();
        }
        System.out.println(pontuacaoMedia5);

        System.out.println("\n-----------------------------------------\n");

        double pontuacaoMedia6 = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .average()
                .orElseThrow(IllegalStateException::new);

        System.out.println(pontuacaoMedia6);

        System.out.println("\n-----------------------------------------\n");

        usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .average()
                .ifPresent(valor -> atualiza(valor));//Verifica se existe valor dentro do opcional, se existir passa um Consumer como argumento

        System.out.println("\n-----------------------------------------\n");

        Optional<Usuario> max = usuarios
                .stream()
                .max(Comparator.comparingInt(Usuario::getPontos));//retorna os usuários

        System.out.println(max.orElseThrow(IllegalStateException::new).getNome());//caso não tiver usuário na lista e vier null lança exceção, caso contrário mostra o nome do usuário

        Optional<String> maxNome = usuarios
                .stream()
                .max(Comparator.comparingInt(Usuario::getPontos))
                .map(u -> u.getNome());//retorna nome do usuário
        System.out.println(maxNome.orElse("Não possui usuário na lista"));

        //ou
        Optional<String> maxNome2 = usuarios
                .stream()
                .max(Comparator.comparingInt(Usuario::getPontos))
                .map(Usuario::getNome);
        System.out.println(maxNome2.get());
    }

    private static void atualiza(double valor) {
        System.out.println("Valor: " + valor);
    }
}
