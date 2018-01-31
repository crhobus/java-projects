package Veiculos;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import DAOFactory.DAOFactory;
import Modelo.Veiculo;

public class VeiculoControl {

    private List<Veiculo> listaVeiculo;
    private DAOFactory daoFactory;
    private Connection con;

    public VeiculoControl(DAOFactory daoFactory, Connection con) {
        this.daoFactory = daoFactory;
        this.con = con;
    }

    public boolean setVeiculo(Veiculo veiculo) throws Exception {
        if (daoFactory.createVeiculosDAO().setVeiculo(veiculo, con)) {
            return true;
        }
        return false;
    }

    public Veiculo getVeiculo(String placa) throws Exception {
        return daoFactory.createVeiculosDAO().getVeiculo(placa, con);
    }

    public boolean removeVeiculo(String placa) throws Exception {
        if (daoFactory.createVeiculosDAO().removeVeiculo(placa, con)) {
            return true;
        }
        return false;
    }

    public boolean isVeiVazio() throws Exception {
        return daoFactory.createVeiculosDAO().isVeiVazio(con);
    }

    public int getProxCodVei() throws Exception {
        return daoFactory.createVeiculosDAO().getProxCodVei(con);
    }

    public int getQtdadeVeiCadas() {
        return listaVeiculo.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Veiculo veiculo = listaVeiculo.get(linha);
        switch (coluna) {
            case 0:
                return veiculo.getCodigo();
            case 1:
                return veiculo.getNome();
            case 2:
                return veiculo.getAno();
            case 3:
                return veiculo.getModelo();
            case 4:
                return veiculo.getCor();
            case 5:
                return veiculo.getCombustivel();
            case 6:
                return veiculo.getPotencia();
            case 7:
                return veiculo.getVavulas();
            case 8:
                return veiculo.getCilindros();
            case 9:
                return veiculo.getPlaca();
            case 10:
                return veiculo.getChassi();
            case 11:
                return veiculo.getRenavam();
            case 12:
                return veiculo.getTipo();
            default:
                return veiculo.getCpfCNPJCliente();
        }
    }

    public void listaTodos() throws Exception {
        listaVeiculo = daoFactory.createVeiculosDAO().listVeiculos(con);
    }

    public void limparLista() {
        listaVeiculo.clear();
    }

    public void getListaVeiCod(int codigo) {
        for (Veiculo veiculo : listaVeiculo) {
            if (codigo == veiculo.getCodigo()) {
                listaVeiculo.clear();
                listaVeiculo.add(veiculo);
                return;
            }
        }
    }

    public void getListaVeiNome(String nome) {
        List<Veiculo> listaAux = new ArrayList<Veiculo>();
        for (Veiculo veiculo : listaVeiculo) {
            if (nome.equalsIgnoreCase(veiculo.getNome())) {
                listaAux.add(veiculo);
            }
        }
        if (!listaAux.isEmpty()) {
            listaVeiculo.clear();
            listaVeiculo = listaAux;
        }
    }

    public void getListaVeiAno(int ano) {
        List<Veiculo> listaAux = new ArrayList<Veiculo>();
        for (Veiculo veiculo : listaVeiculo) {
            if (ano == veiculo.getAno()) {
                listaAux.add(veiculo);
            }
        }
        if (!listaAux.isEmpty()) {
            listaVeiculo.clear();
            listaVeiculo = listaAux;
        }
    }

    public void getListaVeiMod(int modelo) {
        List<Veiculo> listaAux = new ArrayList<Veiculo>();
        for (Veiculo veiculo : listaVeiculo) {
            if (modelo == veiculo.getModelo()) {
                listaAux.add(veiculo);
            }
        }
        if (!listaAux.isEmpty()) {
            listaVeiculo.clear();
            listaVeiculo = listaAux;
        }
    }

    public void getListaVeiCor(String cor) {
        List<Veiculo> listaAux = new ArrayList<Veiculo>();
        for (Veiculo veiculo : listaVeiculo) {
            if (cor.equalsIgnoreCase(veiculo.getCor())) {
                listaAux.add(veiculo);
            }
        }
        if (!listaAux.isEmpty()) {
            listaVeiculo.clear();
            listaVeiculo = listaAux;
        }
    }

    public void getListaVeiCom(String combustivel) {
        List<Veiculo> listaAux = new ArrayList<Veiculo>();
        for (Veiculo veiculo : listaVeiculo) {
            if (combustivel.equalsIgnoreCase(veiculo.getCombustivel())) {
                listaAux.add(veiculo);
            }
        }
        if (!listaAux.isEmpty()) {
            listaVeiculo.clear();
            listaVeiculo = listaAux;
        }
    }

    public void getListaVeiPot(double potencia) {
        List<Veiculo> listaAux = new ArrayList<Veiculo>();
        for (Veiculo veiculo : listaVeiculo) {
            if (potencia == veiculo.getPotencia()) {
                listaAux.add(veiculo);
            }
        }
        if (!listaAux.isEmpty()) {
            listaVeiculo.clear();
            listaVeiculo = listaAux;
        }
    }

    public void getListaVeiVal(int vavulas) {
        List<Veiculo> listaAux = new ArrayList<Veiculo>();
        for (Veiculo veiculo : listaVeiculo) {
            if (vavulas == veiculo.getVavulas()) {
                listaAux.add(veiculo);
            }
        }
        if (!listaAux.isEmpty()) {
            listaVeiculo.clear();
            listaVeiculo = listaAux;
        }
    }

    public void getListaVeiCil(int cilindros) {
        List<Veiculo> listaAux = new ArrayList<Veiculo>();
        for (Veiculo veiculo : listaVeiculo) {
            if (cilindros == veiculo.getCilindros()) {
                listaAux.add(veiculo);
            }
        }
        if (!listaAux.isEmpty()) {
            listaVeiculo.clear();
            listaVeiculo = listaAux;
        }
    }

    public void getListaVeiPla(String placa) {
        for (Veiculo veiculo : listaVeiculo) {
            if (placa.equalsIgnoreCase(veiculo.getPlaca())) {
                listaVeiculo.clear();
                listaVeiculo.add(veiculo);
                return;
            }
        }
    }

    public void getListaVeiCha(String chassi) {
        List<Veiculo> listaAux = new ArrayList<Veiculo>();
        for (Veiculo veiculo : listaVeiculo) {
            if (chassi.equalsIgnoreCase(veiculo.getChassi())) {
                listaAux.add(veiculo);
            }
        }
        if (!listaAux.isEmpty()) {
            listaVeiculo.clear();
            listaVeiculo = listaAux;
        }
    }

    public void getListaVeiRen(String renavam) {
        List<Veiculo> listaAux = new ArrayList<Veiculo>();
        for (Veiculo veiculo : listaVeiculo) {
            if (renavam.equalsIgnoreCase(veiculo.getRenavam())) {
                listaAux.add(veiculo);
            }
        }
        if (!listaAux.isEmpty()) {
            listaVeiculo.clear();
            listaVeiculo = listaAux;
        }
    }

    public void getListaVeiTip(String tipo) {
        List<Veiculo> listaAux = new ArrayList<Veiculo>();
        for (Veiculo veiculo : listaVeiculo) {
            if (tipo.equalsIgnoreCase(veiculo.getTipo())) {
                listaAux.add(veiculo);
            }
        }
        if (!listaAux.isEmpty()) {
            listaVeiculo.clear();
            listaVeiculo = listaAux;
        }
    }

    public void getListaVeiCPFCNPJ(String cpfCnpj) {
        List<Veiculo> listaAux = new ArrayList<Veiculo>();
        for (Veiculo veiculo : listaVeiculo) {
            if (cpfCnpj.equalsIgnoreCase(veiculo.getCpfCNPJCliente())) {
                listaAux.add(veiculo);
            }
        }
        if (!listaAux.isEmpty()) {
            listaVeiculo.clear();
            listaVeiculo = listaAux;
        }
    }
}
