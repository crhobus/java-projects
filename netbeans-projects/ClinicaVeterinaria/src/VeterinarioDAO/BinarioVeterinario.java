package VeterinarioDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import javax.swing.JOptionPane;

import ClinicaVeterinaria.Veterinario;
import DAOFactory.BinarioFactory;
import DAOFactory.LeituraBytes;

public class BinarioVeterinario extends LeituraBytes implements VeterinarioDAO {

    @Override
    public void gravar(Veterinario veterinario) {
        File diretorio = new File("Binario");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Veterinario.dat"), "rw");
                try {
                    arq.seek(veterinario.getId() * 196);
                    arq.writeInt(veterinario.getId());
                    arq.writeLong(veterinario.getCpf());
                    arq.write(Arrays.copyOf(veterinario.getNome().getBytes(), 35));
                    arq.write(Arrays.copyOf(veterinario.getTelefone().getBytes(), 20));
                    arq.write(Arrays.copyOf(veterinario.getEndereco().getBytes(), 45));
                    arq.write(Arrays.copyOf(veterinario.getTitulacao().getBytes(), 40));
                    arq.write(Arrays.copyOf(veterinario.getReferencias().getBytes(), 40));
                    arq.writeInt(veterinario.getConsulta().getId());
                    arq.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo de veterinario", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo de veterinario para gravação", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public Veterinario ler(int id) {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Veterinario.dat"), "rw");
                Veterinario veterinario = new Veterinario();
                try {
                    arq.seek(id * 196);
                    veterinario.setId(arq.readInt());
                    veterinario.setCpf(arq.readLong());
                    veterinario.setNome(new String(Arrays.copyOf(recuperaTam(arq, 35), getTam())));
                    veterinario.setTelefone(new String(Arrays.copyOf(recuperaTam(arq, 20), getTam())));
                    veterinario.setEndereco(new String(Arrays.copyOf(recuperaTam(arq, 45), getTam())));
                    veterinario.setTitulacao(new String(Arrays.copyOf(recuperaTam(arq, 40), getTam())));
                    veterinario.setReferencias(new String(Arrays.copyOf(recuperaTam(arq, 40), getTam())));
                    veterinario.setConsulta(new BinarioFactory().createConsultaDAO().ler(arq.readInt()));
                    arq.close();
                    return veterinario;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao ler um registro no arquivo de veterinario", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo de veterinario para leitura", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
