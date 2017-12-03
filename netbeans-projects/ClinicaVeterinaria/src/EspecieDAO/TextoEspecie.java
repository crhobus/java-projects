package EspecieDAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import ClinicaVeterinaria.Especie;

public class TextoEspecie implements EspecieDAO {

    @Override
    public void gravar(Especie especie) {
        File diretorio = new File("Texto");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            File subDiretorio = new File(diretorio, "Especie");
            result = subDiretorio.mkdir();
            if (result || subDiretorio.exists()) {
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(subDiretorio, especie.getId() + ".txt"))));
                    out.println(especie.getId());
                    out.println(especie.getDescricao());
                    out.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo " + especie.getId() + ".txt do diretorio de texto", "Erro na espécie", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public Especie ler(int id) {
        File diretorio = new File("Texto/Especie");
        if (diretorio.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File(diretorio, id + ".txt")));
                try {
                    Especie especie = new Especie();
                    especie.setId(Integer.parseInt(in.readLine()));
                    especie.setDescricao(in.readLine());
                    in.close();
                    return especie;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro na leitura de uma linha do arquivo " + id + ".txt do diretorio de texto", "Erro na especie", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo " + id + ".txt do diretorio de texto", "Erro na especie", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
