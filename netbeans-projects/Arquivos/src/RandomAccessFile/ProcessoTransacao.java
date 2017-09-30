package RandomAccessFile;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

public class ProcessoTransacao {

    private EditorArquivos arquivoDados;
    private RandomAccessRegistroConta regConta;
    private MenuOption escolhas[] = {MenuOption.IMPRESSAO, MenuOption.RECUPERAR, MenuOption.NOVO, MenuOption.DELETE, MenuOption.FIM};

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

    private boolean abrirArquivo() {
        try {
            arquivoDados = new EditorArquivos("RandomClientes.dat");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void fecharArquivo() {
        try {
            arquivoDados.fecharArquivo();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void executarMenu(MenuOption menu) {
        int numConta = 0;
        String nome;
        String sobrenome;
        double saldo;
        try {
            switch (menu) {
                case IMPRESSAO:
                    arquivoDados.lerRegistros();
                    break;
                case NOVO:
                    numConta = getInteger("Entre com o número da conta de 1 a 100");
                    nome = getString("Entre com o nome");
                    sobrenome = getString("Entre com o sobrenome");
                    saldo = getDouble("Entre com o saldo");
                    arquivoDados.novaConta(numConta, nome, sobrenome, saldo);
                    break;
                case RECUPERAR:
                    boolean erroRec = true;
                    do {
                        String conta = JOptionPane.showInputDialog(null, "Entre com o número da conta", "Recuperar", JOptionPane.QUESTION_MESSAGE);
                        if (conta == null) {
                            erroRec = false;
                        } else {
                            try {
                                numConta = Integer.parseInt(conta);
                                erroRec = false;
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor tente novamente", "Erro", JOptionPane.ERROR_MESSAGE);
                                erroRec = true;
                            }
                        }
                    } while (erroRec);
                    regConta = arquivoDados.getRegistro(numConta);
                    if (regConta.getConta() == 0) {
                        JOptionPane.showMessageDialog(null, "Conta inexistente", "Erro", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Registros recuperado com sucesso");
                        System.out.print("Conta: " + regConta.getConta() + ", " + "Nome: " + regConta.getNome() + ", " + "Sobrenome: " + regConta.getSobrenome() + ", " + "Saldo: " + regConta.getSaldo() + "\n");
                        System.out.println();
                    }
                    break;
                case DELETE:
                    boolean erroExc = true;
                    do {
                        String conta = JOptionPane.showInputDialog(null, "Entre com o número da conta", "Excluir", JOptionPane.QUESTION_MESSAGE);
                        if (conta == null) {
                            erroExc = false;
                        } else {
                            try {
                                numConta = Integer.parseInt(conta);
                                if (numConta > 0) {
                                    if (numConta <= 100) {
                                        erroExc = false;
                                    } else {
                                        JOptionPane.showMessageDialog(null, "A entrada de ser menor ou igual a 100", "Erro", JOptionPane.ERROR_MESSAGE);
                                        erroExc = true;
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "A entrada deve ser maior que 0", "Erro", JOptionPane.ERROR_MESSAGE);
                                    erroExc = true;
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor tente novamente", "Erro", JOptionPane.ERROR_MESSAGE);
                                erroExc = true;
                            }
                        }
                    } while (erroExc);
                    arquivoDados.deletarRegistro(numConta);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ação inválida", "Erro", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar no arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private MenuOption EntreEscolha() {
        int menuEscolha = 1;
        boolean erro = true;
        do {
            String opcao = JOptionPane.showInputDialog(null, "Entre com a opção: 1 - Lista de contas, 2 - Recuperar uma conta, 3 - Adicionar uma nova conta, 4 - Excluir uma conta, 5 - Programa do Fim", "Escolha", JOptionPane.QUESTION_MESSAGE);
            if (opcao == null) {
                erro = false;
                menuEscolha = 5;
            } else {
                try {
                    menuEscolha = Integer.parseInt(opcao);
                    if (menuEscolha > 0) {
                        if (menuEscolha <= 5) {
                            erro = false;
                        } else {
                            JOptionPane.showMessageDialog(null, "A entrada de ser menor ou igual a 5", "Erro", JOptionPane.ERROR_MESSAGE);
                            erro = true;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "A entrada deve ser maior que 0", "Erro", JOptionPane.ERROR_MESSAGE);
                        erro = true;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor tente novamente", "Erro", JOptionPane.ERROR_MESSAGE);
                    erro = true;
                }
            }
        } while (erro);
        return escolhas[menuEscolha - 1];
    }

    private void processarAux() {
        abrirArquivo();
        MenuOption escolha = EntreEscolha();
        while (escolha != MenuOption.FIM) {
            executarMenu(escolha);
            escolha = EntreEscolha();
        }
        fecharArquivo();
    }

    public void processar() {
        File arq = new File("RandomClientes.dat");
        if (arq.exists() == false) {
            CriarRandomArquivo criar = new CriarRandomArquivo();
            criar.CriarArquivo();
            processarAux();
        } else {
            processarAux();
        }
    }
}
