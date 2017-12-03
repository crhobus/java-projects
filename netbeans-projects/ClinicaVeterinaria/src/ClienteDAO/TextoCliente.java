package ClienteDAO;

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

import ClinicaVeterinaria.Cliente;
import DAOFactory.TextoFactory;

public class TextoCliente implements ClienteDAO {

    @Override
    public void gravar(Cliente cliente) {
        File diretorio = new File("Texto");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            File subDiretorio = new File(diretorio, "Cliente");
            result = subDiretorio.mkdir();
            if (result || subDiretorio.exists()) {
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(subDiretorio, cliente.getId() + ".txt"))));
                    out.println(cliente.getId());
                    out.println(cliente.getCpf());
                    out.println(cliente.getNome());
                    out.println(cliente.getTelefone());
                    out.println(cliente.getEndereco());
                    out.println(cliente.getPontos());
                    out.println(SimpleDateFormat.getInstance().format(cliente.getDataUltimaConsulta()));
                    out.println(cliente.getAnimal().getId());
                    out.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo " + cliente.getId() + ".txt do diretorio de texto", "Erro no cliente", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public Cliente ler(int id) {
        File diretorio = new File("Texto/Cliente");
        if (diretorio.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File(diretorio, id + ".txt")));
                try {
                    Cliente cliente = new Cliente();
                    cliente.setId(Integer.parseInt(in.readLine()));
                    cliente.setCpf(Long.parseLong(in.readLine()));
                    cliente.setNome(in.readLine());
                    cliente.setTelefone(in.readLine());
                    cliente.setEndereco(in.readLine());
                    cliente.setPontos(Integer.parseInt(in.readLine()));
                    try {
                        cliente.setDataUltimaConsulta(SimpleDateFormat.getInstance().parse(in.readLine()));
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo de clientes", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    cliente.setAnimal(new TextoFactory().createAnimalDAO().ler(Integer.parseInt(in.readLine())));
                    in.close();
                    return cliente;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro na leitura de uma linha do arquivo " + id + ".txt do diretorio de texto", "Erro no cliente", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo " + id + ".txt do diretorio de texto", "Erro no cliente", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
