package com.utils.java8.cap9MapPartAgrupParalelizando;

import com.utils.java8.cap2Lambda.Usuario;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 *
 * @author crhobus
 */
public class Sistema {

    public static void main(String[] args) {
        try {

            Stream<String> strings = Files.list(Paths.get("C:\\Users\\crhobus\\MyDataFiles\\Documents\\Arquivos\\"))
                    .filter(p -> p.toString().endsWith(".txt"))
                    .flatMap(p -> lines(p));
            strings.forEach(System.out::println);

            System.out.println("\n-----------------------------------------\n");

            LongStream lines = Files.list(Paths.get("C:\\Users\\crhobus\\MyDataFiles\\Documents\\Arquivos\\"))
                    .filter(p -> p.toString().endsWith(".txt"))
                    .mapToLong(p -> lines(p).count());

            lines.forEach(System.out::println);

            System.out.println("\n-----------------------------------------\n");

            List<Long> lines2 = Files.list(Paths.get("C:\\Users\\crhobus\\MyDataFiles\\Documents\\Arquivos\\"))
                    .filter(p -> p.toString().endsWith(".txt"))
                    .map(p -> lines(p).count())
                    .collect(Collectors.toList());
            lines2.forEach(System.out::println);

            System.out.println("\n-----------------------------------------\n");

            Map<Path, Long> linesPerFile = new HashMap<>();
            Files.list(Paths.get("C:\\Users\\crhobus\\MyDataFiles\\Documents\\Arquivos\\"))
                    .filter(p -> p.toString().endsWith(".txt"))
                    .forEach(p -> linesPerFile.put(p, lines(p).count()));
            System.out.println(linesPerFile);

            //ou
            Map<Path, Long> lines3 = Files.list(Paths.get("C:\\Users\\crhobus\\MyDataFiles\\Documents\\Arquivos\\"))
                    .filter(p -> p.toString().endsWith(".txt"))
                    .collect(Collectors.toMap(
                            p -> p,
                            p -> lines(p).count()));
            System.out.println(lines3);

            System.out.println("\n-----------------------------------------\n");

            Map<Path, List<String>> content = Files.list(Paths.get("C:\\Users\\crhobus\\MyDataFiles\\Documents\\Arquivos\\"))
                    .filter(p -> p.toString().endsWith(".txt"))
                    .collect(Collectors.toMap(
                            Function.identity(),//Vai retornar a instância do objeto Path, sempre a mesma instância
                            p -> lines(p).collect(Collectors.toList())));

            System.out.println("\n-----------------------------------------\n");

            Usuario user1 = new Usuario("Nome1", 50);
            Usuario user2 = new Usuario("Nome2", 150);
            Usuario user3 = new Usuario("Nome3", 80);
            Usuario user4 = new Usuario("Nome4", 200);
            Usuario user5 = new Usuario("Nome5", 130);

            List<Usuario> usuarios = Arrays.asList(user1, user2, user3, user4, user5);

            Map<String, Usuario> nameToUser = usuarios.stream()
                    .collect(Collectors.toMap(
                            Usuario::getNome,
                            Function.identity()));

            System.out.println("\n-----------------------------------------\n");

            Usuario userX = new Usuario("userX", 50, true);
            Usuario userY = new Usuario("userY", 150, true);
            Usuario userZ = new Usuario("userZ", 80);
            Usuario userW = new Usuario("userW", 200);
            Usuario userP = new Usuario("userP", 200);

            usuarios = Arrays.asList(userX, userY, userZ, userW, userP);

            //Modo anterior ao java8
            Map<Integer, List<Usuario>> pontuacao = new HashMap<>();
            for (Usuario u : usuarios) {
                if (!pontuacao.containsKey(u.getPontos())) {
                    pontuacao.put(u.getPontos(), new ArrayList<>());
                }
                pontuacao.get(u.getPontos()).add(u);
            }
            System.out.println(pontuacao);
            //fim

            //após
            Map<Integer, List<Usuario>> pontuacao2 = new HashMap<>();
            for (Usuario u : usuarios) {
                pontuacao2
                        .computeIfAbsent(u.getPontos(), user -> new ArrayList<>())//caso não existir a chave vai criar uma nova ArrayList, e a nova chave do map, e depois adicionar o objeto a lista, se existir a chave, depois apenas adiciona o objeto a essa lista, se tiver 2 objetos com o mesmo "pontos" que é a chave, vai apenas adicioná-lo a lista
                        .add(u);
            }
            System.out.println(pontuacao2);

            //abaixo mesma saída do anterior, vai criar um map, onde a chave é os pontos e o valor é o objeto usuário dentro de uma lista
            Map<Integer, List<Usuario>> pontuacao3 = usuarios
                    .stream()
                    .collect(Collectors.groupingBy(Usuario::getPontos));
            System.out.println(pontuacao3);

            System.out.println("\n-----------------------------------------\n");

            Map<Boolean, List<Usuario>> moderadores = usuarios
                    .stream()
                    .collect(Collectors.partitioningBy(Usuario::isModerador));//Irá criar um map com a chave boolean, uma true para quem é moderador e outra false para quem não é, e o valor será uma lista contendo cada usuário
            System.out.println(moderadores);

            System.out.println("\n-----------------------------------------\n");

            Map<Boolean, List<String>> nomesPorTipo = usuarios.stream()
                    .collect(
                            Collectors.partitioningBy(
                                    Usuario::isModerador,
                                    Collectors.mapping(Usuario::getNome,
                                            Collectors.toList())));
            System.out.println(nomesPorTipo);

            System.out.println("\n-----------------------------------------\n");

            Map<Boolean, Integer> pontuacaoPorTipo = usuarios.stream()
                    .collect(
                            Collectors.partitioningBy(
                                    Usuario::isModerador,
                                    Collectors.summingInt(Usuario::getPontos)));
            System.out.println(pontuacaoPorTipo);

            System.out.println("\n-----------------------------------------\n");

            String nomes = usuarios.stream()
                    .map(Usuario::getNome)
                    .collect(Collectors.joining(", "));
            System.out.println(nomes);

            System.out.println("\n-----------------------------------------\n");

            Usuario user10 = new Usuario("Nome10", 230);
            Usuario user11 = new Usuario("Nome11", 20);
            Usuario user12 = new Usuario("Nome12", 500);
            Usuario user13 = new Usuario("Nome13", 60);
            Usuario user14 = new Usuario("Nome14", 630);
            Usuario user15 = new Usuario("Nome15", 520);
            Usuario user16 = new Usuario("Nome16", 125);
            Usuario user17 = new Usuario("Nome17", 80);
            Usuario user18 = new Usuario("Nome18", 450);
            Usuario user19 = new Usuario("Nome19", 50);
            Usuario user20 = new Usuario("Nome20", 900);

            usuarios = Arrays.asList(user10, user11, user12, user13, user14, user15, user16, user17, user18, user19, user20);

            List<Usuario> filtradosOrdenados = usuarios.stream()
                    .filter(u -> u.getPontos() > 100)
                    .sorted(Comparator.comparing(Usuario::getNome))
                    .collect(Collectors.toList());
            System.out.println(filtradosOrdenados);

            //Mesmo exemplo anterior mas usando paralelismo
            List<Usuario> filtradosOrdenados2 = usuarios.parallelStream()
                    .filter(u -> u.getPontos() > 100)
                    .sorted(Comparator.comparing(Usuario::getNome))
                    .collect(Collectors.toList());
            System.out.println(filtradosOrdenados2);

            System.out.println("\n-----------------------------------------\n");

            long sum = LongStream.range(0, 1000000000)
                    .parallel()//executa paralelo
                    .filter(x -> x % 2 == 0)
                    .sum();
            System.out.println(sum);

            long sum2 = LongStream.range(0, 1000000000)
                    .sequential()//executa normal sem thread
                    .filter(x -> x % 2 == 0)
                    .sum();
            System.out.println(sum2);

        } catch (IOException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Stream<String> lines(Path p) {
        try {
            return Files.lines(p, StandardCharsets.ISO_8859_1);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
