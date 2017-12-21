package JTable;

import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class TableModel extends AbstractTableModel {

    ArrayList<Cadastro> lista = new ArrayList<Cadastro>();

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 5;
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return String.class;
            case 1:
                return Boolean.class;
            case 2:
                return Double.class;
            case 3:
                return Date.class;
            default:
                return ImageIcon.class;
        }
    }

    public Object getValueAt(int linha, int coluna) {
        Cadastro cadas = lista.get(linha);
        switch (coluna) {
            case 0:
                return cadas.nome;
            case 1:
                return cadas.vip;
            case 2:
                return cadas.credito;
            case 3:
                return cadas.data;
            default:
                return new ImageIcon(cadas.getNome() + ".jpg");
        }
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Nome";
            case 1:
                return "VIP";
            case 2:
                return "Credito";
            case 3:
                return "Data";
            default:
                return "Foto";
        }
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        return coluna == 0 || coluna == 2 || coluna == 4;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        Cadastro cadas = lista.get(linha);
        try {
            switch (coluna) {
                case 0:
                    cadas.setNome((String) valor);
                    break;
                case 2:
                    NumberFormat nf = NumberFormat.getCurrencyInstance();
                    cadas.setCredito(nf.parse(valor.toString()).doubleValue());
            }
            fireTableRowsUpdated(linha, linha);
        } catch (Exception ex) {
        }
    }

    public void adicionar(String nome, double credito, boolean vip, Date data) {
        Cadastro cadas = new Cadastro();
        cadas.nome = nome;
        cadas.credito = credito;
        cadas.vip = vip;
        cadas.data = data;
        lista.add(cadas);
    }

    private class Cadastro {

        private String nome;
        private double credito;
        private Date data;
        private boolean vip;

        public double getCredito() {
            return credito;
        }

        public void setCredito(double credito) {
            this.credito = credito;
        }

        public Date getData() {
            return data;
        }

        public void setData(Date data) {
            this.data = data;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public boolean isVip() {
            return vip;
        }

        public void setVip(boolean vip) {
            this.vip = vip;
        }
    }
}
