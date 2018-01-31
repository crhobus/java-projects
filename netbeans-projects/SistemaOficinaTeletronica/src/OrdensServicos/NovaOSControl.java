package OrdensServicos;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Clientes.ClienteControl;
import Modelo.Cliente;
import Modelo.OrdemServico;

public class NovaOSControl {

    private OrdemServicoDAO ordemServicoDAO;
    private List<ConteudoTabela> lista;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private Connection con;

    public NovaOSControl(Connection con) {
        this.con = con;
        ordemServicoDAO = new OrdemServicoDAO(con);
        lista = new ArrayList<ConteudoTabela>();
    }

    public boolean insertOrdemServico(OrdemServico ordemServico) throws Exception {
        return ordemServicoDAO.insertOrdemServico(ordemServico);
    }

    public boolean updateOrdemServico(OrdemServico ordemServico) throws Exception {
        return ordemServicoDAO.updateOrdemServico(ordemServico);
    }

    public boolean updateOrdemServicoSituacao(String situacao, int numeroOS) throws Exception {
        return ordemServicoDAO.updateOrdemServicoSituacao(situacao, numeroOS);
    }

    public OrdemServico selectOrdemServico(int numero) throws Exception {
        return ordemServicoDAO.selectOrdemServico(numero);
    }

    public boolean isOSVazio() throws Exception {
        return ordemServicoDAO.isOSVazio();
    }

    public int getProxNumOS() throws Exception {
        return ordemServicoDAO.getProxNumOS();
    }

    public List<OrdemServico> listaOSClie(String cpfCNPJ) throws Exception {
        return ordemServicoDAO.listaOSClie(cpfCNPJ);
    }

    public List<OrdemServico> listaOSData(int mes, int ano, String nomeOPRealizada) throws Exception {
        return ordemServicoDAO.listaOSData(mes, ano, nomeOPRealizada);
    }

    public int getQtdadeOSCadas() {
        return lista.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        ConteudoTabela conteudo = lista.get(linha);
        switch (coluna) {
            case 0:
                return conteudo.getNumeroOS();
            case 1:
                return conteudo.getSituacao();
            case 2:
                return formatDate.format(conteudo.getDataGeracao());
            case 3:
                return conteudo.getNomeClie();
            case 4:
                return conteudo.getCpfCnpjClie();
            case 5:
                return conteudo.getEnderecoClie();
            case 6:
                return conteudo.getBairroClie();
            case 7:
                return conteudo.getNumeroClie();
            case 8:
                return conteudo.getCidadeClie();
            case 9:
                return conteudo.getUfClie();
            case 10:
                return conteudo.getFoneClie();
            case 11:
                return conteudo.getCelularClie();
            case 12:
                return conteudo.getNomeApa();
            case 13:
                return conteudo.getMarcaApa();
            case 14:
                return conteudo.getModeloApa();
            case 15:
                return conteudo.getAssistenciaApa();
            default:
                if (conteudo.isOrcamentoApa()) {
                    return " -";
                }
                return "";
        }
    }

    public void listaTodos() throws Exception {
        ClienteControl controlClie = new ClienteControl(con);
        List<OrdemServico> listaOS = ordemServicoDAO.listOrdensServicos();
        Cliente cliente;
        lista.clear();
        for (OrdemServico ordemServico : listaOS) {
            cliente = controlClie.selectCliente(ordemServico.getCpfCnpjClie());
            ConteudoTabela conteudo = new ConteudoTabela();
            conteudo.setNumeroOS(ordemServico.getNumeroOs());
            conteudo.setNumeroClie(cliente.getNumero());
            conteudo.setSituacao(ordemServico.getSituacao());
            conteudo.setNomeClie(cliente.getNome());
            conteudo.setCpfCnpjClie(ordemServico.getCpfCnpjClie());
            conteudo.setEnderecoClie(cliente.getEndereco());
            conteudo.setBairroClie(cliente.getBairro());
            conteudo.setCidadeClie(cliente.getCidade());
            conteudo.setUfClie(cliente.getUf());
            conteudo.setFoneClie(cliente.getFone());
            conteudo.setCelularClie(cliente.getCelular1());
            conteudo.setNomeApa(ordemServico.getNomeApa());
            conteudo.setMarcaApa(ordemServico.getMarcaApa());
            conteudo.setModeloApa(ordemServico.getModeloApa());
            conteudo.setAssistenciaApa(ordemServico.getAssistenciaApa());
            conteudo.setDataGeracao(ordemServico.getDataGeracao());
            conteudo.setOrcamentoApa(ordemServico.isOrcamentoApa());
            lista.add(conteudo);
        }
    }

    public void limparLista() {
        lista.clear();
    }

    public OrdemServico getListaPosicao(int posicao) throws Exception {
        return selectOrdemServico(lista.get(posicao).getNumeroOS());
    }

    private class ConteudoTabela {

        private int numeroOS, numeroClie;
        private String situacao, nomeClie, cpfCnpjClie, enderecoClie,
                BairroClie, cidadeClie, ufClie, foneClie, celularClie, nomeApa,
                marcaApa, modeloApa, assistenciaApa;
        private Date dataGeracao;
        private boolean orcamentoApa;

        private int getNumeroOS() {
            return numeroOS;
        }

        private void setNumeroOS(int numeroOS) {
            this.numeroOS = numeroOS;
        }

        private int getNumeroClie() {
            return numeroClie;
        }

        private void setNumeroClie(int numeroClie) {
            this.numeroClie = numeroClie;
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

        private String getNomeApa() {
            return nomeApa;
        }

        private void setNomeApa(String nomeApa) {
            this.nomeApa = nomeApa;
        }

        private String getMarcaApa() {
            return marcaApa;
        }

        private void setMarcaApa(String marcaApa) {
            this.marcaApa = marcaApa;
        }

        private String getModeloApa() {
            return modeloApa;
        }

        private void setModeloApa(String modeloApa) {
            this.modeloApa = modeloApa;
        }

        private String getAssistenciaApa() {
            return assistenciaApa;
        }

        private void setAssistenciaApa(String assistenciaApa) {
            this.assistenciaApa = assistenciaApa;
        }

        private Date getDataGeracao() {
            return dataGeracao;
        }

        private void setDataGeracao(Date dataGeracao) {
            this.dataGeracao = dataGeracao;
        }

        private boolean isOrcamentoApa() {
            return orcamentoApa;
        }

        private void setOrcamentoApa(boolean orcamentoApa) {
            this.orcamentoApa = orcamentoApa;
        }
    }

    public void getListaNumOS(int numeroOS) {
        for (ConteudoTabela conteudo : lista) {
            if (numeroOS == conteudo.getNumeroOS()) {
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

    public void getListaDataGer(String dataGeracao) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (dataGeracao.equals(formatDate.format(conteudo.getDataGeracao()))) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaClie(String nomeClie) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (nomeClie.equalsIgnoreCase(conteudo.getNomeClie())) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaCpfCnpj(String cpfCnpj) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (cpfCnpj.equalsIgnoreCase(conteudo.getCpfCnpjClie())) {
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

    public void getListaCel(String celular) {
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

    public void getListaNomeApa(String nomeApa) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (nomeApa.equalsIgnoreCase(conteudo.getNomeApa())) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaMarcaApa(String marcaApa) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (marcaApa.equalsIgnoreCase(conteudo.getMarcaApa())) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaModApa(String modeloApa) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (modeloApa.equalsIgnoreCase(conteudo.getModeloApa())) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaAssi(String assistenciaApa) {
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (assistenciaApa.equalsIgnoreCase(conteudo.getAssistenciaApa())) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }

    public void getListaOrc(String orcamento) {
        if (orcamento.equalsIgnoreCase("T")) {
            orcamento = "true";
        } else {
            if (orcamento.equalsIgnoreCase("F")) {
                orcamento = "false";
            } else {
                return;
            }
        }
        List<ConteudoTabela> listaAux = new ArrayList<ConteudoTabela>();
        for (ConteudoTabela conteudo : lista) {
            if (orcamento.equals(Boolean.toString(conteudo.isOrcamentoApa()))) {
                listaAux.add(conteudo);
            }
        }
        if (!listaAux.isEmpty()) {
            lista.clear();
            lista = listaAux;
        }
    }
}
