package ExameDAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import ClinicaVeterinaria.Exame;

public class TextoExame implements ExameDAO {

    @Override
    public void gravar(Exame exame) {
        File diretorio = new File("Texto");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            File subDiretorio = new File(diretorio, "Exame");
            result = subDiretorio.mkdir();
            if (result || subDiretorio.exists()) {
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(subDiretorio, exame.getId() + ".txt"))));
                    out.println(exame.getId());
                    out.println(exame.getDescricao());
                    out.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo " + exame.getId() + ".txt do diretorio de texto", "Erro no exame", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public Exame ler(int id) {
        File diretorio = new File("Texto/Exame");
        if (diretorio.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File(diretorio, id + ".txt")));
                try {
                    Exame exame = new Exame();
                    exame.setId(Integer.parseInt(in.readLine()));
                    exame.setDescricao(in.readLine());
                    in.close();
                    return exame;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro na leitura de uma linha do arquivo " + id + ".txt do diretorio de texto", "Erro no exame", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo " + id + ".txt do diretorio de texto", "Erro no exame", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
