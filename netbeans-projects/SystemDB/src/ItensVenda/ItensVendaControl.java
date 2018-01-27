package ItensVenda;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import Modelo.ItensVenda;

public class ItensVendaControl {

    private List<ItensVenda> listaItens;
    private ItensVendaDAO itensVendaDAO;

    public ItensVendaControl(Connection con) {
        listaItens = new ArrayList<ItensVenda>();
        itensVendaDAO = new ItensVendaDAO(con);
    }

    public boolean insertItemVenda(ItensVenda itensVenda) throws SQLException {
        return itensVendaDAO.insertItemVenda(itensVenda);
    }

    public int getQtdadeItensCadas() {
        return listaItens.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        ItensVenda item = listaItens.get(linha);
        double precoUnitCom = item.getProduto().getPrecoCompra();
        precoUnitCom += precoUnitCom * (item.getProduto().getPercentualLucro() / 100);
        precoUnitCom += precoUnitCom * (item.getProduto().getIpi() / 100);
        precoUnitCom -= precoUnitCom * (item.getProduto().getDescontos() / 100);
        switch (coluna) {
            case 0:
                return item.getProduto().getProduto();
            case 1:
                return item.getProduto().getMarca() + " - " + item.getProduto().getModelo() + " - " + item.getProduto().getDescricao();
            case 2:
                return item.getQtdade();
            case 3:
                return NumberFormat.getCurrencyInstance().format(precoUnitCom);
            default:
                return NumberFormat.getCurrencyInstance().format((item.getQtdade() * precoUnitCom));
        }
    }

    public boolean isListaVazia() {
        return listaItens.isEmpty();
    }

    public void limparLista() {
        listaItens.clear();
    }

    public void listarItens(int codVenda) throws SQLException {
        listaItens = itensVendaDAO.listItens(codVenda);
    }
}
