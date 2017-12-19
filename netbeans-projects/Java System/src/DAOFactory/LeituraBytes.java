package DAOFactory;

import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JOptionPane;

public class LeituraBytes {

    private int tam;

    public byte[] recuperaTam(RandomAccessFile arq, int i) {
        byte[] nome = new byte[i];
        try {
            arq.read(nome);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro na leitura do registro", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tam = i;
        for (int y = 0; y < nome.length; y++) {
            if (nome[y] == 0) {
                tam = y;
                break;
            }
        }
        return nome;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }
}
