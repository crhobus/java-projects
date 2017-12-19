package CidadeDAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import DAOFactory.ArqTextoGenerico;
import Modelo.Cidade;

public class CidadeTexto extends ArqTextoGenerico implements CidadeDAO {

    @Override
    public boolean arqCidVazio() throws Exception {
        return arqVazio("Texto/Cidade");
    }

    @Override
    public Cidade getCidade(String cid) throws Exception {
        File diretorio = new File("Texto/Cidade");
        if (diretorio.exists()) {
            File arq = new File(diretorio, cid + ".txt");
            if (arq.exists()) {
                try {
                    return leituraCidade(arq);
                } catch (Exception ex) {
                    throw new Exception("Erro na leitura do arquivo de cidade\no arquivo " + cid + ".txt está corrompido");
                }
            }
        }
        return null;
    }

    private Cidade leituraCidade(File arq) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(arq));
        Cidade cidade = new Cidade();
        cidade.setCodigo(Integer.parseInt(in.readLine()));
        cidade.setCidade(in.readLine());
        cidade.setEstado(in.readLine());
        in.close();
        return cidade;
    }

    @Override
    public int getProxCodCid() throws Exception {
        return getProxCod("Texto/Cidade", "cidade");
    }

    @Override
    public List<Cidade> listCidade() throws Exception {
        List<Cidade> lista = new ArrayList<Cidade>();
        File diretorio = new File("Texto/Cidade");
        if (diretorio.exists()) {
            File[] arqs = diretorio.listFiles();
            try {
                for (int i = 0; i < arqs.length; i++) {
                    lista.add(leituraCidade(new File(diretorio, arqs[i].getName())));
                }
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de cidade\n há um ou mais arquivos corrompidos");
            }
        }
        return lista;
    }

    @Override
    public boolean removeCid(String cid) throws Exception {
        return remove("Texto/Cidade", cid);
    }

    @Override
    public boolean setCidade(Cidade cidade) throws Exception {
        List<Cidade> lista = listCidade();
        for (Cidade cid : lista) {
            if (cid.getCodigo() == cidade.getCodigo()) {
                removeCid(cid.getCidade());
            }
        }
        File diretorio = new File("Texto");
        if (diretorio.mkdir() || diretorio.exists()) {
            File subDiretorio = new File(diretorio, "Cidade");
            if (subDiretorio.mkdir() || subDiretorio.exists()) {
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(subDiretorio, cidade.getCidade() + ".txt"))));
                    out.println(cidade.getCodigo());
                    out.println(cidade.getCidade());
                    out.println(cidade.getEstado());
                    out.close();
                    return true;
                } catch (Exception e) {
                    throw new Exception("Erro na gravação do arquivo de cidade");
                }
            }
        }
        return false;
    }
}
