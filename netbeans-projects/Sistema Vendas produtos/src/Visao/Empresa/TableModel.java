package Visao.Empresa;

import Controle.EmpresaControl;
import javax.swing.table.*;

public class TableModel extends AbstractTableModel {

    private EmpresaControl controle;

    public TableModel(EmpresaControl controle) {
        this.controle = controle;
    }

    public int getRowCount() {
        return controle.tamanho();
    }

    public int getColumnCount() {
        return 14;
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
                return "CNPJ";
            case 3:
                return "IE";
            case 4:
                return "Endereço";
            case 5:
                return "Número";
            case 6:
                return "Bairro";
            case 7:
                return "CEP";
            case 8:
                return "Cidade";
            case 9:
                return "Estado";
            case 10:
                return "Fone";
            case 11:
                return "Fax";
            case 12:
                return "E-Mail";
            default:
                return "Observações";
        }
    }
}
