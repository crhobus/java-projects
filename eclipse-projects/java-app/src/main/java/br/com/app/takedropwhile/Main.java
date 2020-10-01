package br.com.app.takedropwhile;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> collect;
        List<String> lista = List.of("bmw", "audi", "volkswagen", "chevrolet", "honda").stream().sorted().collect(Collectors.toList());

        collect = lista.stream().takeWhile(f -> f.length() < 5).collect(Collectors.toList());
        System.out.println(collect); // [audi, bmw]

        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        collect = lista.stream().dropWhile(f -> f.length() < 5).collect(Collectors.toList());
        System.out.println(collect); // [chevrolet, honda, volkswagen]

    }

}
