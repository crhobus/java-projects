package com.utils.java8.cap8OperacoesStreams.fileStream;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author crhobus
 */
public class Sistema2 {

    public static void main(String[] args) {
        try {

            Files.list(Paths.get("C:\\Users\\crhobus\\MyDataFiles\\Documents\\Arquivos\\"))
                    .forEach(System.out::println);

            System.out.println("\n-----------------------------------------\n");

            Files.list(Paths.get("C:\\Users\\crhobus\\MyDataFiles\\Documents\\Arquivos\\"))
                    .filter(p -> p.toString().endsWith(".txt"))
                    .forEach(System.out::println);

            System.out.println("\n-----------------------------------------\n");

            Files.list(Paths.get("C:\\Users\\crhobus\\MyDataFiles\\Documents\\Arquivos\\"))
                    .filter(p -> p.toString().endsWith(".txt"))
                    .map(p -> lines(p))
                    .forEach(System.out::println);

            System.out.println("\n-----------------------------------------\n");

            Stream<Stream<String>> strings = Files.list(Paths.get("C:\\Users\\crhobus\\MyDataFiles\\Documents\\Arquivos\\"))
                    .filter(p -> p.toString().endsWith(".txt"))
                    .map(p -> lines(p));

            System.out.println("\n-----------------------------------------\n");

            Stream<String> strings2 = Files.list(Paths.get("C:\\Users\\crhobus\\MyDataFiles\\Documents\\Arquivos\\"))
                    .filter(p -> p.toString().endsWith(".txt"))
                    .flatMap(p -> lines(p));

            System.out.println("\n-----------------------------------------\n");

            IntStream chars = Files.list(Paths.get("C:\\Users\\crhobus\\MyDataFiles\\Documents\\Arquivos\\"))
                    .filter(p -> p.toString().endsWith(".txt"))
                    .flatMap(p -> lines(p))
                    .flatMapToInt((String s) -> s.chars());

        } catch (IOException ex) {
            Logger.getLogger(Sistema2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Stream<String> lines(Path p) {
        try {
            return Files.lines(p);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
