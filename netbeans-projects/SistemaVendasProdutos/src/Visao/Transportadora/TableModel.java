package Visao.Transportadora;

import Controle.TransportadoraControl;
import javax.swing.table.*;

public class TableModel extends AbstractTableModel {

    private TransportadoraControl controle;

    public TableModel(TransportadoraControl controle) {
        this.controle = controle;
    }

    public int getRowCount() {
        return controle.tamanho();
    }

    public int getColumnCount() {
        return 10;
    }

    public Object getValueAt(int linha, int coluna) {
        return controle.conteudoLinha(linha, coluna);
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
                return "Número";
            case 4:
                return "Bairro";
            case 5:
                return "CEP";
            case 6:
                return "Cidade";
            case 7:
                return "Estado";
            case 8:
                return "Fone";
            default:
                return "E-Mail";
        }
    }
}
