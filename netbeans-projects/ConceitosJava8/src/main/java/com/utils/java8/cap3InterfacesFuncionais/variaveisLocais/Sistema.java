package com.utils.java8.cap3InterfacesFuncionais.variaveisLocais;

/**
 *
 * @author crhobus
 */
public class Sistema {

    public static void main(String[] args) {
        final int numero = 5;
        new Thread(() -> {
            System.out.println(numero);
        }).start();

        //-----------------------------------------
        int numero2 = 6;
        new Thread(() -> {
            System.out.println(numero2);
        }).start();
    }
}
