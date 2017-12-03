package TratamentoDAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import ClinicaVeterinaria.Tratamento;
import DAOFactory.TextoFactory;

public class TextoTratamento implements TratamentoDAO {

    @Override
    public void gravar(Tratamento tratamento) {
        File diretorio = new File("Texto");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            File subDiretorio = new File(diretorio, "Tratamento");
            result = subDiretorio.mkdir();
            if (result || subDiretorio.exists()) {
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(subDiretorio, tratamento.getId() + ".txt"))));
                    out.println(tratamento.getId());
                    out.println(SimpleDateFormat.getInstance().format(tratamento.getDataInicio()));
                    out.println(SimpleDateFormat.getInstance().format(tratamento.getDataFim()));
                    out.println(tratamento.getDescricao());
                    out.println(tratamento.getValor());
                    out.println(tratamento.getExame().getId());
                    out.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo " + tratamento.getId() + ".txt do diretorio de texto", "Erro no tratamento", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public Tratamento ler(int id) {
        File diretorio = new File("Texto/Tratamento");
        if (diretorio.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File(diretorio, id + ".txt")));
                try {
                    Tratamento tratamento = new Tratamento();
                    tratamento.setId(Integer.parseInt(in.readLine()));
                    try {
                        tratamento.setDataInicio(SimpleDateFormat.getInstance().parse(in.readLine()));
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo de tratamento", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    try {
                        tratamento.setDataFim(SimpleDateFormat.getInstance().parse(in.readLine()));
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo de tratamento", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    tratamento.setDescricao(in.readLine());
                    tratamento.setValor(Double.parseDouble(in.readLine()));
                    tratamento.setExame(new TextoFactory().createExameDAO().ler(Integer.parseInt(in.readLine())));
                    in.close();
                    return tratamento;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro na leitura de uma linha do arquivo " + id + ".txt do diretorio de texto", "Erro no tratamento", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo " + id + ".txt do diretorio de texto", "Erro no tratamento", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
