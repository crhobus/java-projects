package br.com.app.arquivo;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Escrita {

    public static void main(String[] args) {
        String caminho = "teste.txt";
        String conteudo = "Hello World!";

        try {
            Files.writeString(new File(caminho).toPath(), conteudo, StandardCharsets.ISO_8859_1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
