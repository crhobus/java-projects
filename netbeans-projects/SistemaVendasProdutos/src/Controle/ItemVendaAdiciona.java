package Controle;

import Arquivos.*;
import Modelo.ItemVenda;
import java.util.*;

public class ItemVendaAdiciona {

    private ArrayList<ItemVenda> lista = new ArrayList<ItemVenda>();
    private String nome, descricao, modelo;
    private int codItem, codVenda, codProduto, codigoAux;
    private double total;

    public void adicionar(String nome, String descricao, String modelo, int codItem, int codVenda,
            int codProduto, double total, int codigoAux) {
        ItemVenda cadas = new ItemVenda();
        cadas.setNome(nome);
        cadas.setDescricao(descricao);
        cadas.setModelo(modelo);
        cadas.setCodItem(codItem);
        cadas.setCodVenda(codVenda);
        cadas.setCodProduto(codProduto);
        cadas.setTotal(total);
        cadas.setCodigoAux(codigoAux);
        lista.add(cadas);
    }

    public int tamanho() {
        return lista.size();
    }

    public Object conteudoLinha(int linha, int coluna) {
        ItemVenda cadas = lista.get(linha);
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

    public int ultimoCodigoAuxCadas() {
        int maior = -1;
        if (lista.isEmpty() == true) {
            maior = 0;
        } else {
            for (int i = 0; i < lista.size(); i++) {
                ItemVenda aux = lista.get(i);
                if (aux.getCodigoAux() > maior) {
                    maior = aux.getCodigoAux();
                }
            }
        }
        return maior;
    }

    public void arquivo(Arquivo arquivo) {
        try {
            arquivo.arquivo(lista, "ItemVenda");
        } catch (Exception ex) {
        }
    }

    public void lerArquivo(LerArquivo lerArquivo) {
        try {
            lista = lerArquivo.lerArquivo("ItemVenda");
        } catch (Exception ex) {
        }
    }

    public boolean RecuperarItemVendas(int codigo, int codigoAux) {
        for (int i = 0; i < lista.size(); i++) {
            ItemVenda aux = lista.get(i);
            if (codigoAux == aux.getCodigoAux()) {
                if (aux.getCodItem() == aux.getCodVenda()) {
                    if (codigo == aux.getCodVenda()) {
                        this.codProduto = aux.getCodProduto();
                        nome = aux.getNome();
                        descricao = aux.getDescricao();
                        modelo = aux.getModelo();
                        total = aux.getTotal();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getCodItem() {
        return codItem;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public int getCodVenda() {
        return codVenda;
    }

    public int getCodigoAux() {
        return codigoAux;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getModelo() {
        return modelo;
    }

    public String getNome() {
        return nome;
    }

    public double getTotal() {
        return total;
    }
}
