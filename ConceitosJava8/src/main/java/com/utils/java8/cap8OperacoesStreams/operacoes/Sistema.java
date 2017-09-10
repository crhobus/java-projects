package com.utils.java8.cap8OperacoesStreams.operacoes;

import com.utils.java8.cap2Lambda.Usuario;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;
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

        usuarios.sort(Comparator.comparing(Usuario::getNome));
        usuarios.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        List<Usuario> listaUser = usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .sorted(Comparator.comparing(Usuario::getNome))
                .collect(Collectors.toList());
        listaUser.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        List<Usuario> listaUser2 = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario.getPontos() > 100) {
                listaUser2.add(usuario);
            }
        }
        Collections.sort(listaUser2, new Comparator<Usuario>() {
            public int compare(Usuario u1, Usuario u2) {
                return u1.getNome().compareTo(u2.getNome());
            }
        });
        for (Usuario usuario : listaUser2) {
            System.out.println(usuario);
        }

        System.out.println("\n-----------------------------------------\n");

        Optional<Usuario> usuarioOptional = usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .findAny();
        System.out.println(usuarioOptional.orElse(null).getNome());

        System.out.println("\n-----------------------------------------\n");

        Optional<Usuario> usuario2Optional = usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .findFirst();
        System.out.println(usuario2Optional.orElse(null).getNome());

        System.out.println("\n-----------------------------------------\n");

        Optional<Usuario> usuario3Optional = usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .peek(System.out::println)
                .findAny();
        System.out.println(usuario3Optional.orElse(null).getNome());

        System.out.println("\n-----------------------------------------\n");

        Optional<Usuario> usuario4Optional = usuarios.stream()
                .sorted(Comparator.comparing(Usuario::getNome))
                .peek(System.out::println)
                .findAny();
        System.out.println(usuario4Optional.orElse(null).getNome());

        System.out.println("\n-----------------------------------------\n");

        double pontuacaoMedia = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .average()
                .getAsDouble();
        System.out.println(pontuacaoMedia);

        Optional<Usuario> max = usuarios.stream()
                .max(Comparator.comparing(Usuario::getPontos));
        Usuario maximaPontuacao = max.get();
        System.out.println(maximaPontuacao.getNome());

        int total = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .sum();
        System.out.println(total);

        System.out.println("\n-----------------------------------------\n");

        int valorInicial = 0;
        IntBinaryOperator operacao = (a, b) -> a + b;
        total = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .reduce(valorInicial, operacao);
        System.out.println(total);

        System.out.println("\n-----------------------------------------\n");

        total = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .reduce(0, (a, b) -> a + b);
        System.out.println(total);

        total = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .reduce(0, Integer::sum);
        System.out.println(total);

        int multiplicacao = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .reduce(1, (a, b) -> a * b);
        System.out.println(multiplicacao);

        total = usuarios.stream()
                .reduce(0, (atual, u) -> atual + u.getPontos(), Integer::sum);
        System.out.println(total);

        System.out.println("\n-----------------------------------------\n");

        Iterator<Usuario> i = usuarios.stream().iterator();

        usuarios.stream().iterator()
                .forEachRemaining(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        boolean hasModerator = usuarios.stream()
                .anyMatch(Usuario::isModerador);
        System.out.println(hasModerator);

        boolean allModerator = usuarios.stream()
                .allMatch(Usuario::isModerador);
        System.out.println(allModerator);

        boolean noneModerator = usuarios.stream()
                .noneMatch(Usuario::isModerador);
        System.out.println(noneModerator);

        System.out.println("\n-----------------------------------------\n");

        long count = usuarios.stream().count();
        System.out.println(count);

        List<Usuario> users = usuarios.stream().skip(0).collect(Collectors.toList());//skip irá pular os dois primeiros
        users.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        List<Usuario> users2 = usuarios.stream().limit(5).collect(Collectors.toList());
        users2.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        Stream<Usuario> users3 = Stream.of(user1, user2, user3);//cria um Stream com 3 usuários
        users3.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        Stream<Usuario> users4 = Stream.empty();//cria uma Stream vazia

        System.out.println("\n-----------------------------------------\n");

        Stream<Usuario> users5 = Stream.of(user4, user5, user6);
        Stream<Usuario> users6 = Stream.of(user7, user8, user9);

        List<Usuario> users7 = Stream.concat(users5, users6).collect(Collectors.toList());
        users7.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        //--gera números randomicamente infinitos, como é lazy só executa quando utilizado o stream, não usar o sum neste caso, pois ficará em loop infinito
        Random random = new Random(0);
        Supplier<Integer> supplier = () -> random.nextInt();
        Stream<Integer> stream = Stream.generate(supplier);

        //ou
        Random random2 = new Random(0);
        IntStream stream2 = IntStream.generate(() -> random2.nextInt());

        //fim
        System.out.println("\n-----------------------------------------\n");

        //como tem um limit não ficará em loop infinito, pois pegará só os 100 primeiros da lista
        Random random3 = new Random(0);
        IntStream stream3 = IntStream.generate(() -> random3.nextInt());
        List<Integer> list = stream3
                .limit(100)
                .boxed()
                .collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        IntStream.generate(new Fibonacci())
                .limit(10)
                .forEach(System.out::println);

        System.out.println("\n-----------------------------------------\n");

        int maiorQue100 = IntStream
                .generate(new Fibonacci())
                .filter(f -> f > 100)
                .findFirst()
                .getAsInt();
        System.out.println(maiorQue100);
    }
}
