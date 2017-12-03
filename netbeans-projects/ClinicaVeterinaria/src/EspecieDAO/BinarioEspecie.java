package EspecieDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import javax.swing.JOptionPane;

import ClinicaVeterinaria.Especie;
import DAOFactory.LeituraBytes;

public class BinarioEspecie extends LeituraBytes implements EspecieDAO {

    @Override
    public void gravar(Especie especie) {
        File diretorio = new File("Binario");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Especie.dat"), "rw");
                try {
                    arq.seek(especie.getId() * 64);
                    arq.writeInt(especie.getId());
                    arq.write(Arrays.copyOf(especie.getDescricao().getBytes(), 60));
                    arq.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo de especie", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo de especie para gravação", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public Especie ler(int id) {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Especie.dat"), "rw");
                Especie especie = new Especie();
                try {
                    arq.seek(id * 64);
                    especie.setId(arq.readInt());
                    especie.setDescricao(new String(Arrays.copyOf(recuperaTam(arq, 60), getTam())));
                    arq.close();
                    return especie;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao ler um registro no arquivo de especie", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo de especie para leitura", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
