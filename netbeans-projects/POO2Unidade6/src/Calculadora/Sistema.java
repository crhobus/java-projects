package Calculadora;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JOptionPane;

public class Sistema {

    private static int getNumero(String s) {
        boolean erro = true;
        do {
            try {
                String valor = JOptionPane.showInputDialog(null, s);
                int n = Integer.parseInt(valor);
                erro = false;
                return n;
            } catch (Exception e) {
                erro = true;
            }
        } while (erro);
        return 0;
    }

    public static void main(String[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> classe = Class.forName("exerc1.Calculadora");
        Constructor<?> construtor;
        int inicio = getNumero("Digite o valor inicial da calculadora:");
        Object o;
        if (inicio > 0) {
            construtor = classe.getConstructor();
            o = construtor.newInstance();
        } else {
            construtor = classe.getConstructor(Integer.class);
            o = construtor.newInstance(Integer.valueOf(inicio));
        }

        Method metodo;
        int tipoSoma = getNumero("Digite um valor de 1 até 4");
        if (tipoSoma == 1) {
            int n1 = getNumero("Digite um valor:");
            metodo = classe.getMethod("soma", int.class);
            System.out.println(metodo.invoke(o, n1));
        } else if (tipoSoma == 2) {
            int n1 = getNumero("Digite um valor:");
            int n2 = getNumero("Digite um valor:");
            metodo = classe.getMethod("soma", int.class, int.class);
            System.out.println(metodo.invoke(o, n1, n2));
        } else if (tipoSoma == 3) {
            int n1 = getNumero("Digite um valor:");
            int n2 = getNumero("Digite um valor:");
            int n3 = getNumero("Digite um valor:");
            metodo = classe.getMethod("soma", int.class, int.class, int.class);
            System.out.println(metodo.invoke(o, n1, n2, n3));
        } else {
            String s = JOptionPane.showInputDialog("Digite:");
            int n1 = getNumero("Digite um valor:");
            int n2 = getNumero("Digite um valor:");
            metodo = classe.getMethod("soma", String.class, int.class, int.class);
            System.out.println(metodo.invoke(o, s, n1, n2));
        }
    }
}
