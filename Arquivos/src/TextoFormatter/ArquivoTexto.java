package TextoFormatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ArquivoTexto {

    private Formatter saida;

    private int getInteger(String s) {
        int n = 1;
        boolean erro = true;
        do {
            String str = JOptionPane.showInputDialog(null, s, "Entrada", JOptionPane.INFORMATION_MESSAGE);
            if (str == null) {
                erro = false;
            } else {
                try {
                    n = Integer.parseInt(str);
                    if (n <= 0) {
                        JOptionPane.showMessageDialog(null, "O número da conta deve ser maior do que 0", "Erro", JOptionPane.ERROR_MESSAGE);
                        erro = true;
                    } else {
                        erro = false;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Entre com um número válido", "Erro", JOptionPane.ERROR_MESSAGE);
                    erro = true;
                }
            }
        } while (erro);
        return n;
    }

    private double getDouble(String s) {
        double n = 0;
        boolean erro = true;
        do {
            String str = JOptionPane.showInputDialog(null, s, "Entrada", JOptionPane.INFORMATION_MESSAGE);
            if (str == null) {
                erro = false;
            } else {
                try {
                    n = Double.parseDouble(str);
                    erro = false;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Entre com um número válido", "Erro", JOptionPane.ERROR_MESSAGE);
                    erro = true;
                }
            }
        } while (erro);
        return n;
    }

    private String getString(String s) {
        String str = "";
        str = JOptionPane.showInputDialog(null, s, "Entrada", JOptionPane.INFORMATION_MESSAGE);
        return str;
    }

    private void abrirArq() {
        try {
            saida = new Formatter("clientes.txt");
        } catch (SecurityException ex) {
            JOptionPane.showMessageDialog(null, "Você não tem acesso para gravar este arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addRegistros() {
        List<Conta> listaContas = new ArrayList<Conta>();
        listaContas = lerRegistros();
        boolean erroOpcao = true;
        do {
            try {
                String opcao = JOptionPane.showInputDialog(null, "Precione 1 para cadastrar uma nova conta e 2 para remover uma conta", "Escolha", JOptionPane.QUESTION_MESSAGE);
                if (opcao == null) {
                    erroOpcao = false;
                } else {
                    int numOpcao = Integer.parseInt(opcao);
                    if (numOpcao == 1 || numOpcao == 2) {
                        abrirArq();
                        if (numOpcao == 1) {
                            boolean erro = true;
                            do {
                                try {
                                    int numConta = getInteger("Entre com o numero da conta");
                                    String nome = getString("Entre com o nome");
                                    String sobrenome = getString("Entre com o sobrenome");
                                    double saldo = getDouble("Entre com o saldo");
                                    boolean encontrado = false;
                                    for (int i = 0; i < listaContas.size(); i++) {
                                        Conta aux = listaContas.get(i);
                                        if (numConta == aux.getConta()) {
                                            encontrado = true;
                                        }
                                    }
                                    if (encontrado == true) {
                                        int resposta = JOptionPane.showConfirmDialog(null, "A conta com o número " + numConta + " ja está cadastrada deseja substituíla?", "Pergunta", JOptionPane.YES_NO_OPTION);
                                        if (resposta == 0) {
                                            for (int i = 0; i < listaContas.size(); i++) {
                                                Conta aux = listaContas.get(i);
                                                if (numConta == aux.getConta()) {
                                                    listaContas.remove(aux);
                                                }
                                            }
                                            Conta conta = new Conta(numConta, nome, sobrenome, saldo);
                                            listaContas.add(conta);
                                        }
                                    } else {
                                        Conta conta = new Conta(numConta, nome, sobrenome, saldo);
                                        listaContas.add(conta);
                                    }
                                } catch (FormatterClosedException ex) {
                                    JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
                                    return;
                                } catch (NoSuchElementException ex) {
                                    JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, tente novamente", "Erro", JOptionPane.ERROR_MESSAGE);
                                    erro = true;
                                }
                                boolean err = true;
                                do {
                                    String confirma = JOptionPane.showInputDialog(null, "Deseja cadastrar mais um registro pressione S/N");
                                    if (confirma == null) {
                                        erro = false;
                                        break;
                                    } else {
                                        if ("S".equalsIgnoreCase(confirma)) {
                                            err = false;
                                        } else {
                                            if ("N".equalsIgnoreCase(confirma)) {
                                                err = false;
                                                erro = false;
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Entrada invalida, tente novamente", "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                } while (err);
                            } while (erro);
                            for (int i = 0; i < listaContas.size(); i++) {
                                Conta aux = listaContas.get(i);
                                saida.format("%d %s %s %.2f\n", aux.getConta(), aux.getNome(),
                                        aux.getSobrenome(), aux.getSaldo());
                            }
                        } else {
                            boolean erroRemove = true;
                            do {
                                String strConta = JOptionPane.showInputDialog(null, "Entre com o numero da conta a ser removida", "Entrada", JOptionPane.QUESTION_MESSAGE);
                                if (strConta == null) {
                                    erroRemove = false;
                                } else {
                                    try {
                                        int numConta = Integer.parseInt(strConta);
                                        boolean encontrado = false;
                                        for (int i = 0; i < listaContas.size(); i++) {
                                            Conta aux = listaContas.get(i);
                                            if (numConta == aux.getConta()) {
                                                listaContas.remove(aux);
                                                encontrado = true;
                                            }
                                        }
                                        if (encontrado == true) {
                                            JOptionPane.showMessageDialog(null, "Registro removido com sucesso");
                                            erroRemove = false;
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Registro não encontrado", "Erro", JOptionPane.WARNING_MESSAGE);
                                            erroRemove = false;
                                        }

                                    } catch (NumberFormatException ex) {
                                        JOptionPane.showMessageDialog(null, "Entrada inválida, tente novamente", "Erro", JOptionPane.ERROR_MESSAGE);
                                        erroRemove = true;
                                    }
                                }
                            } while (erroRemove);
                            for (int i = 0; i < listaContas.size(); i++) {
                                Conta aux = listaContas.get(i);
                                saida.format("%d %s %s %.2f\n", aux.getConta(), aux.getNome(),
                                        aux.getSobrenome(), aux.getSaldo());
                            }
                        }
                        fecharArq();
                    } else {
                        JOptionPane.showMessageDialog(null, "Entre com uma opção válida", "Erro", JOptionPane.ERROR_MESSAGE);
                        erroOpcao = true;
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Entre com um numero válido", "Erro", JOptionPane.ERROR_MESSAGE);
                erroOpcao = true;
            }
        } while (erroOpcao);
    }

    private void fecharArq() {
        if (saida != null) {
            saida.close();
        }
    }

    public List<Conta> lerRegistros() {
        List<Conta> listaConta = new ArrayList<Conta>();
        Scanner input = null;
        File arq = new File("clientes.txt");
        if (arq.exists() == true) {
            try {
                input = new Scanner(new File("clientes.txt"));
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo para leitura", "Erro", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
            try {
                while (input.hasNext()) {
                    Conta conta = new Conta();
                    conta.setConta(input.nextInt());
                    conta.setNome(input.next());
                    conta.setSobrenome(input.next());
                    conta.setSaldo(input.nextDouble());
                    listaConta.add(conta);
                }
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
            if (input != null) {
                input.close();
            }
        }
        return listaConta;
    }
}
