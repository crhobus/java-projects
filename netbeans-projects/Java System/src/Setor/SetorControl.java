package Setor;

import java.util.List;

import DAOFactory.DAOFactory;
import Modelo.Setor;

public class SetorControl {

    private List<Setor> listaSetor;
    private DAOFactory daoFactory;

    public SetorControl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public boolean setSetor(Setor setor) throws Exception {
        if (daoFactory.createSetorDAO().setSetor(setor)) {
            return true;
        }
        return false;
    }

    public Setor getSetor(String setor) throws Exception {
        return daoFactory.createSetorDAO().getSetor(setor);
    }

    public boolean removeSetor(String setor) throws Exception {
        if (daoFactory.createSetorDAO().removeSetor(setor)) {
            return true;
        }
        return false;
    }

    public boolean arqSetorVazio() throws Exception {
        return daoFactory.createSetorDAO().arqSetorVazio();
    }

    public int getProxCodSetor() throws Exception {
        return daoFactory.createSetorDAO().getProxCodSetor();
    }

    public int getQtdadeSetorCadas() {
        return listaSetor.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Setor setor = listaSetor.get(linha);
        switch (coluna) {
            case 0:
                return setor.getCodigo();
            default:
                return setor.getNome();
        }
    }

    public void listaTodos() throws Exception {
        listaSetor = daoFactory.createSetorDAO().listSetor();
    }

    public List<Setor> getLista() throws Exception {
        return daoFactory.createSetorDAO().listSetor();
    }
}
