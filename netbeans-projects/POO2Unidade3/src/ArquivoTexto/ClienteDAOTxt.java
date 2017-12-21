package ArquivoTexto;

import java.io.*;
import java.text.*;
import java.util.*;

public class ClienteDAOTxt {

    private String nomeArq;
    private SimpleDateFormat sDataf = new SimpleDateFormat("dd/MM/yyyy");

    public ClienteDAOTxt(String nomeArq) {
        this.nomeArq = nomeArq;
    }

    public Cliente ler(int linha) throws Exception {
        String s;
        Cliente cliente = null;
        try {
            FileReader lerArquivo = new FileReader(nomeArq);
            BufferedReader ler = new BufferedReader(lerArquivo);
            int i = 0;
            do {
                s = ler.readLine();
                if (s == null) {
                    break;
                } else if (i == linha) {
                    cliente = new Cliente();
                    String vetor[] = s.split("#");
                    cliente.setNome(vetor[0]);
                    cliente.setCredito(Double.parseDouble(vetor[1]));
                    Date data = sDataf.parse(vetor[2]);
                    cliente.setDataNasc(data);
                    ler.close();
                    return cliente;
                }
                i++;
            } while (true);
            ler.close();
        } catch (Exception ex) {
            throw new Exception("Erro na leitura do arquivo");
        }
        return cliente;
    }

    public void gravar(Cliente cliente) throws Exception {
        try {
            FileWriter arquivo = new FileWriter(nomeArq, true);
            PrintWriter escreve = new PrintWriter(arquivo);
            escreve.write(cliente.getNome() + "#");
            escreve.write(Double.toString(cliente.getCredito()) + "#");
            String data = sDataf.format(cliente.getDataNasc());
            escreve.println(data);
            escreve.close();
        } catch (Exception ex) {
            throw new Exception("Erro na criação do arquivo");
        }
    }

    public ArrayList<Cliente> listarTodos() throws Exception {
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        Cliente cliente;
        int i = 0;
        while ((cliente = ler(i)) != null) {
            lista.add(cliente);
            i++;
        }
        return lista;
    }
}
