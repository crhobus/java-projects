package Controle;

import Arquivos.*;
import Modelo.Produto;
import java.io.*;
import java.util.*;

public class ProdutoControl implements Serializable {

    private ArrayList<Produto> lista = new ArrayList<Produto>();
    private String nome, descricao, modelo;
    private int codigo, quantidade, numeroSerie;
    private double valorUnitario, ipi, descontos, valorTotal;

    public void adicionar(String nome, String descricao, int numeroSerie, String modelo, int codigo,
            int quantidade, double valorUnitario, double ipi, double descontos, double valorTotal) {
        Produto cadas = new Produto();
        cadas.setNome(nome);
        cadas.setDescricao(descricao);
        cadas.setNumeroSerie(numeroSerie);
        cadas.setModelo(modelo);
        cadas.setCodigo(codigo);
        cadas.setQuantidade(quantidade);
        cadas.setValorUnitario(valorUnitario);
        cadas.setIpi(ipi);
        cadas.setDescontos(descontos);
        cadas.setValorTotal(valorTotal);
        lista.add(cadas);
    }

    public int tamanho() {
        return lista.size();
    }

    public int ultimoCadasCod() {
        int maior = -1;
        if (vazio() == true) {
            maior = 0;
        } else {
            for (int i = 0; i < lista.size(); i++) {
                Produto aux = lista.get(i);
                if (aux.getCodigo() > maior) {
                    maior = aux.getCodigo();
                }
            }
        }
        return maior;
    }

    public boolean vazio() {
        if (lista.isEmpty() == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean produtoCadasCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Produto aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public boolean produtoCadasNome(String nome) {
        for (int i = 0; i < lista.size(); i++) {
            Produto aux = lista.get(i);
            if (nome.equalsIgnoreCase(aux.getNome())) {
                codigo = aux.getCodigo();
                return true;
            }
        }
        return false;
    }

    public void removerCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Produto aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                lista.remove(aux);
            }
        }
    }

    public Object conteudoLinha(int linha, int coluna) {
        Produto cadas = lista.get(linha);
        switch (coluna) {
            case 0:
                return cadas.getCodigo();
            case 1:
                return cadas.getNome();
            case 2:
                return cadas.getDescricao();
            case 3:
                return cadas.getNumeroSerie();
            case 4:
                return cadas.getModelo();
            default:
                return cadas.getValorTotal();
        }
    }

    public int quantidadeTotal() {
        int soma = 0;
        for (int i = 0; i < lista.size(); i++) {
            Produto aux = lista.get(i);
            soma = soma + aux.getQuantidade();
        }
        return soma;
    }

    public double valorTotal() {
        double soma = 0;
        for (int i = 0; i < lista.size(); i++) {
            Produto aux = lista.get(i);
            soma = soma + aux.getValorTotal();
        }
        return soma;
    }

    public int quantidadeProd(String nome, String descricao) {
        int qtdade = 0;
        for (int i = 0; i < lista.size(); i++) {
            Produto aux = lista.get(i);
            if (aux.getNome().equalsIgnoreCase(nome) & aux.getDescricao().equalsIgnoreCase(descricao)) {
                qtdade = qtdade + aux.getQuantidade();
            }
        }
        return qtdade;
    }

    public double valorProd(String nome, String descricao) {
        double valor = 0;
        for (int i = 0; i < lista.size(); i++) {
            Produto aux = lista.get(i);
            if (aux.getNome().equalsIgnoreCase(nome) & aux.getDescricao().equalsIgnoreCase(descricao)) {
                valor = valor + aux.getValorTotal();
            }
        }
        return valor;
    }

    public boolean recuperar(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Produto aux = lista.get(i);
            if (codigo == aux.getCodigo()) {
                nome = aux.getNome();
                descricao = aux.getDescricao();
                modelo = aux.getModelo();
                this.codigo = aux.getCodigo();
                quantidade = aux.getQuantidade();
                numeroSerie = aux.getNumeroSerie();
                valorUnitario = aux.getValorUnitario();
                ipi = aux.getIpi();
                descontos = aux.getDescontos();
                valorTotal = aux.getValorTotal();
                return true;
            }
        }
        return false;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getDescontos() {
        return descontos;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getIpi() {
        return ipi;
    }

    public String getModelo() {
        return modelo;
    }

    public String getNome() {
        return nome;
    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void arquivo(Arquivo arquivo) {
        try {
            arquivo.arquivo(lista, "Produto");
        } catch (Exception ex) {
        }
    }

    public void lerArquivo(LerArquivo lerArquivo) {
        try {
            lista = lerArquivo.lerArquivo("Produto");
        } catch (Exception ex) {
        }
    }
}
