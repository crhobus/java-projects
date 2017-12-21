package CadasFuncionario;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JOptionPane;

public class Sistema {

    public static void main(String[] args) {
        try {
            Class<?> clazzCadasFunc = Class.forName("exerc3.CadastroFuncionarios");
            Class<?> clazzFunc = Class.forName("exerc3.Funcionario");

            Constructor<?> construtorCadasFunc = clazzCadasFunc.getConstructor();
            Object cadasFunc = construtorCadasFunc.newInstance();

            Constructor<?> construtorFunc = clazzFunc.getConstructor();

            boolean erro = false;
            do {
                String strQtdade = JOptionPane.showInputDialog(null, "Quantos funciários deseja cadastrar", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
                if (strQtdade == null) {
                    erro = false;
                } else {
                    try {
                        int qtdade = Integer.parseInt(strQtdade);
                        erro = false;
                        for (int i = 0; i < qtdade; i++) {
                            Object funcionario = construtorFunc.newInstance();

                            Method nome = clazzFunc.getMethod("setNome", String.class);
                            nome.invoke(funcionario, JOptionPane.showInputDialog(null, "Entre com o nome do funciário", "Funcionário", JOptionPane.INFORMATION_MESSAGE));

                            Method endereco = clazzFunc.getMethod("setEndereco", String.class);
                            endereco.invoke(funcionario, JOptionPane.showInputDialog(null, "Entre com o endereco do funciário", "Funcionário", JOptionPane.INFORMATION_MESSAGE));

                            Method idade = clazzFunc.getMethod("setIdade", int.class);
                            erro = false;
                            do {
                                try {
                                    idade.invoke(funcionario, Integer.parseInt(JOptionPane.showInputDialog(null, "Entre com a idade do funciário", "Funcionário", JOptionPane.INFORMATION_MESSAGE)));
                                    erro = false;
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Informe um número válido", "Erro", JOptionPane.ERROR_MESSAGE);
                                    erro = true;
                                }
                            } while (erro);

                            Method salario = clazzFunc.getMethod("setSalario", double.class);
                            erro = false;
                            do {
                                try {
                                    salario.invoke(funcionario, Double.parseDouble(JOptionPane.showInputDialog(null, "Entre com o salário do funciário", "Funcionário", JOptionPane.INFORMATION_MESSAGE)));
                                    erro = false;
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null, "Informe um número válido", "Erro", JOptionPane.ERROR_MESSAGE);
                                    erro = true;
                                }
                            } while (erro);
                            Method addFunc = clazzCadasFunc.getMethod("add", funcionario.getClass());
                            addFunc.invoke(cadasFunc, funcionario);
                        }
                        Method totalSalario = clazzCadasFunc.getMethod("getTotalSalarios");
                        System.out.println("Total Salarios: " + totalSalario.invoke(cadasFunc));
                        erro = false;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Informe um número válido", "Erro", JOptionPane.ERROR_MESSAGE);
                        erro = true;
                    }
                }
            } while (erro);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        } catch (SecurityException ex) {
            ex.printStackTrace();
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}
