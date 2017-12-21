package Funcionario;

import java.lang.reflect.Field;
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

    public static void main(String[] args) throws Exception {

        Class classeControle = Class.forName("exerc3.CadastroFuncionarios");
        Class classeFuncionario = Class.forName("exerc3.Funcionario");

        Object objControl = classeControle.newInstance();
        int qtdadeFuncionarios = getNumero("Entre com a quantidade de funcionário a serem cadastrados");

        Field[] atributos = classeFuncionario.getDeclaredFields();
        for (int i = 0; i < qtdadeFuncionarios; i++) {
            Object funcionario = classeFuncionario.newInstance();
            for (int j = 0; j < atributos.length; j++) {
                String string = JOptionPane.showInputDialog(null, "Entre com o " + atributos[j].getName() + " do funcionário:");
                atributos[j].setAccessible(true);
                if (atributos[j].getName().equals("nome")) {
                    atributos[j].set(funcionario, string);
                } else if (atributos[j].getName().equals("idade")) {
                    int valor = Integer.parseInt(string);
                    atributos[j].set(funcionario, valor);
                } else if (atributos[j].getName().equals("salario")) {
                    double valor = Double.parseDouble(string);
                    atributos[j].set(funcionario, valor);
                } else if (atributos[j].getName().equals("endereco")) {
                    atributos[j].set(funcionario, string);
                }
            }
            Method addFuncionario = classeControle.getDeclaredMethod("add", funcionario.getClass());
            addFuncionario.invoke(objControl, funcionario);
        }
        Method getTotalSalario = classeControle.getDeclaredMethod("getTotalSalarios");
        System.out.println(getTotalSalario.getName() + ": " + getTotalSalario.invoke(objControl));
    }
}
