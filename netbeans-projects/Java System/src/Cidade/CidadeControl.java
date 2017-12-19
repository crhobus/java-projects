package Cidade;

import java.util.List;

import DAOFactory.DAOFactory;
import Modelo.Cidade;

public class CidadeControl {

    private List<Cidade> listaCidade;
    private DAOFactory daoFactory;

    public CidadeControl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public boolean setCidade(Cidade cidade) throws Exception {
        if (daoFactory.createCidadeDAO().setCidade(cidade)) {
            return true;
        }
        return false;
    }

    public Cidade getCidade(String cidade) throws Exception {
        return daoFactory.createCidadeDAO().getCidade(cidade);
    }

    public boolean removeCid(String cidade) throws Exception {
        if (daoFactory.createCidadeDAO().removeCid(cidade)) {
            return true;
        }
        return false;
    }

    public boolean arqCidVazio() throws Exception {
        return daoFactory.createCidadeDAO().arqCidVazio();
    }

    public int getProxCodCid() throws Exception {
        return daoFactory.createCidadeDAO().getProxCodCid();
    }

    public int getQtdadeCidCadas() {
        return listaCidade.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Cidade cidade = listaCidade.get(linha);
        switch (coluna) {
            case 0:
                return cidade.getCodigo();
            case 1:
                return cidade.getCidade();
            default:
                return cidade.getEstado();
        }
    }

    public void listaTodos() throws Exception {
        listaCidade = daoFactory.createCidadeDAO().listCidade();
    }

    public List<Cidade> getLista() throws Exception {
        return daoFactory.createCidadeDAO().listCidade();
    }
}
