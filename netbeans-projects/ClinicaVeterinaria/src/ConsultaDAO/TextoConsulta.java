package ConsultaDAO;

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

import ClinicaVeterinaria.Consulta;
import DAOFactory.TextoFactory;

public class TextoConsulta implements ConsultaDAO {

    @Override
    public void gravar(Consulta consulta) {
        File diretorio = new File("Texto");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            File subDiretorio = new File(diretorio, "Consulta");
            result = subDiretorio.mkdir();
            if (result || subDiretorio.exists()) {
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(subDiretorio, consulta.getId() + ".txt"))));
                    out.println(consulta.getId());
                    out.println(SimpleDateFormat.getInstance().format(consulta.getData()));
                    out.println(consulta.getValor());
                    out.println(consulta.getDescricao());
                    out.println(consulta.getAnimal().getId());
                    if (consulta.getTratamento() != null) {
                        out.println(consulta.getTratamento().getId());
                    } else {
                        out.println("");
                    }
                    out.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo " + consulta.getId() + ".txt do diretorio de texto", "Erro na consulta", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public Consulta ler(int id) {
        File diretorio = new File("Texto/Consulta");
        if (diretorio.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File(diretorio, id + ".txt")));
                try {
                    Consulta consulta = new Consulta();
                    consulta.setId(Integer.parseInt(in.readLine()));
                    try {
                        consulta.setData(SimpleDateFormat.getInstance().parse(in.readLine()));
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo de consulta", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    consulta.setValor(Double.parseDouble(in.readLine()));
                    consulta.setDescricao(in.readLine());
                    consulta.setAnimal(new TextoFactory().createAnimalDAO().ler(Integer.parseInt(in.readLine())));
                    try {
                        consulta.setTratamento(new TextoFactory().createTratamentoDAO().ler(Integer.parseInt(in.readLine())));
                    } catch (NumberFormatException ex) {
                    }
                    in.close();
                    return consulta;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro na leitura de uma linha do arquivo " + id + ".txt do diretorio de texto", "Erro na consulta", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo " + id + ".txt do diretorio de texto", "Erro na consulta", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
