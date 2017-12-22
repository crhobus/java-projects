package Controle;

import Arquivos.*;
import Modelo.Pagamentos;
import java.util.*;

public class PagamentosControl {

    private ArrayList<Pagamentos> lista = new ArrayList<Pagamentos>();
    private String nomeClie, dataEmissao, condPagto, parcelasRes, situacao;
    private int codigo, codigoVen;
    private double descontos, total, valorParcelas, valorPago;

    public void adicionar(String nomeClie, String dataEmissao, String condPagto, String parcelasRes, String situacao,
            int codigo, int codigoVen, double descontos, double total, double valorParcelas, double valorPago) {
        Pagamentos cadas = new Pagamentos();
        cadas.setNomeClie(nomeClie);
        cadas.setDataEmissao(dataEmissao);
        cadas.setCondPagto(condPagto);
        cadas.setParcelasRes(parcelasRes);
        cadas.setSituacao(situacao);
        cadas.setCodigo(codigo);
        cadas.setCodigoVen(codigoVen);
        cadas.setDescontos(descontos);
        cadas.setTotal(total);
        cadas.setValorParcelas(valorParcelas);
        cadas.setValorPago(valorPago);
        lista.add(cadas);
    }

    public int tamanho() {
        return lista.size();
    }

    public Object conteudoLinha(int linha, int coluna) {
        Pagamentos cadas = lista.get(linha);
        switch (coluna) {
            case 0:
                return cadas.getCodigo();
            case 1:
                return cadas.getCodigoVen();
            case 2:
                return cadas.getNomeClie();
            case 3:
                return cadas.getDataEmissao();
            case 4:
                return cadas.getTotal();
            case 5:
                return cadas.getDescontos();
            case 6:
                return cadas.getCondPagto();
            case 7:
                return cadas.getValorParcelas();
            case 8:
                return cadas.getValorPago();
            case 9:
                return cadas.getParcelasRes();
            default:
                return cadas.getSituacao();
        }
    }

    public int ultimoCadasCod() {
        int maior = -1;
        if (vazio() == true) {
            maior = 0;
        } else {
            for (int i = 0; i < lista.size(); i++) {
                Pagamentos aux = lista.get(i);
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

    public void removerCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Pagamentos aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                lista.remove(aux);
            }
        }
    }

    public boolean verificaCodigo(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Pagamentos aux = lista.get(i);
            if (codigo == aux.getCodigo()) {
                return true;
            }
        }
        return false;
    }

    public int verificaSituacaoPagamento(String nome) {
        int cont = 0;
        for (int i = 0; i < lista.size(); i++) {
            Pagamentos aux = lista.get(i);
            if (nome.equalsIgnoreCase(aux.getNomeClie())) {
                if ("Pago".equals(aux.getSituacao())) {
                    cont++;
                }
            }
        }
        return cont;
    }

    public boolean verificaCodigoVenda(int codigoVenda) {
        for (int i = 0; i < lista.size(); i++) {
            Pagamentos aux = lista.get(i);
            if (codigoVenda == aux.getCodigoVen()) {
                return true;
            }
        }
        return false;
    }

    public boolean recuperar(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Pagamentos aux = lista.get(i);
            if (codigo == aux.getCodigo()) {
                nomeClie = aux.getNomeClie();
                dataEmissao = aux.getDataEmissao();
                condPagto = aux.getCondPagto();
                parcelasRes = aux.getParcelasRes();
                situacao = aux.getSituacao();
                this.codigo = aux.getCodigo();
                codigoVen = aux.getCodigoVen();
                descontos = aux.getDescontos();
                total = aux.getTotal();
                valorParcelas = aux.getValorParcelas();
                valorPago = aux.getValorPago();
                return true;
            }
        }
        return false;
    }

    public String getParcelasRes() {
        return parcelasRes;
    }

    public String getSituacao() {
        return situacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getCodigoVen() {
        return codigoVen;
    }

    public String getCondPagto() {
        return condPagto;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public double getDescontos() {
        return descontos;
    }

    public String getNomeClie() {
        return nomeClie;
    }

    public double getTotal() {
        return total;
    }

    public double getValorPago() {
        return valorPago;
    }

    public double getValorParcelas() {
        return valorParcelas;
    }

    public boolean recuperarVen(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Pagamentos aux = lista.get(i);
            if (codigo == aux.getCodigoVen()) {
                valorPago = aux.getValorPago();
                parcelasRes = aux.getParcelasRes();
                situacao = aux.getSituacao();
                this.codigo = aux.getCodigo();
                codigoVen = aux.getCodigoVen();
                return true;
            }
        }
        return false;
    }

    public void arquivo(Arquivo arquivo) {
        try {
            arquivo.arquivo(lista, "Pagamentos");
        } catch (Exception ex) {
        }
    }

    public void lerArquivo(LerArquivo lerArquivo) {
        try {
            lista = lerArquivo.lerArquivo("Pagamentos");
        } catch (Exception ex) {
        }
    }
}
