package AnimalDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import javax.swing.JOptionPane;

import ClinicaVeterinaria.Animal;
import DAOFactory.BinarioFactory;
import DAOFactory.LeituraBytes;

public class BinarioAnimal extends LeituraBytes implements AnimalDAO {

    @Override
    public void gravar(Animal animal) {
        File diretorio = new File("Binario");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Animal.dat"), "rw");
                try {
                    arq.seek(animal.getId() * 56);
                    arq.writeInt(animal.getId());
                    arq.write(Arrays.copyOf(animal.getNome().getBytes(), 35));
                    arq.writeInt(animal.getIdade());
                    arq.write(Arrays.copyOf(animal.getSexo().getBytes(), 9));
                    arq.writeInt(animal.getEspecie().getId());
                    arq.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo de animal", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo de animal para gravação", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public Animal ler(int id) {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Animal.dat"), "rw");
                Animal animal = new Animal();
                try {
                    arq.seek(id * 56);
                    animal.setId(arq.readInt());
                    animal.setNome(new String(Arrays.copyOf(recuperaTam(arq, 35), getTam())));
                    animal.setIdade(arq.readInt());
                    animal.setSexo(new String(Arrays.copyOf(recuperaTam(arq, 9), getTam())));
                    animal.setEspecie(new BinarioFactory().createEspecieDAO().ler(arq.readInt()));
                    arq.close();
                    return animal;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao ler um registro no arquivo de animal", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo de especie para animal", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
