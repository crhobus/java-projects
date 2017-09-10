package RandomAccessFile;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

public class EditorArquivos {

    RandomAccessFile file;

    public EditorArquivos(String nomeArq) throws IOException {
        file = new RandomAccessFile(nomeArq, "rw");
    }

    public void fecharArquivo() throws IOException {
        if (file != null) {
            file.close();
        }
    }

    public RandomAccessRegistroConta getRegistro(int numeroConta) throws IllegalArgumentException, NumberFormatException, IOException {
        RandomAccessRegistroConta regConta = new RandomAccessRegistroConta();
        if (numeroConta < 1 || numeroConta > 100) {
            throw new IllegalArgumentException("A entrada deve ser entre 1 e 100");
        }
        file.seek((numeroConta - 1) * RandomAccessRegistroConta.SIZE);
        regConta.ler(file);
        return regConta;
    }

    public void novaConta(int numConta, String nome, String sobrenome, double saldo) throws IllegalArgumentException, IOException {
        RandomAccessRegistroConta regConta = getRegistro(numConta);
        if (regConta.getConta() != 0) {
            throw new IllegalArgumentException("Esta conta ja existe, portanto não será substituida");
        }
        file.seek((numConta - 1) * RandomAccessRegistroConta.SIZE);
        regConta = new RandomAccessRegistroConta(numConta, nome, sobrenome, saldo);
        regConta.escrever(file);
    }

    public void deletarRegistro(int numConta) throws IllegalArgumentException, IOException {
        RandomAccessRegistroConta regConta = getRegistro(numConta);
        if (regConta.getConta() == 0) {
            throw new IllegalArgumentException("Conta inexistente");
        }
        file.seek((numConta - 1) * RandomAccessRegistroConta.SIZE);
        regConta = new RandomAccessRegistroConta();
        regConta.escrever(file);
    }

    public void lerRegistros() {
        RandomAccessRegistroConta regConta = new RandomAccessRegistroConta();
        try {
            file.seek(0);
            System.out.println("Abaixo todos os registros lidos do arquivo");
            while (true) {
                do {
                    regConta.ler(file);
                } while (regConta.getConta() == 0);
                System.out.print("Conta: " + regConta.getConta() + ", " + "Nome: " + regConta.getNome() + ", " + "Sobrenome: " + regConta.getSobrenome() + ", " + "Saldo: " + regConta.getSaldo() + "\n");
            }
        } catch (EOFException ex) {
            return;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}
