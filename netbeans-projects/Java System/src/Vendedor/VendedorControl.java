package Vendedor;

import java.util.List;

import DAOFactory.DAOFactory;
import Modelo.Vendedor;

public class VendedorControl {

    private List<Vendedor> listaVendedor;
    private DAOFactory daoFactory;

    public VendedorControl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public boolean setVendedor(Vendedor vendedor) throws Exception {
        if (daoFactory.createVendedorDAO().setVendedor(vendedor)) {
            return true;
        }
        return false;
    }

    public Vendedor getVendedor(String cpf) throws Exception {
        return daoFactory.createVendedorDAO().getVendedor(cpf);
    }

    public boolean removeVendedor(String cpf) throws Exception {
        if (daoFactory.createVendedorDAO().removeVendedor(cpf)) {
            return true;
        }
        return false;
    }

    public boolean arqVenVazio() throws Exception {
        return daoFactory.createVendedorDAO().arqVenVazio();
    }

    public int getProxCodVen() throws Exception {
        return daoFactory.createVendedorDAO().getProxCodVen();
    }

    public int getQtdadeVenCadas() {
        return listaVendedor.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Vendedor vendedor = listaVendedor.get(linha);
        switch (coluna) {
            case 0:
                return vendedor.getCodigo();
            case 1:
                return vendedor.getCpf();
            case 2:
                return vendedor.getEmailVendedor();
            case 3:
                return vendedor.getHomePage();
            case 4:
                return vendedor.getComissao();
            default:
                return vendedor.getSalarioLiquido();
        }
    }

    public void listaTodos() throws Exception {
        listaVendedor = daoFactory.createVendedorDAO().listVendedor();
    }

    public List<Vendedor> getLista() throws Exception {
        return daoFactory.createVendedorDAO().listVendedor();
    }
}
