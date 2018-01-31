package Controle;

import Arquivos.*;
import Modelo.Venda;
import java.util.*;

public class VendasControl {

    private ArrayList<Venda> lista = new ArrayList<Venda>();
    private String dataEmissao, situacao, condPagto, formaPagto, descricao, endedrecoEnt, bairroEnt, cidadeEnt, estadoEnt, contatoEnt;
    private int codigo, codigoVendedor, codigoCliente, codigoItemVendas, numeroEnt;
    private double subTotal, descontos, total;

    public void adicionar(String dataEmissao, String situacao, String condPagto, String formaPagto, String descricao,
            String endedrecoEnt, String bairroEnt, String cidadeEnt, String estadoEnt, String contatoEnt, int codigo,
            int codigoVendedor, int codigoCliente, int codigoItemVendas, int numeroEnt, double subTotal, double descontos, double total) {
        Venda cadas = new Venda();
        cadas.setDataEmissao(dataEmissao);
        cadas.setSituacao(situacao);
        cadas.setCondPagto(condPagto);
        cadas.setFormaPagto(formaPagto);
        cadas.setDescricao(descricao);
        cadas.setEndedrecoEnt(endedrecoEnt);
        cadas.setBairroEnt(bairroEnt);
        cadas.setCidadeEnt(cidadeEnt);
        cadas.setEstadoEnt(estadoEnt);
        cadas.setContatoEnt(contatoEnt);
        cadas.setCodigo(codigo);
        cadas.setCodigoVendedor(codigoVendedor);
        cadas.setCodigoCliente(codigoCliente);
        cadas.setCodigoItemVendas(codigoItemVendas);
        cadas.setNumeroEnt(numeroEnt);
        cadas.setSubTotal(subTotal);
        cadas.setDescontos(descontos);
        cadas.setTotal(total);
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
                Venda aux = lista.get(i);
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

    public boolean vendasCadasCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Venda aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public boolean vendasCadasCodPag(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Venda aux = lista.get(i);
            if (codigo == (aux.getCodigoCliente())) {
                return true;
            }
        }
        return false;
    }

    public int verificaSituacaoClie(int codigo) {
        int cont = 0;
        for (int i = 0; i < lista.size(); i++) {
            Venda aux = lista.get(i);
            if (codigo == (aux.getCodigoCliente())) {
                if ("Aberto".equals(aux.getSituacao())) {
                    cont++;
                }
            }
        }
        return cont;
    }

    public boolean recuperar(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Venda aux = lista.get(i);
            if (codigo == aux.getCodigo()) {
                dataEmissao = aux.getDataEmissao();
                situacao = aux.getSituacao();
                condPagto = aux.getCondPagto();
                formaPagto = aux.getFormaPagto();
                descricao = aux.getDescricao();
                endedrecoEnt = aux.getEndedrecoEnt();
                bairroEnt = aux.getBairroEnt();
                cidadeEnt = aux.getCidadeEnt();
                estadoEnt = aux.getEstadoEnt();
                contatoEnt = aux.getContatoEnt();
                this.codigo = aux.getCodigo();
                codigoVendedor = aux.getCodigoVendedor();
                codigoCliente = aux.getCodigoCliente();
                codigoItemVendas = aux.getCodigoItemVendas();
                numeroEnt = aux.getNumeroEnt();
                subTotal = aux.getSubTotal();
                descontos = aux.getDescontos();
                total = aux.getTotal();
                return true;
            }
        }
        return false;
    }

    public String getBairroEnt() {
        return bairroEnt;
    }

    public String getCidadeEnt() {
        return cidadeEnt;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public int getCodigoItemVendas() {
        return codigoItemVendas;
    }

    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    public String getCondPagto() {
        return condPagto;
    }

    public String getContatoEnt() {
        return contatoEnt;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public double getDescontos() {
        return descontos;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEndedrecoEnt() {
        return endedrecoEnt;
    }

    public String getEstadoEnt() {
        return estadoEnt;
    }

    public String getFormaPagto() {
        return formaPagto;
    }

    public int getNumeroEnt() {
        return numeroEnt;
    }

    public String getSituacao() {
        return situacao;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getTotal() {
        return total;
    }

    public void arquivo(Arquivo arquivo) {
        try {
            arquivo.arquivo(lista, "Vendas");
        } catch (Exception ex) {
        }
    }

    public void lerArquivo(LerArquivo lerArquivo) {
        try {
            lista = lerArquivo.lerArquivo("Vendas");
        } catch (Exception ex) {
        }
    }
}
