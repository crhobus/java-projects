package br.com.app;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        List<DTO> originalList = new ArrayList<>();
        originalList.add(new DTO("a"));
        originalList.add(new DTO("b"));
        originalList.add(new DTO("c"));
        originalList.add(new DTO("d"));
        originalList.add(new DTO("e"));
        originalList.add(new DTO("f"));
        originalList.add(new DTO("g"));
        originalList.add(new DTO("h"));
        originalList.add(new DTO("i"));
        originalList.add(new DTO("j"));
        originalList.add(new DTO("k"));
        originalList.add(new DTO("l"));
        originalList.add(new DTO("m"));
        originalList.add(new DTO("n"));
        originalList.add(new DTO("o"));
        originalList.add(new DTO("p"));
        originalList.add(new DTO("q"));
        originalList.add(new DTO("r"));
        originalList.add(new DTO("s"));
        originalList.add(new DTO("t"));
        originalList.add(new DTO("u"));
        originalList.add(new DTO("v"));
        originalList.add(new DTO("x"));
        originalList.add(new DTO("w"));
        originalList.add(new DTO("y"));
        originalList.add(new DTO("z"));

        System.out.println(originalList.size());

        List<DTO> subList;
        int n = 10;
        while (!originalList.isEmpty()) {
            if (n <= originalList.size()) {
                subList = new ArrayList<>(originalList.subList(0, n));
            } else {
                subList = new ArrayList<>(originalList.subList(0, originalList.size()));
            }
            originalList.removeAll(subList);

            //////////////////////////////////////////////////////////////////////////////////////

            System.out.println("");
            System.out.println("Ini lisagem");

            subList.forEach(l -> {
                System.out.println("str: " + l.getStr());
            });

            System.out.println("Fim lisagem");
        }
    }

}
