package AnimalDAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import ClinicaVeterinaria.Animal;
import DAOFactory.TextoFactory;

public class TextoAnimal implements AnimalDAO {

    @Override
    public void gravar(Animal animal) {
        File diretorio = new File("Texto");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            File subDiretorio = new File(diretorio, "Animal");
            result = subDiretorio.mkdir();
            if (result || subDiretorio.exists()) {
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(subDiretorio, animal.getId() + ".txt"))));
                    out.println(animal.getId());
                    out.println(animal.getNome());
                    out.println(animal.getIdade());
                    out.println(animal.getSexo());
                    out.println(animal.getEspecie().getId());
                    out.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo " + animal.getId() + ".txt do diretorio de texto", "Erro no animal", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public Animal ler(int id) {
        File diretorio = new File("Texto/Animal");
        if (diretorio.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File(diretorio, id + ".txt")));
                try {
                    Animal animal = new Animal();
                    animal.setId(Integer.parseInt(in.readLine()));
                    animal.setNome(in.readLine());
                    animal.setIdade(Integer.parseInt(in.readLine()));
                    animal.setSexo(in.readLine());
                    animal.setEspecie(new TextoFactory().createEspecieDAO().ler(Integer.parseInt(in.readLine())));
                    in.close();
                    return animal;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro na leitura de uma linha do arquivo " + id + ".txt do diretorio de texto", "Erro no animal", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo " + id + ".txt do diretorio de texto", "Erro no animal", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
