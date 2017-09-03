package com.utils.java8.cap4DefaultMethods.executarMetodoDefault;

import com.utils.java8.cap2Lambda.Usuario;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author crhobus
 */
public class Sistema {

    public static void main(String[] args) {
        Usuario user1 = new Usuario("Nome1", 150);
        Usuario user2 = new Usuario("Nome2", 200);
        Usuario user3 = new Usuario("Nome3", 250);

        List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

        Consumer<Usuario> mostraMensagem = u -> System.out.println("antes de imprimir os nomes");

        Consumer<Usuario> imprimeNome = u -> System.out.println(u.getNome());

        usuarios.forEach(mostraMensagem.andThen(imprimeNome));
    }
}
