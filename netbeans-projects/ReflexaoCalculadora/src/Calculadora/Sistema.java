package Calculadora;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JOptionPane;

public class Sistema {

    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("exerc1.Calculadora");
            boolean erro = false;
            Constructor<?> construtor;
            Object newInstance = null;
            do {
                try {
                    String tipoConstrutor = JOptionPane.showInputDialog(null, "Informe o valor inicial da calculadora", "Calculadora", JOptionPane.INFORMATION_MESSAGE);
                    if (tipoConstrutor == null) {
                        return;
                    } else {
                        int valor = Integer.parseInt(tipoConstrutor);
                        if (valor == 0) {
                            try {
                                construtor = clazz.getConstructor();// obtem o construtor sem parametros
                                try {
                                    newInstance = construtor.newInstance();// Instancia o construtor sem parametros
                                } catch (IllegalArgumentException ex) {
                                    ex.printStackTrace();
                                } catch (InstantiationException ex) {
                                    ex.printStackTrace();
                                } catch (IllegalAccessException ex) {
                                    ex.printStackTrace();
                                } catch (InvocationTargetException ex) {
                                    ex.printStackTrace();
                                }
                            } catch (SecurityException ex) {
                                ex.printStackTrace();
                            } catch (NoSuchMethodException ex) {
                                ex.printStackTrace();
                            }
                            erro = false;
                        } else {
                            try {
                                construtor = clazz.getConstructor(Integer.class);// Instancia o construtor com parametros
                                try {
                                    newInstance = construtor.newInstance(valor);// /obtem o construtor com parametros
                                } catch (IllegalArgumentException ex) {
                                    ex.printStackTrace();
                                } catch (InstantiationException ex) {
                                    ex.printStackTrace();
                                } catch (IllegalAccessException ex) {
                                    ex.printStackTrace();
                                } catch (InvocationTargetException ex) {
                                    ex.printStackTrace();
                                }
                            } catch (SecurityException ex) {
                                ex.printStackTrace();
                            } catch (NoSuchMethodException ex) {
                                ex.printStackTrace();
                            }
                            erro = false;
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Informe um número válido", "Erro", JOptionPane.ERROR_MESSAGE);
                    erro = true;
                }
            } while (erro);
            do {
                try {
                    String tipoSoma = JOptionPane.showInputDialog(null, "Informe o tipo da soma 1 - 4", "Calculadora", JOptionPane.INFORMATION_MESSAGE);
                    if (tipoSoma == null) {
                        return;
                    } else {
                        int valor = Integer.parseInt(tipoSoma);
                        if (valor >= 1 && valor <= 4) {
                            erro = false;
                            if (valor == 1) {
                                try {
                                    Method m1 = clazz.getMethod("soma", int.class);// retorna o metodo que contem o nome com o tipo do parametro
                                    do {
                                        try {
                                            String strValor = JOptionPane.showInputDialog(null, "Informe um valor", "Calculadora", JOptionPane.INFORMATION_MESSAGE);
                                            if (strValor == null) {
                                                break;
                                            } else {
                                                int num = Integer.parseInt(strValor);
                                                erro = false;
                                                try {
                                                    System.out.println("Resultado da soma: " + m1.invoke(newInstance, num));// executa o  método do objeto passando o parametro
                                                } catch (IllegalArgumentException ex) {
                                                    ex.printStackTrace();
                                                } catch (IllegalAccessException ex) {
                                                    ex.printStackTrace();
                                                } catch (InvocationTargetException ex) {
                                                    ex.printStackTrace();
                                                }
                                            }
                                        } catch (NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(null, "Informe um número válido", "Erro", JOptionPane.ERROR_MESSAGE);
                                            erro = true;
                                        }
                                    } while (erro);
                                } catch (SecurityException ex) {
                                    ex.printStackTrace();
                                } catch (NoSuchMethodException ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                if (valor == 2) {
                                    try {
                                        Method m2 = clazz.getMethod("soma", int.class, int.class);// retorna o metodo que contem o nome com os 2 tipos de parametros
                                        do {
                                            try {
                                                String strValor1 = JOptionPane.showInputDialog(null, "Informe um valor", "Calculadora", JOptionPane.INFORMATION_MESSAGE);
                                                if (strValor1 == null) {
                                                    break;
                                                } else {
                                                    int num1 = Integer.parseInt(strValor1);
                                                    String strValor2 = JOptionPane.showInputDialog(null, "Informe o segundo valor", "Calculadora", JOptionPane.INFORMATION_MESSAGE);
                                                    if (strValor2 == null) {
                                                        break;
                                                    } else {
                                                        int num2 = Integer.parseInt(strValor2);
                                                        erro = false;
                                                        try {
                                                            System.out.println("Resultado da soma: " + m2.invoke(newInstance, num1, num2));// executa o método do objeto passando  os parametros
                                                        } catch (IllegalArgumentException ex) {
                                                            ex.printStackTrace();
                                                        } catch (IllegalAccessException ex) {
                                                            ex.printStackTrace();
                                                        } catch (InvocationTargetException ex) {
                                                            ex.printStackTrace();
                                                        }
                                                    }
                                                }
                                            } catch (NumberFormatException ex) {
                                                JOptionPane.showMessageDialog(null, "Informe um número válido", "Erro", JOptionPane.ERROR_MESSAGE);
                                                erro = true;
                                            }
                                        } while (erro);
                                        erro = false;
                                    } catch (SecurityException ex) {
                                        ex.printStackTrace();
                                    } catch (NoSuchMethodException ex) {
                                        ex.printStackTrace();
                                    }
                                } else {
                                    if (valor == 3) {
                                        try {
                                            Method m3 = clazz.getMethod("soma", int.class, int.class, int.class);// retorna o metodo que contem o nome com os 3 tipos de parametros
                                            do {
                                                try {
                                                    String strValor1 = JOptionPane.showInputDialog(null, "Informe um valor", "Calculadora", JOptionPane.INFORMATION_MESSAGE);
                                                    if (strValor1 == null) {
                                                        break;
                                                    } else {
                                                        int num1 = Integer.parseInt(strValor1);
                                                        String strValor2 = JOptionPane.showInputDialog(null, "Informe o segundo valor", "Calculadora", JOptionPane.INFORMATION_MESSAGE);
                                                        if (strValor2 == null) {
                                                            break;
                                                        } else {
                                                            int num2 = Integer.parseInt(strValor2);
                                                            String strValor3 = JOptionPane.showInputDialog(null, "Informe o terceiro valor", "Calculadora", JOptionPane.INFORMATION_MESSAGE);
                                                            if (strValor3 == null) {
                                                                break;
                                                            } else {
                                                                int num3 = Integer.parseInt(strValor3);
                                                                erro = false;
                                                                try {
                                                                    System.out.println("Resultado da soma: " + m3.invoke(newInstance, num1, num2, num3));// executa  o  método do objeto passando os parametros
                                                                } catch (IllegalArgumentException ex) {
                                                                    ex.printStackTrace();
                                                                } catch (IllegalAccessException ex) {
                                                                    ex.printStackTrace();
                                                                } catch (InvocationTargetException ex) {
                                                                    ex.printStackTrace();
                                                                }
                                                            }
                                                        }
                                                    }
                                                } catch (NumberFormatException ex) {
                                                    JOptionPane.showMessageDialog(null, "Informe um número válido", "Erro", JOptionPane.ERROR_MESSAGE);
                                                    erro = true;
                                                }
                                            } while (erro);
                                            erro = false;
                                        } catch (SecurityException ex) {
                                            ex.printStackTrace();
                                        } catch (NoSuchMethodException ex) {
                                            ex.printStackTrace();
                                        }
                                    } else {
                                        try {
                                            Method m4 = clazz.getMethod("soma", String.class, int.class, int.class);// retorna o metodo que contem o nome com os 3  tipos de parametros
                                            do {
                                                try {
                                                    String str = JOptionPane.showInputDialog(null, "Informe uma palavra", "Calculadora", JOptionPane.INFORMATION_MESSAGE);
                                                    if (str == null) {
                                                        break;
                                                    } else {
                                                        String strValor1 = JOptionPane.showInputDialog(null, "Informe um valor", "Calculadora", JOptionPane.INFORMATION_MESSAGE);
                                                        if (strValor1 == null) {
                                                            break;
                                                        } else {
                                                            int num1 = Integer.parseInt(strValor1);
                                                            String strValor2 = JOptionPane.showInputDialog(null, "Informe o segundo valor", "Calculadora", JOptionPane.INFORMATION_MESSAGE);
                                                            if (strValor2 == null) {
                                                                break;
                                                            } else {
                                                                int num2 = Integer.parseInt(strValor2);
                                                                erro = false;
                                                                try {
                                                                    System.out.println("Resultado da soma: " + m4.invoke(newInstance, str, num1, num2));// executa o  método do objeto passando os parametros
                                                                } catch (IllegalArgumentException ex) {
                                                                    ex.printStackTrace();
                                                                } catch (IllegalAccessException ex) {
                                                                    ex.printStackTrace();
                                                                } catch (InvocationTargetException ex) {
                                                                    ex.printStackTrace();
                                                                }
                                                            }
                                                        }
                                                    }
                                                } catch (NumberFormatException ex) {
                                                    JOptionPane.showMessageDialog(null, "Informe um número válido", "Erro", JOptionPane.ERROR_MESSAGE);
                                                    erro = true;
                                                }
                                            } while (erro);
                                            erro = false;
                                        } catch (SecurityException ex) {
                                            ex.printStackTrace();
                                        } catch (NoSuchMethodException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Informe um número válido", "Erro", JOptionPane.ERROR_MESSAGE);
                            erro = true;
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Informe um número válido", "Erro", JOptionPane.ERROR_MESSAGE);
                    erro = true;
                }
            } while (erro);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
