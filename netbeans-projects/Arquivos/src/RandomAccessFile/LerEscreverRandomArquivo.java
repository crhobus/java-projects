package RandomAccessFile;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.NoSuchElementException;
import javax.swing.JOptionPane;

public class LerEscreverRandomArquivo {

    private static final int NUMERO_REGISTROS = 100;

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

    public void addRegistros() {
        RandomAccessFile output = null;
        try {
            output = new RandomAccessFile("RandomClientesAux.dat", "rw");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo inexistente", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        RandomAccessRegistroConta regConta = new RandomAccessRegistroConta();
        int numConta = 0;
        String nome;
        String sobrenome;
        double saldo;
        try {
            numConta = getInteger("Entre com o número da conta de 1 a 100");
            nome = getString("Entre com o nome");
            sobrenome = getString("Entre com o sobrenome");
            saldo = getDouble("Entre com o saldo");
            if (numConta > 0 && numConta <= NUMERO_REGISTROS) {
                regConta.setConta(numConta);
                regConta.setNome(nome);
                regConta.setSobrenome(sobrenome);
                regConta.setSaldo(saldo);
                output.seek((numConta - 1) * RandomAccessRegistroConta.SIZE);
                regConta.escrever(output);
            } else {
                JOptionPane.showMessageDialog(null, "A conta deve ser entre 0 e 100", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (NoSuchElementException ex) {
            JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, tente novamente", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public void lerRegistros() {
        RandomAccessFile input = null;
        try {
            input = new RandomAccessFile("RandomClientesAux.dat", "r");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        RandomAccessRegistroConta regConta = new RandomAccessRegistroConta();
        try {
            while (true) {
                do {
                    regConta.ler(input);
                } while (regConta.getConta() == 0);
                System.out.print("Conta: " + regConta.getConta() + ", " + "Nome: " + regConta.getNome() + ", " + "Sobrenome: " + regConta.getSobrenome() + ", " + "Saldo: " + regConta.getSaldo() + "\n");
            }
        } catch (EOFException ex) {
            return;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}
