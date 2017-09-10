package AssistenciaTecRandom;

import javax.swing.table.*;

public class TableModelClie extends AbstractTableModel {

    private ClienteControl control;

    public TableModelClie(ClienteControl control) {
        this.control = control;
    }

    public int getRowCount() {
        return control.tamanho();
    }

    public int getColumnCount() {
        return 13;
    }

    public Object getValueAt(int linha, int coluna) {
        return control.conteudoLinha(linha, coluna);
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Codigo";
            case 1:
                return "Nome";
            case 2:
                return "Endereço";
            case 3:
                return "Bairro";
            case 4:
                return "Número";
            case 5:
                return "Cidade";
            case 6:
                return "Estado";
            case 7:
                return "CEP";
            case 8:
                return "Fone";
            case 9:
                return "Celular";
            case 10:
                return "Solicitante";
            case 11:
                return "RG";
            default:
                return "CPF";
        }
    }
}
