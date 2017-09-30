package RandomAccessFile;

import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

public class CriarRandomArquivo {

    private static final int NUMERO_REGISTROS = 100;

    public void CriarArquivo() {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("RandomClientes.dat", "rw");
            RandomAccessRegistroConta registroBranco = new RandomAccessRegistroConta();
            for (int i = 0; i < NUMERO_REGISTROS; i++) {
                registroBranco.escrever(file);
            }
            JOptionPane.showMessageDialog(null, "O arquivo RandomClientes.dat foi criado com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao processar o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }
    }
}
