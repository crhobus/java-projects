package ConsultaDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.swing.JOptionPane;

import ClinicaVeterinaria.Consulta;
import DAOFactory.BinarioFactory;
import DAOFactory.LeituraBytes;

public class BinarioConsulta extends LeituraBytes implements ConsultaDAO {

    @Override
    public void gravar(Consulta consulta) {
        File diretorio = new File("Binario");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Consulta.dat"), "rw");
                try {
                    arq.seek(consulta.getId() * 100);
                    arq.writeInt(consulta.getId());
                    arq.write(Arrays.copyOf(SimpleDateFormat.getInstance().format(consulta.getData()).getBytes(), 20));
                    arq.writeDouble(consulta.getValor());
                    arq.write(Arrays.copyOf(consulta.getDescricao().getBytes(), 60));
                    arq.writeInt(consulta.getAnimal().getId());
                    if (consulta.getTratamento() != null) {
                        arq.write(Arrays.copyOf(Integer.toString(consulta.getTratamento().getId()).getBytes(), 4));
                    } else {
                        arq.write(Arrays.copyOf("".getBytes(), 4));
                    }
                    arq.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo de consulta", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo de consulta para gravação", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public Consulta ler(int id) {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Consulta.dat"), "rw");
                Consulta consulta = new Consulta();
                try {
                    arq.seek(id * 100);
                    consulta.setId(arq.readInt());
                    try {
                        consulta.setData(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 20), getTam()))));
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo de consulta", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    consulta.setValor(arq.readDouble());
                    consulta.setDescricao(new String(Arrays.copyOf(recuperaTam(arq, 60), getTam())));
                    consulta.setAnimal(new BinarioFactory().createAnimalDAO().ler(arq.readInt()));
                    try {
                        consulta.setTratamento(new BinarioFactory().createTratamentoDAO().ler(Integer.parseInt(new String(Arrays.copyOf(recuperaTam(arq, 4), getTam())))));
                    } catch (NumberFormatException ex) {
                    }
                    arq.close();
                    return consulta;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao ler um registro no arquivo de consulta", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo de consulta para leitura", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
