package Exe1JTable;

import java.io.*;
import java.util.*;
import javax.swing.table.*;

public class ListarArquivosTableModel extends AbstractTableModel {

    private ArrayList<Arquivo> arquivo = new ArrayList();

    public int getRowCount() {
        return arquivo.size();
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int row, int col) {
        Arquivo arq = arquivo.get(row);
        switch (col) {
            case 0:
                return arq.arquivo;
            case 1:
                return arq.nome;
            case 2:
                return arq.tamanho;
            default:
                return arq.ultimaModficacao;
        }
    }

    @Override//Override sobrescreve o metodo padrão do AbstractTableModel
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Arquivo";
            case 1:
                return "Nome";
            case 2:
                return "Tamanho";
            default:
                return "Ult. Modif.";
        }
    }

    public void atualizar(String nomePasta) {
        arquivo.clear();
        File f = new File(nomePasta);
        for (File arq : f.listFiles()) {//listFiles() pega todos as pastas e arquivos de uma determinada pasta
            Arquivo arq2 = new Arquivo();
            arq2.arquivo = arq.isDirectory();
            arq2.nome = arq.getName();
            arq2.tamanho = arq.length();
            arq2.ultimaModficacao = new Date(arq.lastModified());
            arquivo.add(arq2);
        }
    }

    private class Arquivo {

        private boolean arquivo;
        private String nome;
        private long tamanho;
        private Date ultimaModficacao;
    }
}
