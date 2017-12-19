package Transportadora;

import java.util.List;

import DAOFactory.DAOFactory;
import Modelo.Transportadora;

public class TransportadoraControl {

    private List<Transportadora> listaTransportadora;
    private DAOFactory daoFactory;

    public TransportadoraControl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public boolean setTransportadora(Transportadora transportadora) throws Exception {
        if (daoFactory.createTransportadoraDAO().setTransportadora(transportadora)) {
            return true;
        }
        return false;
    }

    public Transportadora getTransportadora(String nome) throws Exception {
        return daoFactory.createTransportadoraDAO().getTransportadora(nome);
    }

    public boolean removeTransportadora(String nome) throws Exception {
        if (daoFactory.createTransportadoraDAO().removeTransportadora(nome)) {
            return true;
        }
        return false;
    }

    public boolean arqTransVazio() throws Exception {
        return daoFactory.createTransportadoraDAO().arqTransVazio();
    }

    public int getProxCodTrans() throws Exception {
        return daoFactory.createTransportadoraDAO().getProxCodTrans();
    }

    public int getQtdadeTransCadas() {
        return listaTransportadora.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Transportadora transportadora = listaTransportadora.get(linha);
        /*switch (coluna) {
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
        }*/
        return null;
    }

    public void listaTodos() throws Exception {
        listaTransportadora = daoFactory.createTransportadoraDAO().listTransportadora();
    }

    public List<Transportadora> getLista() throws Exception {
        return daoFactory.createTransportadoraDAO().listTransportadora();
    }
}
