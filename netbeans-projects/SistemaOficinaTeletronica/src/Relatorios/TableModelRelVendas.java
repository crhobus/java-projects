package Relatorios;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Modelo.Venda;

public class TableModelRelVendas extends AbstractTableModel {

    private static final long serialVersionUID = -6321850652441383254L;
    private List<Venda> lista = new ArrayList<Venda>();
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");

    public void addLista(List<Venda> lista) {
        this.lista = lista;
    }

    public void limparLista() {
        lista.clear();
    }

    public boolean isListaVazio() {
        return lista.isEmpty();
    }

    @Override
    public int getColumnCount() {
        return 13;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Venda venda = lista.get(linha);
        switch (coluna) {
            case 0:
                return venda.getNumeroVen();
            case 1:
                return venda.getSituacao();
            case 2:
                return venda.getTipo();
            case 3:
                return venda.getVendedor();
            case 4:
                return venda.getCpfCnpjClie();
            case 5:
                return formatDate.format(venda.getDataEntrega());
            case 6:
                return NumberFormat.getCurrencyInstance().format(venda.getTotal());
            case 7:
                return venda.getCondPagto();
            case 8:
                return venda.getFormaPagto();
            case 9:
                return NumberFormat.getCurrencyInstance().format(venda.getParcelasMes());
            case 10:
                if (venda.getJuros() != -1) {
                    return venda.getJuros();
                }
                return 0.0;
            case 11:
                return venda.getParcelasRes();
            default:
                return NumberFormat.getCurrencyInstance().format(venda.getValorRestante());
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Número Venda";
            case 1:
                return "Situação";
            case 2:
                return "Tipo";
            case 3:
                return "Vendedor";
            case 4:
                return "Cliente";
            case 5:
                return "Data Entrega";
            case 6:
                return "Total";
            case 7:
                return "Cond. Pagto";
            case 8:
                return "Forma Pagto";
            case 9:
                return "Parcelas Mês";
            case 10:
                return "Juros";
            case 11:
                return "Parcelas Restantes";
            default:
                return "Valor Restante";
        }
    }
}
