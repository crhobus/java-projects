package Fornecedores;

import java.sql.Connection;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import Modelo.Fornecedor;

public class FornecedorControl {

    private List<Fornecedor> listaFornecedores;
    private FornecedorDAO fornecedorDAO;

    public FornecedorControl(Connection con) {
        fornecedorDAO = new FornecedorDAO(con);
    }

    public boolean insertFornecedor(Fornecedor fornecedor) throws Exception {
        return fornecedorDAO.insertFornecedor(fornecedor);
    }

    public boolean updateFornecedor(Fornecedor fornecedor) throws Exception {
        return fornecedorDAO.updateFornecedor(fornecedor);
    }

    public Fornecedor selectFornecedor(String cnpj) throws Exception {
        return fornecedorDAO.selectFornecedor(cnpj);
    }

    public boolean deleteFornecedor(String cnpj) throws Exception {
        return fornecedorDAO.deleteFornecedor(cnpj);
    }

    public boolean isFornVazio() throws Exception {
        return fornecedorDAO.isFornVazio();
    }

    public int getProxCodForn() throws Exception {
        return fornecedorDAO.getProxCodForn();
    }

    public int getQtdadeFornCadas() {
        return listaFornecedores.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Fornecedor fornecedor = listaFornecedores.get(linha);
        switch (coluna) {
            case 0:
                return fornecedor.getCodigo();
            case 1:
                return fornecedor.getNome();
            case 2:
                return fornecedor.getNomeFantasia();
            case 3:
                return fornecedor.getSigla();
            case 4:
                return fornecedor.getEndereco();
            case 5:
                return fornecedor.getBairro();
            case 6:
                return fornecedor.getNumero();
            case 7:
                return fornecedor.getCidade();
            case 8:
                return fornecedor.getUf();
            case 9:
                return fornecedor.getFone();
            case 10:
                return fornecedor.getCelular1();
            case 11:
                return fornecedor.getCnpj();
            case 12:
                return fornecedor.getIe();
            case 13:
                return fornecedor.getEmail();
            case 14:
                if (fornecedor.getCompraMinima() != -1) {
                    return NumberFormat.getCurrencyInstance().format(fornecedor.getCompraMinima());
                }
                return "R$ 0.00";
            case 15:
                if (fornecedor.getCompraMaxima() != -1) {
                    return NumberFormat.getCurrencyInstance().format(fornecedor.getCompraMaxima());
                }
                return "R$ 0.00";
            case 16:
                if (fornecedor.getValorFrete() != -1) {
                    return NumberFormat.getCurrencyInstance().format(fornecedor.getValorFrete());
                }
                return "R$ 0.00";
            default:
                return fornecedor.getJuros();
        }
    }

    public void listarFornecedores() throws Exception {
        listaFornecedores = fornecedorDAO.listFornecedores();
    }

    public void limparLista() {
        listaFornecedores.clear();
    }

    public Fornecedor getListaPosicao(int posicao) {
        return listaFornecedores.get(posicao);
    }

    public void getListaCod(int codigo) {
        for (Fornecedor fornecedor : listaFornecedores) {
            if (codigo == fornecedor.getCodigo()) {
                listaFornecedores.clear();
                listaFornecedores.add(fornecedor);
                return;
            }
        }
    }

    public void getListaNome(String nome) {
        List<Fornecedor> listaAux = new ArrayList<Fornecedor>();
        for (Fornecedor fornecedor : listaFornecedores) {
            if (nome.equalsIgnoreCase(fornecedor.getNome())) {
                listaAux.add(fornecedor);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFornecedores.clear();
            listaFornecedores = listaAux;
        }
    }

    public void getListaNomeFan(String nomeFantasia) {
        List<Fornecedor> listaAux = new ArrayList<Fornecedor>();
        for (Fornecedor fornecedor : listaFornecedores) {
            if (nomeFantasia.equalsIgnoreCase(fornecedor.getNomeFantasia())) {
                listaAux.add(fornecedor);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFornecedores.clear();
            listaFornecedores = listaAux;
        }
    }

    public void getListaSig(String sigla) {
        List<Fornecedor> listaAux = new ArrayList<Fornecedor>();
        for (Fornecedor fornecedor : listaFornecedores) {
            if (sigla.equalsIgnoreCase(fornecedor.getSigla())) {
                listaAux.add(fornecedor);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFornecedores.clear();
            listaFornecedores = listaAux;
        }
    }

    public void getListaEnd(String endereco) {
        List<Fornecedor> listaAux = new ArrayList<Fornecedor>();
        for (Fornecedor fornecedor : listaFornecedores) {
            if (endereco.equalsIgnoreCase(fornecedor.getEndereco())) {
                listaAux.add(fornecedor);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFornecedores.clear();
            listaFornecedores = listaAux;
        }
    }

    public void getListaBai(String bairro) {
        List<Fornecedor> listaAux = new ArrayList<Fornecedor>();
        for (Fornecedor fornecedor : listaFornecedores) {
            if (bairro.equalsIgnoreCase(fornecedor.getBairro())) {
                listaAux.add(fornecedor);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFornecedores.clear();
            listaFornecedores = listaAux;
        }
    }

    public void getListaNum(int numero) {
        List<Fornecedor> listaAux = new ArrayList<Fornecedor>();
        for (Fornecedor fornecedor : listaFornecedores) {
            if (numero == fornecedor.getNumero()) {
                listaAux.add(fornecedor);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFornecedores.clear();
            listaFornecedores = listaAux;
        }
    }

    public void getListaCid(String cidade) {
        List<Fornecedor> listaAux = new ArrayList<Fornecedor>();
        for (Fornecedor fornecedor : listaFornecedores) {
            if (cidade.equalsIgnoreCase(fornecedor.getCidade())) {
                listaAux.add(fornecedor);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFornecedores.clear();
            listaFornecedores = listaAux;
        }
    }

    public void getListaUF(String uf) {
        List<Fornecedor> listaAux = new ArrayList<Fornecedor>();
        for (Fornecedor fornecedor : listaFornecedores) {
            if (uf.equalsIgnoreCase(fornecedor.getUf())) {
                listaAux.add(fornecedor);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFornecedores.clear();
            listaFornecedores = listaAux;
        }
    }

    public void getListaFon(String fone) {
        List<Fornecedor> listaAux = new ArrayList<Fornecedor>();
        for (Fornecedor fornecedor : listaFornecedores) {
            if (fone.equalsIgnoreCase(fornecedor.getFone())) {
                listaAux.add(fornecedor);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFornecedores.clear();
            listaFornecedores = listaAux;
        }
    }

    public void getListaCel(String celular) {
        List<Fornecedor> listaAux = new ArrayList<Fornecedor>();
        for (Fornecedor fornecedor : listaFornecedores) {
            if (celular.equalsIgnoreCase(fornecedor.getCelular1())) {
                listaAux.add(fornecedor);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFornecedores.clear();
            listaFornecedores = listaAux;
        }
    }

    public void getListaCNPJ(String cnpj) {
        for (Fornecedor fornecedor : listaFornecedores) {
            if (cnpj.equalsIgnoreCase(fornecedor.getCnpj())) {
                listaFornecedores.clear();
                listaFornecedores.add(fornecedor);
                return;
            }
        }
    }

    public void getListaIE(String ie) {
        for (Fornecedor fornecedor : listaFornecedores) {
            if (ie.equalsIgnoreCase(fornecedor.getIe())) {
                listaFornecedores.clear();
                listaFornecedores.add(fornecedor);
                return;
            }
        }
    }

    public void getListaMail(String email) {
        for (Fornecedor fornecedor : listaFornecedores) {
            if (email.equalsIgnoreCase(fornecedor.getEmail())) {
                listaFornecedores.clear();
                listaFornecedores.add(fornecedor);
                return;
            }
        }
    }

    public void getListaComMin(double compraMinima) {
        List<Fornecedor> listaAux = new ArrayList<Fornecedor>();
        for (Fornecedor fornecedor : listaFornecedores) {
            if (compraMinima == fornecedor.getCompraMinima()) {
                listaAux.add(fornecedor);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFornecedores.clear();
            listaFornecedores = listaAux;
        }
    }

    public void getListaComMax(double compraMaxima) {
        List<Fornecedor> listaAux = new ArrayList<Fornecedor>();
        for (Fornecedor fornecedor : listaFornecedores) {
            if (compraMaxima == fornecedor.getCompraMaxima()) {
                listaAux.add(fornecedor);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFornecedores.clear();
            listaFornecedores = listaAux;
        }
    }

    public void getListaValFre(double valorfrete) {
        List<Fornecedor> listaAux = new ArrayList<Fornecedor>();
        for (Fornecedor fornecedor : listaFornecedores) {
            if (valorfrete == fornecedor.getValorFrete()) {
                listaAux.add(fornecedor);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFornecedores.clear();
            listaFornecedores = listaAux;
        }
    }

    public void getListaJur(double juros) {
        List<Fornecedor> listaAux = new ArrayList<Fornecedor>();
        for (Fornecedor fornecedor : listaFornecedores) {
            if (juros == fornecedor.getJuros()) {
                listaAux.add(fornecedor);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFornecedores.clear();
            listaFornecedores = listaAux;
        }
    }
}
