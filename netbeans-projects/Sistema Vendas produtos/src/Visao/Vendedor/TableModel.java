package Visao.Vendedor;

import Controle.VendedorControl;
import javax.swing.table.*;

public class TableModel extends AbstractTableModel {

    private VendedorControl controle;

    public TableModel(VendedorControl controle) {
        this.controle = controle;
    }

    public int getRowCount() {
        return controle.tamanho();
    }

    public int getColumnCount() {
        return 16;
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
                return "Setor";
            case 3:
                return "Comissão";
            case 4:
                return "Empresa";
            case 5:
                return "IE";
            case 6:
                return "CNPJ";
            case 7:
                return "Endereço";
            case 8:
                return "Bairro";
            case 9:
                return "Numero";
            case 10:
                return "CEP";
            case 11:
                return "Cidade";
            case 12:
                return "Estado";
            case 13:
                return "Fone";
            case 14:
                return "Fax";
            default:
                return "E-Mail";
        }
    }
}
