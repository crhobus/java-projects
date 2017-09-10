package com.utils.java8.cap6References.methodReferencesInstancia;

import com.utils.java8.cap2Lambda.Usuario;
import java.util.function.Consumer;

/**
 *
 * @author crhobus
 */
public class Sistema {

    public static void main(String[] args) {
        Usuario user = new Usuario("User 1", 50);
        Runnable bloco = user::tornaModerador;
        bloco.run();

        //bloco1 e bloco2 são iguais:
        bloco = user::tornaModerador;//method reference
        bloco = () -> user.tornaModerador();//lambda
        //-----//

        //-----------------------------------------
        Usuario user2 = new Usuario("User 2", 100);
        Consumer<Usuario> consumer = Usuario::tornaModerador;
        consumer.accept(user2);
        System.out.println(user2.isModerador());

        //consumer1 e consumer2 são iguais:
        consumer = Usuario::tornaModerador;//method reference
        consumer = u -> u.tornaModerador();//lambda
        //-----//
    }
}
