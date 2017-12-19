package SetorDAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import DAOFactory.ArqTextoGenerico;
import Modelo.Setor;

public class SetorTexto extends ArqTextoGenerico implements SetorDAO {

    @Override
    public boolean arqSetorVazio() throws Exception {
        return arqVazio("Texto/Setor");
    }

    @Override
    public int getProxCodSetor() throws Exception {
        return getProxCod("Texto/Setor", "setor");
    }

    @Override
    public Setor getSetor(String s) throws Exception {
        File diretorio = new File("Texto/Setor");
        if (diretorio.exists()) {
            File arq = new File(diretorio, s + ".txt");
            if (arq.exists()) {
                try {
                    return leituraSetor(arq);
                } catch (Exception ex) {
                    throw new Exception("Erro na leitura do arquivo de setor\no arquivo " + s + ".txt está corrompido");
                }
            }
        }
        return null;
    }

    @Override
    public List<Setor> listSetor() throws Exception {
        List<Setor> lista = new ArrayList<Setor>();
        File diretorio = new File("Texto/Setor");
        if (diretorio.exists()) {
            File[] arqs = diretorio.listFiles();
            try {
                for (int i = 0; i < arqs.length; i++) {
                    lista.add(leituraSetor(new File(diretorio, arqs[i].getName())));
                }
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de setor\n há um ou mais arquivos corrompidos");
            }
        }
        return lista;
    }

    private Setor leituraSetor(File arq) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(arq));
        Setor setor = new Setor();
        setor.setCodigo(Integer.parseInt(in.readLine()));
        setor.setNome(in.readLine());
        in.close();
        return setor;
    }

    @Override
    public boolean removeSetor(String setor) throws Exception {
        return remove("Texto/Setor", setor);
    }

    @Override
    public boolean setSetor(Setor setor) throws Exception {
        List<Setor> lista = listSetor();
        for (Setor set : lista) {
            if (set.getCodigo() == setor.getCodigo()) {
                removeSetor(set.getNome());
            }
        }
        File diretorio = new File("Texto");
        if (diretorio.mkdir() || diretorio.exists()) {
            File subDiretorio = new File(diretorio, "Setor");
            if (subDiretorio.mkdir() || subDiretorio.exists()) {
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(subDiretorio, setor.getNome() + ".txt"))));
                    out.println(setor.getCodigo());
                    out.println(setor.getNome());
                    out.close();
                    return true;
                } catch (Exception e) {
                    throw new Exception("Erro na gravação do arquivo de setor");
                }
            }
        }
        return false;
    }
}
