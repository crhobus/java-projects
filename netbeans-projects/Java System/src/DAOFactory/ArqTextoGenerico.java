package DAOFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ArqTextoGenerico {

    public int getProxCod(String endereco, String nome) throws Exception {
        List<Integer> lista = new ArrayList<Integer>();
        File diretorio = new File(endereco);
        if (diretorio.exists() && !arqVazio(endereco)) {
            try {
                File[] arqs = diretorio.listFiles();
                for (int i = 0; i < arqs.length; i++) {
                    BufferedReader in = new BufferedReader(new FileReader(new File(diretorio, arqs[i].getName())));
                    lista.add(Integer.parseInt(in.readLine()));
                    in.close();
                }
                Collections.sort(lista);
                int y = 1;
                for (int i = 0; i < lista.get(lista.size() - 1); i++) {
                    if (lista.get(i) != y) {
                        return y;
                    }
                    y++;
                }
            } catch (Exception e) {
                throw new Exception("Erro na leitura do arquivo de " + nome);
            }
        }
        return lista.size() + 1;
    }

    public boolean arqVazio(String endereco) throws Exception {
        File diretorio = new File(endereco);
        if (diretorio.exists()) {
            File[] arqs = diretorio.listFiles();
            if (arqs.length > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean remove(String endereco, String nomeArq) throws Exception {
        File arq = new File(endereco, nomeArq + ".txt");
        if (arq.exists()) {
            arq.delete();
            return true;
        }
        return false;
    }
}
