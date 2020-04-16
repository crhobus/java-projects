package br.com.app.idgenerator;

import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        System.out.println("UUID: " + UUID.randomUUID());

        System.out.println("ID: " + UUID.randomUUID().toString().replace("-", "").toUpperCase());
    }

}
