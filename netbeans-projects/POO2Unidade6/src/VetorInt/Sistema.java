package VetorInt;

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

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> classe = Class.forName("exerc2.VetorInt");
        Constructor<?> construtor;
        Object o;
        Method metodo;
        int qtdadeMax = getNumero("Entre com a quantidade máxima de elementos");
        String valorPadrao = JOptionPane.showInputDialog(null, "Entre com o valor padrão");
        if (valorPadrao.length() == 0) {
            construtor = classe.getConstructor(int.class);
            o = construtor.newInstance(qtdadeMax);
            for (int i = 0; i < qtdadeMax - 1; i++) {
                int valor = getNumero("Entre com um numero");
                metodo = classe.getMethod("add", int.class);
                metodo.invoke(o, valor);
            }
        } else {
            int qtdadePadrao = Integer.parseInt(valorPadrao);
            construtor = classe.getConstructor(int.class, int.class);
            o = construtor.newInstance(qtdadeMax, qtdadePadrao);
        }
        metodo = classe.getMethod("getQtosElementos");
        System.out.println(metodo.getName() + ": " + metodo.invoke(o));
        metodo = classe.getMethod("getSoma");
        System.out.println(metodo.getName() + ": " + metodo.invoke(o));
        metodo = classe.getMethod("getMedia");
        System.out.println(metodo.getName() + ": " + metodo.invoke(o));
        metodo = classe.getMethod("getMin");
        System.out.println(metodo.getName() + ": " + metodo.invoke(o));
        metodo = classe.getMethod("getMax");
        System.out.println(metodo.getName() + ": " + metodo.invoke(o));
    }
}
