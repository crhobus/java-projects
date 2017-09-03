package com.utils.java8.cap3InterfacesFuncionais.interfaceFuncional;

/**
 *
 * @author crhobus
 */
public class Sistema {

    public static void main(String[] args) {
        Validador<String> validadorCEP = new Validador<String>() {

            @Override
            public boolean valida(String valor) {
                return valor.matches("[0-9]{5}-[0-9]{3}");
            }
        };
        System.out.println(validadorCEP.valida("89130-000"));

        System.out.println("\n-----------------------------------------\n");

        Validador<String> validadorCEP2 = valor -> {
            return valor.matches("[0-9]{5}-[0-9]{3}");
        };
        System.out.println(validadorCEP2.valida("89130-000"));

        System.out.println("\n-----------------------------------------\n");

        Validador<String> validadorCEP3 = valor -> valor.matches("[0-9]{5}-[0-9]{3}");
        System.out.println(validadorCEP3.valida("89130-000"));
    }
}
