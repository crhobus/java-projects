package Orquestra;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.JOptionPane;

public class Sistema {

    private static int getNumero(String s) {
        int valor = 0;
        boolean erro = true;
        do {
            try {
                String valorInicial = JOptionPane.showInputDialog(null, s);
                valor = Integer.parseInt(valorInicial);
                erro = false;
            } catch (Exception e) {
                erro = true;
            }
        } while (erro);

        return valor;
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class classeOrq = Class.forName("orquestra.Orquestra");
        Constructor<?> construtorOrq;
        Object objOrq;
        int opcao = getNumero("Entre com uma das opções:  1-classico  2-lirico  3-opera");
        if (opcao == 1) {
            construtorOrq = classeOrq.getConstructor(String.class);
            objOrq = construtorOrq.newInstance("classico");
        } else {
            if (opcao == 2) {
                construtorOrq = classeOrq.getConstructor(String.class);
                objOrq = construtorOrq.newInstance("lirico");
            } else {
                if (opcao == 3) {
                    construtorOrq = classeOrq.getConstructor(String.class);
                    objOrq = construtorOrq.newInstance("opera");
                }
            }
        }
        Class classeCla = Class.forName("orquestra.Classificado");
        Constructor<?> construtorCla;
        Object objOrqCla = classeCla.newInstance();
        //Method addFuncionario = classeCla.getDeclaredMethod("add", funcionario.getClass());
        //addFuncionario.invoke(objControl, funcionario);
    }
}
