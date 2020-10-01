package br.com.app.base64;

import java.util.Base64;

public class Main {

    public static void main(String[] args) {
        try {
            String texto = "A classe Base64 no java8!";
            System.out.println(texto);

            String textoCodificado = Base64.getEncoder().encodeToString(texto.getBytes("UTF-8"));
            System.out.println(textoCodificado);

            String textoDecodificado = new String(Base64.getDecoder().decode(textoCodificado), "UTF-8");
            System.out.println(textoDecodificado);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
