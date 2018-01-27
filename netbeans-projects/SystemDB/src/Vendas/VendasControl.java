package Vendas;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import Modelo.Cliente;
import Modelo.Vendas;

public class VendasControl {

    private List<Vendas> listaVendas;
    private VendasDAO vendasDAO;
    private SimpleDateFormat formatDate;

    public VendasControl(Connection con) {
        vendasDAO = new VendasDAO(con);
        formatDate = new SimpleDateFormat("dd/MM/yyyy");
    }

    public boolean insertVenda(Vendas vendas) throws SQLException {
        return vendasDAO.insertVenda(vendas);
    }

    public boolean updateVenda(Vendas vendas) throws SQLException {
        return vendasDAO.updateVenda(vendas);
    }

    public boolean isVendaVazio() throws SQLException {
        return vendasDAO.isVendaVazio();
    }

    public int getProxCodVenda() throws SQLException {
        return vendasDAO.getProxCodVenda();
    }

    public void updateSusbTotalVenda(double subTotal, int codVenda) throws SQLException {
        vendasDAO.updateSusbTotalVenda(subTotal, codVenda);
    }

    public void updateParcelasResVenda(int parcelasRes, int codVenda, String situacao) throws SQLException {
        vendasDAO.updateParcelasResVenda(parcelasRes, codVenda, situacao);
    }

    public int getQtdadeClieCadas() {
        return listaVendas.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Vendas vendas = listaVendas.get(linha);
        Cliente clie = vendas.getCliente();
        switch (coluna) {
            case 0:
                return vendas.getNumVenda();
            case 1:
                return formatDate.format(vendas.getDataEmissao());
            case 2:
                return vendas.getSituacao();
            case 3:
                return vendas.getFuncionario().getNome();
            case 4:
                return vendas.getTipo();
            case 5:
                if (clie == null) {
                    return "Cliente não cadastrado";
                } else {
                    return clie.getNome();
                }
            default:
                if (clie == null) {
                    return "Cliente não cadastrado";
                } else {
                    return clie.getCpfCnpj();
                }
        }
    }

    public void limparLista() {
        listaVendas.clear();
    }

    public void listarVendas() throws SQLException {
        listaVendas = vendasDAO.listVendas();
    }

    public void getLista(final int coluna, final String s, final int n) throws SQLException {
        listaVendas = vendasDAO.getLista(coluna, s, n);
    }

    public Vendas getListaPosicao(int posicao) {
        return listaVendas.get(posicao);
    }
}
