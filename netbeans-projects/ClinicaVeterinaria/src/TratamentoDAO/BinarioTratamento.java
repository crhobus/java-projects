package TratamentoDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.swing.JOptionPane;

import ClinicaVeterinaria.Tratamento;
import DAOFactory.BinarioFactory;
import DAOFactory.LeituraBytes;

public class BinarioTratamento extends LeituraBytes implements TratamentoDAO {

    @Override
    public void gravar(Tratamento tratamento) {
        File diretorio = new File("Binario");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Tratamento.dat"), "rw");
                try {
                    arq.seek(tratamento.getId() * 116);
                    arq.writeInt(tratamento.getId());
                    arq.write(Arrays.copyOf(SimpleDateFormat.getInstance().format(tratamento.getDataInicio()).getBytes(), 20));
                    arq.write(Arrays.copyOf(SimpleDateFormat.getInstance().format(tratamento.getDataFim()).getBytes(), 20));
                    arq.write(Arrays.copyOf(tratamento.getDescricao().getBytes(), 60));
                    arq.writeDouble(tratamento.getValor());
                    arq.writeInt(tratamento.getExame().getId());
                    arq.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo de tratamento", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo de tratamento para gravação", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public Tratamento ler(int id) {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Tratamento.dat"), "rw");
                Tratamento tratamento = new Tratamento();
                try {
                    arq.seek(id * 116);
                    tratamento.setId(arq.readInt());
                    try {
                        tratamento.setDataInicio(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 20), getTam()))));
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo de tratamento", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    try {
                        tratamento.setDataFim(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 20), getTam()))));
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo de tratamento", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    tratamento.setDescricao(new String(Arrays.copyOf(recuperaTam(arq, 60), getTam())));
                    tratamento.setValor(arq.readDouble());
                    tratamento.setExame(new BinarioFactory().createExameDAO().ler(arq.readInt()));
                    arq.close();
                    return tratamento;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao ler um registro no arquivo de tratamento", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo de tratamento para leitura", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
