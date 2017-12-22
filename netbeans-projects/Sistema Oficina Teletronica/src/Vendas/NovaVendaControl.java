package Vendas;

import java.sql.Connection;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Clientes.ClienteControl;
import Modelo.Cliente;
import Modelo.Venda;

public class NovaVendaControl {

    private VendasDAO vendasDAO;
    private List<ConteudoTabela> lista;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private Connection con;

    public NovaVendaControl(Connection con) {
        this.con = con;
        vendasDAO = new VendasDAO(con);
        lista = new ArrayList<ConteudoTabela>();
    }

    public boolean insertVenda(Venda venda) throws Exception {
        return vendasDAO.insertVenda(venda);
    }

    public boolean updateVenda(Venda venda) throws Exception {
        return vendasDAO.updateVenda(venda);
    }

    public Venda selectVenda(int numero) throws Exception {
        return vendasDAO.selectVenda(numero);
    }

    public boolean isVendaVazio() throws Exception {
        return vendasDAO.isVendaVazio();
    }

    public int getProxNumVen() throws Exception {
        return vendasDAO.getProxCodVenda();
    }

    public boolean pagarVenda(int numero, double valorRestante, int parcelasRestante, String situacao) throws Exception {
        return vendasDAO.pagarVenda(numero, valorRestante, parcelasRestante, situacao);
    }

    public List<Venda> listaVendaData(int mes, int ano) throws Exception {
        return vendasDAO.listaVendaData(mes, ano);
    }

    public int getQtdadeVendasCadas() {
        return lista.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        ConteudoTabela conteudo = lista.get(linha);
        switch (coluna) {
            case 0:
                return conteudo.getNumeroVenda();
            case 1:
                return conteudo.getSituacao();
            case 2:
                return conteudo.getTipo();
            case 3:
                return conteudo.getVendedor();
            case 4:
                return formatDate.format(conteudo.getDataEmissao());
            case 5:
                return conteudo.getNomeClie();
            case 6:
                return conteudo.getCpfCnpjClie();
            case 7:
                return conteudo.getEnderecoClie();
            case 8:
                return conteudo.getBairroClie();
            case 9:
                return conteudo.getNumeroClie();
            case 10:
                return conteudo.getCidadeClie();
            case 11:
                return conteudo.getUfClie();
            case 12:
                return conteudo.getFoneClie();
            case 13:
                return conteudo.getCelularClie();
            case 14:
                return NumberFormat.getCurrencyInstance().format(conteudo.getTotal());
            case 15:
                return conteudo.getCondPagto();
            case 16:
                return conteudo.getFormaPagto();
            case 17:
                return NumberFormat.getCurrencyInstance().format(conteudo.getParcelasMes());
            case 18:
                if (conteudo.getJuros() != -1) {
                    return conteudo.getJuros();
                }
                return 0.0;
            case 19:
                return conteudo.getParcelasRes();
            default:
                return NumberFormat.getCurrencyInstance().format(conteudo.getValorRestante());
        }
    }

    public void listaTodos() throws Exception {
        ClienteControl controlClie = new ClienteControl(con);
        List<Venda> listaVendas = vendasDAO.listVendas();
        Cliente cliente;
        lista.clear();
        for (Venda venda : listaVendas) {
            cliente = controlClie.selectCliente(venda.getCpfCnpjClie());
            ConteudoTabela conteudo = new ConteudoTabela();
            conteudo.setNumeroVenda(venda.getNumeroVen());
            conteudo.setNumeroClie(cliente.getNumero());
            conteudo.setParcelasRes(venda.getParcelasRes());
            conteudo.setSituacao(venda.getSituacao());
            conteudo.setNomeClie(cliente.getNome());
            conteudo.setCpfCnpjClie(venda.getCpfCnpjClie());
            conteudo.setEnderecoClie(cliente.getEndereco());
            conteudo.setBairroClie(cliente.getBairro());
            conteudo.setCidadeClie(cliente.getCidade());
            conteudo.setUfClie(cliente.getUf());
            conteudo.setFoneClie(cliente.getFone());
            conteudo.setCelularClie(cliente.getCelular1());
            conteudo.setVendedor(venda.getVendedor());
            conteudo.setTipo(venda.getTipo());
            conteudo.setCondPagto(venda.getCondPagto());
            conteudo.setFormaPagto(venda.getFormaPagto());
            conteudo.setTotal(venda.getTotal());
            conteudo.setParcelasMes(venda.getParcelasMes());
            conteudo.setJuros(venda.getJuros());
            conteudo.setValorRestante(venda.getValorRestante());
            conteudo.setDataEmissao(venda.getDataEmissao());
            lista.add(conteudo);
        }
    }

    public void limparLista() {
        lista.clear();
    }

    public Venda getListaPosicao(int posicao) throws Exception {
        return selectVenda(lista.get(posicao).getNumeroVenda());
    }

    private class ConteudoTabela {

        private int numeroVenda, numeroClie, parcelasRes;
        private String situacao, nomeClie, cpfCnpjClie, enderecoClie,
                BairroClie, cidadeClie, ufClie, foneClie, celularClie,
                vendedor, tipo, condPagto, formaPagto;
        private double total, parcelasMes, juros, valorRestante;
        private Date dataEmissao;

        private int getNumeroVenda() {
            return numeroVenda;
        }

        private void setNumeroVenda(int numeroVenda) {
            this.numeroVenda = numeroVenda;
        }

        private int getNumeroClie() {
            return numeroClie;
        }

        private void setNumeroClie(int numeroClie) {
            this.numeroClie = numeroClie;
        }

        private int getParcelasRes() {
            return parcelasRes;
        }

        private void setParcelasRes(int parcelasRes) {
            this.parcelasRes = parcelasRes;
        }

        private String getSituacao() {
            return situacao;
        }

        private void setSituacao(String situacao) {
            this.situacao = situacao;
        }

        private String getNomeClie() {
            return nomeClie;
        }

        private void setNomeClie(String nomeClie) {
            this.nomeClie = nomeClie;
        }

        private String getCpfCnpjClie() {
            return cpfCnpjClie;
        }

        private void setCpfCnpjClie(String cpfCnpjClie) {
            this.cpfCnpjClie = cpfCnpjClie;
        }

        private String getEnderecoClie() {
            return enderecoClie;
        }

        private void setEnderecoClie(String enderecoClie) {
            this.enderecoClie = enderecoClie;
        }

        private String getBairroClie() {
            return BairroClie;
        }

        private void setBairroClie(String bairroClie) {
            BairroClie = bairroClie;
        }

        private String getCidadeClie() {
            return cidadeClie;
        }

        private void setCidadeClie(String cidadeClie) {
            this.cidadeClie = cidadeClie;
        }

        private String getUfClie() {
            return ufClie;
        }

        private void setUfClie(String ufClie) {
            this.ufClie = ufClie;
        }

        private String getFoneClie() {
            return foneClie;
        }

        private void setFoneClie(String foneClie) {
            this.foneClie = foneClie;
        }

        private String getCelularClie() {
            return celularClie;
        }

        private void setCelularClie(String celularClie) {
            this.celularClie = celularClie;
        }

        private String getVendedor() {
            return vendedor;
        }

        private void setVendedor(String vendedor) {
            this.vendedor = vendedor;
        }

        private String getTipo() {
            return tipo;
        }

        private void setTipo(String tipo) {
            this.tipo = tipo;
        }

        private String getCondPagto() {
            return condPagto;
        }

        private void setCondPagto(String condPagto) {
            this.condPagto = condPagto;
        }

        private String getFormaPagto() {
            return formaPagto;
        }

        private void setFormaPagto(String formaPagto) {
            this.formaPagto = formaPagto;
        }

        private double getTotal() {
            return total;
        }

        private void setTotal(double total) {
            this.total = total;
        }

        private double getParcelasMes() {
            return parcelasMes;
        }

        private void setParcelasMes(double parcelasMes) {
            this.parcelasMes = parcelasMes;
        }

        private double getJuros() {
            return juros;
        }

        private void setJuros(double juros) {
            this.juros = juros;
        }

        private double getValorRestante() {
            return valorRestante;
        }

        private void setValorRestante(double valorRestante) {
            this.valorRestante = valorRestante;
        }

        private Date getDataEmissao() {
            return dataEmissao;
        }

        private void setDataEmissao(Date dataEmissao) {
            this.dataEmissao = dataEmissao;
        }
    }

    public void getListaNumVenda(int numeroVenda) {
        for (ConteudoTabela conteudo : lista) {
            if (numeroVenda == conteudo.getNumeroVenda()) {
                lista.clear();
                lista.add(conteudo);
                return;
            }
        }
    }

    public void getListaSit(String situacao) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (situacao.equalsIgnoreCase(conteudo.getSituacao())) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaTipo(String tipo) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (tipo.equalsIgnoreCase(conteudo.getTipo())) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaVen(String vendedor) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (vendedor.equalsIgnoreCase(conteudo.getVendedor())) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaDataEmi(String dataEmissao) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (dataEmissao.equals(formatDate.format(conteudo.getDataEmissao()))) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaClie(String cliente) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (cliente.equalsIgnoreCase(conteudo.getNomeClie())) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaCPFCNPJ(String cpfCNPJ) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (cpfCNPJ.equalsIgnoreCase(conteudo.getCpfCnpjClie())) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaEnd(String endereco) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (endereco.equalsIgnoreCase(conteudo.getEnderecoClie())) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaBai(String bairro) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (bairro.equalsIgnoreCase(conteudo.getBairroClie())) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaNum(int numero) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (numero == conteudo.getNumeroClie()) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaCid(String cidade) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (cidade.equalsIgnoreCase(conteudo.getCidadeClie())) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaUF(String uf) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (uf.equalsIgnoreCase(conteudo.getUfClie())) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaFone(String fone) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (fone.equalsIgnoreCase(conteudo.getFoneClie())) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaCelu(String celular) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (celular.equalsIgnoreCase(conteudo.getCelularClie())) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaTotal(double total) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (total == conteudo.getTotal()) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaCondPagto(String condPagto) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (condPagto.equalsIgnoreCase(conteudo.getCondPagto())) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaFormaPagto(String formaPagto) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (formaPagto.equalsIgnoreCase(conteudo.getFormaPagto())) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaParcelasMes(double parcelasMes) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (parcelasMes == conteudo.getParcelasMes()) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaJuros(double juros) {
        if (juros == 0) {
            juros = -1;
        }
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (juros == conteudo.getJuros()) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaParcelasRes(double parcelasRes) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (parcelasRes == conteudo.getParcelasRes()) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaValorRes(double valorRes) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (valorRes == conteudo.getValorRestante()) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }
}
