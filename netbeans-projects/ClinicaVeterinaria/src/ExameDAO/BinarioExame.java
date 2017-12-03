package ExameDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import javax.swing.JOptionPane;

import ClinicaVeterinaria.Exame;
import DAOFactory.LeituraBytes;

public class BinarioExame extends LeituraBytes implements ExameDAO {

    @Override
    public void gravar(Exame exame) {
        File diretorio = new File("Binario");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Exame.dat"), "rw");
                try {
                    arq.seek(exame.getId() * 64);
                    arq.writeInt(exame.getId());
                    arq.write(Arrays.copyOf(exame.getDescricao().getBytes(), 60));
                    arq.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo de exame", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo de exame para gravação", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public Exame ler(int id) {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Exame.dat"), "rw");
                Exame exame = new Exame();
                try {
                    arq.seek(id * 64);
                    exame.setId(arq.readInt());
                    exame.setDescricao(new String(Arrays.copyOf(recuperaTam(arq, 40), getTam())));
                    arq.close();
                    return exame;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao ler um registro no arquivo de exame", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo de exame para leitura", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
