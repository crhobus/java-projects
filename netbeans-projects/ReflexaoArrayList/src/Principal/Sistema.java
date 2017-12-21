package Principal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Sistema {

    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("java.util.ArrayList");
            System.out.println("Todos os atributos\n");
            Field[] atributos = clazz.getDeclaredFields();
            for (int i = 0; i < atributos.length; i++) {
                System.out.println(atributos[i].toString());
            }
            System.out.println("\nTodos os construtores\n");
            Constructor<?>[] construtores = clazz.getDeclaredConstructors();
            for (int i = 0; i < construtores.length; i++) {
                System.out.println(construtores[i].toString());
            }
            System.out.println("\nTodos os métodos\n");
            Method metodo[] = clazz.getDeclaredMethods();
            for (int i = 0; i < metodo.length; i++) {
                System.out.println(metodo[i].toString());
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
