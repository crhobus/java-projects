package ConsultaHistoricoOS;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Modelo.ItensOS;

public class TableModelHistoricoOS extends AbstractTableModel {

    private static final long serialVersionUID = -4515438758067047679L;
    private List<ItensOS> lista = new ArrayList<ItensOS>();
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");

    public void add(ItensOS itensOS) {
        lista.add(itensOS);
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        ItensOS itensOS = lista.get(linha);
        switch (coluna) {
            case 0:
                return itensOS.getUnidade();
            case 1:
                return itensOS.getNomeItem();
            case 2:
                return itensOS.getDescricao();
            case 3:
                return NumberFormat.getCurrencyInstance().format(itensOS.getValorUnit());
            case 4:
                return NumberFormat.getCurrencyInstance().format(itensOS.getValorTotal());
            default:
                return formatDate.format(itensOS.getData()) + " as " + formatHora.format(itensOS.getData());
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Unidade";
            case 1:
                return "Nome Item";
            case 2:
                return "Descrição";
            case 3:
                return "Valor Unit";
            case 4:
                return "Valor Total";
            default:
                return "Data";
        }
    }
}
