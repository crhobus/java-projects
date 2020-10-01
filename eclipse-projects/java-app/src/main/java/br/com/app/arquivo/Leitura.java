package br.com.app.arquivo;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Leitura {

    public static void main(String[] args) {
        String caminho = "teste.txt";

        try {
            String conteudo = Files.readString(new File(caminho).toPath(), StandardCharsets.ISO_8859_1);
            System.out.println(conteudo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
