package Vendas;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import Modelo.ItensVenda;

public class ItensVendaControl {

    private List<ItensVenda> listaItensVenda;
    private ItensVendaDAO itensVendaDAO;

    public ItensVendaControl(Connection con) {
        listaItensVenda = new ArrayList<ItensVenda>();
        itensVendaDAO = new ItensVendaDAO(con);
    }

    public boolean insertItensVenda(ItensVenda itensVenda) throws Exception {
        if (itensVendaDAO.insertItensVenda(itensVenda)) {
            listaItensVenda.add(itensVenda);
            return true;
        }
        return false;
    }

    public int getProxCodItemVenda() throws Exception {
        return itensVendaDAO.getProxCodItemVenda();
    }

    public int getQtdadeItensCadas() {
        return listaItensVenda.size();
    }

    public int getQtdadeItensVenda(int numeroVen) throws Exception {
        return itensVendaDAO.getQtdadeItensVenda(numeroVen);
    }

    public Object conteudoTableModel(int linha, int coluna) {
        ItensVenda itensVenda = listaItensVenda.get(linha);
        switch (coluna) {
            case 0:
                return itensVenda.getUnidade();
            case 1:
                return itensVenda.getNomeProd();
            case 2:
                return itensVenda.getDescricao();
            case 3:
                return itensVenda.getValorUnit();
            default:
                return itensVenda.getValorTotal();
        }
    }

    public void limparLista() {
        listaItensVenda.clear();
    }

    public void listarItens(int numeroVen) throws Exception {
        listaItensVenda = itensVendaDAO.listItensVenda(numeroVen);
    }

    public List<ItensVenda> getListaItens(int numeroVen) throws Exception {
        return itensVendaDAO.listItensVenda(numeroVen);
    }

    public double getTotalItensVenda() {
        double d = 0;
        for (ItensVenda itensVenda : listaItensVenda) {
            d += itensVenda.getValorTotal();
        }
        return d;
    }
}
