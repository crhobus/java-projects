package Visao.ArquivosDados;

import java.io.*;
import java.util.*;
import javax.swing.table.*;

public class TableModel extends AbstractTableModel {

    private ArrayList<Arquivo> lista = new ArrayList<Arquivo>();

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 6;
    }

    public Object getValueAt(int linha, int coluna) {
        Arquivo arq = lista.get(linha);
        switch (coluna) {
            case 0:
                return arq.arquivo;
            case 1:
                return arq.nome;
            case 2:
                return arq.tamanho;
            case 3:
                return arq.ultimaModficacao;
            case 4:
                return arq.gravacao;
            default:
                return arq.leitura;
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Arquivo";
            case 1:
                return "Nome";
            case 2:
                return "Tamanho Bytes";
            case 3:
                return "Ult. Modificação";
            case 4:
                return "Gravação";
            default:
                return "Leitura";
        }
    }

    public void adicionar(String nome) {
        File f = new File(nome);
        for (File arq : f.listFiles()) {//listFiles() pega todos as pastas e arquivos de uma determinada pasta
            Arquivo arq2 = new Arquivo();
            arq2.arquivo = arq.isDirectory();
            arq2.nome = arq.getName();
            arq2.tamanho = arq.length();
            arq2.ultimaModficacao = new Date(arq.lastModified());
            arq2.gravacao = arq.canWrite();
            arq2.leitura = arq.canRead();
            lista.add(arq2);
        }
    }

    private class Arquivo {

        private boolean arquivo, gravacao, leitura;
        private String nome;
        private long tamanho;
        private Date ultimaModficacao;
    }
}
