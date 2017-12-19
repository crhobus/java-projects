package FormaPersistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import DAOFactory.BinarioFactory;
import DAOFactory.DAOFactory;
import DAOFactory.TextoFactory;

public class FormaPersistencia {

    private final DAOFactory[] daoFactories = {new BinarioFactory(), new TextoFactory()};
    private DAOFactory daoFactory;

    public void criarDAO(int tipo) throws IOException {
        daoFactory = daoFactories[tipo];
        gravar(tipo);
    }

    public DAOFactory getDaoFactory() {
        return daoFactory;
    }

    public void gravar(int tipoArquivo) throws IOException {
        File diretorio = new File("PersistenciaDados");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(diretorio, "TipoDados.txt"))));
            out.println(tipoArquivo);
            out.close();
        }
    }

    public int ler() throws IOException {
        File diretorio = new File("PersistenciaDados");
        if (diretorio.exists()) {
            BufferedReader in = new BufferedReader(new FileReader(new File(diretorio, "TipoDados.txt")));
            int tipo = Integer.parseInt(in.readLine());
            in.close();
            return tipo;
        }
        return -1;
    }
}
