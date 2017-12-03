package VeterinarioDAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import ClinicaVeterinaria.Veterinario;
import DAOFactory.TextoFactory;

public class TextoVeterinario implements VeterinarioDAO {

    @Override
    public void gravar(Veterinario veterinario) {
        File diretorio = new File("Texto");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            File subDiretorio = new File(diretorio, "Veterinario");
            result = subDiretorio.mkdir();
            if (result || subDiretorio.exists()) {
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(subDiretorio, veterinario.getId() + ".txt"))));
                    out.println(veterinario.getId());
                    out.println(veterinario.getCpf());
                    out.println(veterinario.getNome());
                    out.println(veterinario.getTelefone());
                    out.println(veterinario.getEndereco());
                    out.println(veterinario.getTitulacao());
                    out.println(veterinario.getReferencias());
                    out.println(veterinario.getConsulta().getId());
                    out.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo " + veterinario.getId() + ".txt do diretorio de texto", "Erro no veterinario", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public Veterinario ler(int id) {
        File diretorio = new File("Texto/Veterinario");
        if (diretorio.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File(diretorio, id + ".txt")));
                try {
                    Veterinario veterinario = new Veterinario();
                    veterinario.setId(Integer.parseInt(in.readLine()));
                    veterinario.setCpf(Long.parseLong(in.readLine()));
                    veterinario.setNome(in.readLine());
                    veterinario.setTelefone(in.readLine());
                    veterinario.setEndereco(in.readLine());
                    veterinario.setTitulacao(in.readLine());
                    veterinario.setReferencias(in.readLine());
                    veterinario.setConsulta(new TextoFactory().createConsultaDAO().ler(Integer.parseInt(in.readLine())));
                    in.close();
                    return veterinario;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro na leitura de uma linha do arquivo " + id + ".txt do diretorio de texto", "Erro no veterinario", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo " + id + ".txt do diretorio de texto", "Erro no veterinario", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
