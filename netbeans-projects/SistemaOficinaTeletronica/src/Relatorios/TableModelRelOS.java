package Relatorios;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Modelo.OrdemServico;

public class TableModelRelOS extends AbstractTableModel {

    private static final long serialVersionUID = 7209345655087822941L;
    private List<OrdemServico> lista = new ArrayList<OrdemServico>();
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");

    public void addLista(List<OrdemServico> lista) {
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
        return 17;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        OrdemServico os = lista.get(linha);
        switch (coluna) {
            case 0:
                return os.getNumeroOs();
            case 1:
                return os.getSituacao();
            case 2:
                return formatDate.format(os.getDataGeracao());
            case 3:
                return os.getCpfCnpjClie();
            case 4:
                return os.getNomeApa();
            case 5:
                return os.getMarcaApa();
            case 6:
                return os.getModeloApa();
            case 7:
                return os.getAssistenciaApa();
            case 8:
                if (os.isOrcamentoApa()) {
                    return " -";
                }
                return "";
            case 9:
                return formatDate.format(os.getData());
            case 10:
                return os.getHoraInicial();
            case 11:
                return os.getHoraFinal();
            case 12:
                return os.getTempo();
            case 13:
                return NumberFormat.getCurrencyInstance().format(os.getValorPorHora());
            case 14:
                return NumberFormat.getCurrencyInstance().format(os.getTotal());
            case 15:
                return os.getCondPagto();
            default:
                return os.getFormaPagto();
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Número OS";
            case 1:
                return "Situação";
            case 2:
                return "Data Geração";
            case 3:
                return "Cliente";
            case 4:
                return "Nome Aparelho";
            case 5:
                return "Marca";
            case 6:
                return "Modelo";
            case 7:
                return "Assistência";
            case 8:
                return "Orçamento";
            case 9:
                return "Data Operação";
            case 10:
                return "Hora Inicial";
            case 11:
                return "Hora Final";
            case 12:
                return "Tempo";
            case 13:
                return "Valor Por Hora";
            case 14:
                return "Total";
            case 15:
                return "Cond. Pagto";
            default:
                return "Forma Pagto";
        }
    }
}
