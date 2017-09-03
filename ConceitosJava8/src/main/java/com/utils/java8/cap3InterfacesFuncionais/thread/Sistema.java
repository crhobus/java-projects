package com.utils.java8.cap3InterfacesFuncionais.thread;

/**
 *
 * @author crhobus
 */
public class Sistema {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i <= 1000; i++) {
                    System.out.println("Saída 1: " + i);
                }
            }
        };
        new Thread(runnable).start();

        //-----------------------------------------
        Runnable runnable2 = () -> {
            for (int i = 0; i <= 1000; i++) {
                System.out.println("Saída 2: " + i);
            }
        };
        new Thread(runnable2).start();

        //-----------------------------------------
        new Thread(() -> {
            for (int i = 0; i <= 1000; i++) {
                System.out.println("Saída 3: " + i);
            }
        }).start();
    }
}
