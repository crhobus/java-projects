package Visao.Vendas;

import Controle.ItemVendaAdiciona;
import java.util.*;
import javax.swing.table.*;

public class TableModelConsulta extends AbstractTableModel {

    private ItemVendaAdiciona controle;
    private ArrayList<Itens> lista = new ArrayList<Itens>();

    public TableModelConsulta(ItemVendaAdiciona controle) {
        this.controle = controle;
    }

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 5;
    }

    public Object getValueAt(int linha, int coluna) {
        Itens cadas = lista.get(linha);
        switch (coluna) {
            case 0:
                return cadas.getCodProduto();
            case 1:
                return cadas.getNome();
            case 2:
                return cadas.getDescricao();
            case 3:
                return cadas.getModelo();
            default:
                return cadas.getTotal();
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Codigo";
            case 1:
                return "Produto";
            case 2:
                return "Descrição";
            case 3:
                return "Modelo";
            default:
                return "Total";
        }
    }

    public void adicionaTabela(int codigo) {
        int i = 1;
        while (i <= controle.tamanho()) {
            if (controle.RecuperarItemVendas(codigo, i) == true) {
                Itens cadas = new Itens();
                cadas.setCodProduto(controle.getCodProduto());
                cadas.setNome(controle.getNome());
                cadas.setDescricao(controle.getDescricao());
                cadas.setModelo(controle.getModelo());
                cadas.setTotal(controle.getTotal());
                lista.add(cadas);
                fireTableDataChanged();
            }
            i = i + 1;
        }
    }

    public void limparTabela() {
        lista.clear();
    }

    private class Itens {

        private String nome, descricao, modelo;
        private int codProduto;
        private double total;

        public int getCodProduto() {
            return codProduto;
        }

        public void setCodProduto(int codProduto) {
            this.codProduto = codProduto;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public String getModelo() {
            return modelo;
        }

        public void setModelo(String modelo) {
            this.modelo = modelo;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }
    }
}

