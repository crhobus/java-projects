package ObjectInputOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ArquivoSequencial {

    private ObjectOutputStream output;

    private int getInteger(String s) {
        int n = 0;
        boolean erro = true;
        do {
            String str = JOptionPane.showInputDialog(null, s, "Entrada", JOptionPane.INFORMATION_MESSAGE);
            if (str == null) {
                erro = false;
            } else {
                try {
                    n = Integer.parseInt(str);
                    erro = false;
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

    public List<Conta> lerRegistros() {
        List listaConta = new ArrayList();
        File arq = new File("clientesBin.str");
        if (arq.exists() == true) {
            ObjectInputStream input;
            try {
                input = new ObjectInputStream(new FileInputStream("clientesBin.str"));
                try {
                    listaConta = (List) input.readObject();
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao criar o objeto", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                input.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo para leitura", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listaConta;
    }

    private void abrirArq() {
        try {
            output = new ObjectOutputStream(new FileOutputStream("clientesBin.str"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo para gravação", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addRegistros() {
        List<Conta> listaConta = new ArrayList<Conta>();
        listaConta = lerRegistros();
        int numOpcao = 0;
        boolean erroOpcao = true;
        do {
            String opcao = JOptionPane.showInputDialog(null, "Precione 1 para cadastrar uma nova conta e 2 para remover uma conta", "Escolha", JOptionPane.QUESTION_MESSAGE);
            if (opcao == null) {
                erroOpcao = false;
            } else {
                try {
                    numOpcao = Integer.parseInt(opcao);
                    if (numOpcao == 1 || numOpcao == 2) {
                        abrirArq();
                        erroOpcao = false;
                        if (numOpcao == 1) {
                            boolean erro = true;
                            do {
                                int numConta = getInteger("Entre com o numero da conta");
                                String nome = getString("Entre com o nome");
                                String sobrenome = getString("Entre com o sobrenome");
                                double saldo = getDouble("Entre com o saldo");
                                boolean encontrado = false;
                                for (int i = 0; i < listaConta.size(); i++) {
                                    Conta aux = listaConta.get(i);
                                    if (numConta == aux.getConta()) {
                                        encontrado = true;
                                    }
                                }
                                if (encontrado == true) {
                                    int resposta = JOptionPane.showConfirmDialog(null, "A conta com o número " + numConta + " ja está cadastrada deseja substituíla?", "Pergunta", JOptionPane.YES_NO_OPTION);
                                    if (resposta == 0) {
                                        for (int i = 0; i < listaConta.size(); i++) {
                                            Conta aux = listaConta.get(i);
                                            if (numConta == aux.getConta()) {
                                                listaConta.remove(aux);
                                            }
                                        }
                                        Conta conta = new Conta(numConta, nome, sobrenome, saldo);
                                        listaConta.add(conta);
                                    }
                                } else {
                                    Conta conta = new Conta(numConta, nome, sobrenome, saldo);
                                    listaConta.add(conta);
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
                        } else {
                            String strConta = JOptionPane.showInputDialog(null, "Entre com o numero da conta a ser removida", "Entrada", JOptionPane.QUESTION_MESSAGE);
                            if (strConta != null) {
                                if (listaConta.isEmpty() == true) {
                                    JOptionPane.showMessageDialog(null, "Não há registros no arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    boolean erroRemove = true;
                                    do {
                                        try {
                                            int numConta = Integer.parseInt(strConta);
                                            boolean encontrado = false;
                                            for (int i = 0; i < listaConta.size(); i++) {
                                                Conta aux = listaConta.get(i);
                                                if (numConta == aux.getConta()) {
                                                    listaConta.remove(aux);
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
                                        } catch (Exception ex) {
                                            JOptionPane.showMessageDialog(null, "Entrada inválida, tente novamente", "Erro", JOptionPane.ERROR_MESSAGE);
                                            erroRemove = true;
                                        }
                                    } while (erroRemove);
                                }
                            }
                        }
                        try {
                            output.writeObject(listaConta);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        fecharArq();
                    } else {
                        JOptionPane.showMessageDialog(null, "Entre com uma opção válida", "Erro", JOptionPane.ERROR_MESSAGE);
                        erroOpcao = true;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Entre com um numero válido", "Erro", JOptionPane.ERROR_MESSAGE);
                    erroOpcao = true;
                }
            }
        } while (erroOpcao);
    }

    private void fecharArq() {
        File arq = new File("clientesBin.str");
        if (arq.exists() == true) {
            try {
                output.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
