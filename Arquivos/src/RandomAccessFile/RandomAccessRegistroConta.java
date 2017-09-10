package RandomAccessFile;

import TextoFormatter.Conta;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessRegistroConta extends Conta {

    public static final int SIZE = 72;

    public RandomAccessRegistroConta() {
        this(0, "", "", 0.0);
    }

    public RandomAccessRegistroConta(int conta, String nome, String sobrenome, double saldo) {
        super(conta, nome, sobrenome, saldo);
    }

    public void ler(RandomAccessFile file) throws IOException {
        setConta(file.readInt());
        setNome(leituraNome(file));
        setSobrenome(leituraNome(file));
        setSaldo(file.readDouble());
    }

    private String leituraNome(RandomAccessFile file) throws IOException {
        char nome[] = new char[15];
        for (int i = 0; i < nome.length; i++) {
            nome[i] = file.readChar();
        }
        return new String(nome).replace('\0', ' ');
    }

    public void escrever(RandomAccessFile file) throws IOException {
        file.writeInt(getConta());
        escreverNome(file, getNome());
        escreverNome(file, getSobrenome());
        file.writeDouble(getSaldo());
    }

    public void escreverNome(RandomAccessFile file, String nome) throws IOException {
        StringBuffer buffer = null;
        if (nome != null) {
            buffer = new StringBuffer(nome);
        } else {
            buffer = new StringBuffer(15);
        }
        buffer.setLength(15);
        file.writeChars(buffer.toString());
    }
}
