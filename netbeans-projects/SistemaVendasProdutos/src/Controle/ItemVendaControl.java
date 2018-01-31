package Controle;

import java.util.*;

public class ItemVendaControl {

    private ArrayList<Itens> lista = new ArrayList<Itens>();

    public void adicionar(String nome, String descricao, String modelo, int codProduto, double total) {
        Itens cadas = new Itens();
        cadas.setNome(nome);
        cadas.setDescricao(descricao);
        cadas.setModelo(modelo);
        cadas.setCodProduto(codProduto);
        cadas.setTotal(total);
        lista.add(cadas);

    }

    public int tamanho() {
        return lista.size();
    }

    public Object conteudoLinha(int linha, int coluna) {
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

    public void limparLista() {
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

