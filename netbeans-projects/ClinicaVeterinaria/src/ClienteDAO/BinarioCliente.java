package ClienteDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.swing.JOptionPane;

import ClinicaVeterinaria.Cliente;
import DAOFactory.BinarioFactory;
import DAOFactory.LeituraBytes;

public class BinarioCliente extends LeituraBytes implements ClienteDAO {

    @Override
    public void gravar(Cliente cliente) {
        File diretorio = new File("Binario");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Cliente.dat"), "rw");
                try {
                    arq.seek(cliente.getId() * 145);
                    arq.writeInt(cliente.getId());
                    arq.writeLong(cliente.getCpf());
                    arq.write(Arrays.copyOf(cliente.getNome().getBytes(), 35));
                    arq.write(Arrays.copyOf(cliente.getTelefone().getBytes(), 35));
                    arq.write(Arrays.copyOf(cliente.getEndereco().getBytes(), 35));
                    arq.writeInt(cliente.getPontos());
                    arq.write(Arrays.copyOf(SimpleDateFormat.getInstance().format(cliente.getDataUltimaConsulta()).getBytes(), 20));
                    arq.writeInt(cliente.getAnimal().getId());
                    arq.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo de clientes", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo de clientes para gravação", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public Cliente ler(int id) {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Cliente.dat"), "rw");
                Cliente cliente = new Cliente();
                try {
                    arq.seek(id * 145);
                    cliente.setId(arq.readInt());
                    cliente.setCpf(arq.readLong());
                    cliente.setNome(new String(Arrays.copyOf(recuperaTam(arq, 35), getTam())));
                    cliente.setTelefone(new String(Arrays.copyOf(recuperaTam(arq, 35), getTam())));
                    cliente.setEndereco(new String(Arrays.copyOf(recuperaTam(arq, 35), getTam())));
                    cliente.setPontos(arq.readInt());
                    try {
                        cliente.setDataUltimaConsulta(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 20), getTam()))));
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo de clientes", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    cliente.setAnimal(new BinarioFactory().createAnimalDAO().ler(arq.readInt()));
                    arq.close();
                    return cliente;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao ler um registro no arquivo de clientes", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo de clientes para leitura", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
