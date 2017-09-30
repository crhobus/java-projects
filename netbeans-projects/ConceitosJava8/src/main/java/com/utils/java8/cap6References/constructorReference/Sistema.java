package com.utils.java8.cap6References.constructorReference;

import com.utils.java8.cap2Lambda.Usuario;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;
import java.util.function.ToIntBiFunction;

/**
 *
 * @author crhobus
 */
public class Sistema {

    public static void main(String[] args) {
        Supplier<Usuario> user = Usuario::new;
        Usuario novo = user.get();

        //-----------------------------------------
        Function<String, Usuario> criadorDeUsuarios = Usuario::new;
        Usuario user2 = criadorDeUsuarios.apply("User 2");
        Usuario user3 = criadorDeUsuarios.apply("User 3");

        //-----------------------------------------
        BiFunction<String, Integer, Usuario> criadorDeUsuarios2 = Usuario::new;
        Usuario user4 = criadorDeUsuarios2.apply("User 4", 50);
        Usuario user5 = criadorDeUsuarios2.apply("User 5", 300);

        //-----------------------------------------
        BiFunction<Integer, Integer, Integer> max = Math::max;
        int n = max.apply(1, 2);
        System.out.println(n);

        ToIntBiFunction<Integer, Integer> max2 = Math::max;

        IntBinaryOperator max3 = Math::max;

    }
}
