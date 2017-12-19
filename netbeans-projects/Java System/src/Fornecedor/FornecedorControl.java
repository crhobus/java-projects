package Fornecedor;

import java.util.List;

import DAOFactory.DAOFactory;
import Modelo.Fornecedor;

public class FornecedorControl {

    private List<Fornecedor> listaFornecedor;
    private DAOFactory daoFactory;

    public FornecedorControl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public boolean setFornecedor(Fornecedor fornecedor) throws Exception {
        if (daoFactory.createFornecedorDAO().setFornecedor(fornecedor)) {
            return true;
        }
        return false;
    }

    public Fornecedor getFornecedor(String nome) throws Exception {
        return daoFactory.createFornecedorDAO().getFornecedor(nome);
    }

    public boolean removeFornecedor(String nome) throws Exception {
        if (daoFactory.createFornecedorDAO().removeFornecedor(nome)) {
            return true;
        }
        return false;
    }

    public boolean arqFornVazio() throws Exception {
        return daoFactory.createFornecedorDAO().arqFornVazio();
    }

    public int getProxCodForn() throws Exception {
        return daoFactory.createFornecedorDAO().getProxCodForn();
    }

    public int getQtdadeFornCadas() {
        return listaFornecedor.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Fornecedor fornecedor = listaFornecedor.get(linha);
        switch (coluna) {
            case 0:
                return fornecedor.getCodigo();
            case 1:
                return fornecedor.getNome();
            case 2:
                return fornecedor.getInscricaoEstadual();
            case 3:
                return fornecedor.getCnpj();
            case 4:
                return fornecedor.getInscricaoMunicipal();
            case 5:
                return fornecedor.getTipoFornecedor();
            case 6:
                return fornecedor.getEndereco();
            case 7:
                return fornecedor.getBairro();
            case 8:
                return fornecedor.getNumero();
            case 9:
                return fornecedor.getCidade();
            case 10:
                return fornecedor.getEstado();
            case 11:
                return fornecedor.getRegiao();
            case 12:
                return fornecedor.getFone();
            case 13:
                return fornecedor.getCelular1();
            case 14:
                return fornecedor.getEmail();
            case 15:
                return fornecedor.getMsn();
            case 16:
                return fornecedor.getDescricao();
            case 17:
                return fornecedor.getComissao();
            case 18:
                return fornecedor.getCompraMinima();
            case 19:
                return fornecedor.getCompraMaxima();
            case 20:
                return fornecedor.getValorFrete();
            case 21:
                return fornecedor.getIcms();
            case 22:
                return fornecedor.getCofins();
            case 23:
                return fornecedor.getIpi();
            case 24:
                return fornecedor.getJuros();
            default:
                return fornecedor.getDescontos();
        }
    }

    public void listaTodos() throws Exception {
        listaFornecedor = daoFactory.createFornecedorDAO().listFornecedor();
    }

    public List<Fornecedor> getLista() throws Exception {
        return daoFactory.createFornecedorDAO().listFornecedor();
    }
}
