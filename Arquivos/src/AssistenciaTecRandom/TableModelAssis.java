package AssistenciaTecRandom;

import javax.swing.table.*;

public class TableModelAssis extends AbstractTableModel {

    private AssistenciaControl contol;

    public TableModelAssis(AssistenciaControl contol) {
        this.contol = contol;
    }

    public int getRowCount() {
        return contol.tamanho();
    }

    public int getColumnCount() {
        return 21;
    }

    public Object getValueAt(int linha, int coluna) {
        return contol.conteudoLinha(linha, coluna);
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return Integer.class;
            case 1:
                return Integer.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return String.class;
            case 7:
                return String.class;
            case 8:
                return String.class;
            case 9:
                return String.class;
            case 10:
                return String.class;
            case 11:
                return Boolean.class;
            case 12:
                return String.class;
            case 13:
                return String.class;
            case 14:
                return Double.class;
            case 15:
                return Double.class;
            case 16:
                return Double.class;
            case 17:
                return String.class;
            case 18:
                return String.class;
            case 19:
                return String.class;
            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        return coluna == 11 || coluna == 12 || coluna == 13 || coluna == 14 || coluna == 15 || coluna == 17 || coluna == 18 || coluna == 19 || coluna == 20;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        contol.alterarCelula(valor, linha, coluna);
        fireTableRowsUpdated(linha, linha);
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Codigo";
            case 1:
                return "Cod Clie";
            case 2:
                return "Nome Clie";
            case 3:
                return "Atendente";
            case 4:
                return "Tipo Serviço";
            case 5:
                return "Equipamento";
            case 6:
                return "Marca";
            case 7:
                return "Modelo";
            case 8:
                return "Setor";
            case 9:
                return "Descrição";
            case 10:
                return "Anexo";
            case 11:
                return "Orçamento";
            case 12:
                return "Serviço Realizado";
            case 13:
                return "Incluido por";
            case 14:
                return "Sub-Total";
            case 15:
                return "Descontos";
            case 16:
                return "Total";
            case 17:
                return "Cond. Pagto";
            case 18:
                return "Forma Pagto";
            case 19:
                return "Situação";
            default:
                return "Salvar";
        }
    }
}
