package ConsultaHistorico;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private List<Historico> lista = new ArrayList<Historico>();
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");

    public void add(int codigoOS, int unidade, Date dataGeracao, Date data, String situacao, String nomeItem,
            String descricao, double valorUnit, double valorTotal) {
        Historico historico = new Historico();
        historico.setCodigoOS(codigoOS);
        historico.setUnidade(unidade);
        historico.setDataGeracao(dataGeracao);
        historico.setData(data);
        historico.setSituacao(situacao);
        historico.setNomeItem(nomeItem);
        historico.setDescricao(descricao);
        historico.setValorUnit(valorUnit);
        historico.setValorTotal(valorTotal);
        lista.add(historico);
    }

    public void limparLista() {
        lista.clear();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Historico historico = lista.get(linha);
        switch (coluna) {
            case 0:
                return historico.getCodigoOS();
            case 1:
                return formatDate.format(historico.getDataGeracao());
            case 2:
                return historico.getSituacao();
            case 3:
                return historico.getUnidade();
            case 4:
                return historico.getNomeItem();
            case 5:
                return historico.getDescricao();
            case 6:
                return NumberFormat.getCurrencyInstance().format(historico.getValorUnit());
            case 7:
                return NumberFormat.getCurrencyInstance().format(historico.getValorTotal());
            default:
                return formatDate.format(historico.getData()) + " as " + formatHora.format(historico.getData());
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Nº OS";
            case 1:
                return "Gerado em";
            case 2:
                return "Situação";
            case 3:
                return "Unidade";
            case 4:
                return "Nome Item";
            case 5:
                return "Descrição";
            case 6:
                return "Valor Unit";
            case 7:
                return "Valor Total";
            default:
                return "Data";
        }
    }

    private class Historico {

        private int codigoOS, unidade;
        private Date dataGeracao, data;
        private String Situacao, nomeItem, descricao;
        private double valorUnit, valorTotal;

        private int getCodigoOS() {
            return codigoOS;
        }

        private void setCodigoOS(int codigoOS) {
            this.codigoOS = codigoOS;
        }

        private int getUnidade() {
            return unidade;
        }

        private void setUnidade(int unidade) {
            this.unidade = unidade;
        }

        private Date getDataGeracao() {
            return dataGeracao;
        }

        private void setDataGeracao(Date dataGeracao) {
            this.dataGeracao = dataGeracao;
        }

        private Date getData() {
            return data;
        }

        private void setData(Date data) {
            this.data = data;
        }

        private String getSituacao() {
            return Situacao;
        }

        private void setSituacao(String situacao) {
            Situacao = situacao;
        }

        private String getNomeItem() {
            return nomeItem;
        }

        private void setNomeItem(String nomeItem) {
            this.nomeItem = nomeItem;
        }

        private String getDescricao() {
            return descricao;
        }

        private void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        private double getValorUnit() {
            return valorUnit;
        }

        private void setValorUnit(double valorUnit) {
            this.valorUnit = valorUnit;
        }

        private double getValorTotal() {
            return valorTotal;
        }

        private void setValorTotal(double valorTotal) {
            this.valorTotal = valorTotal;
        }
    }
}
