package Visao.Fornecedores;

import Controle.FornecedorControl;
import javax.swing.table.*;

public class TableModel extends AbstractTableModel {

    private FornecedorControl controle;

    public TableModel(FornecedorControl controle) {
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
                return "Sigla";
            case 3:
                return "Comissão";
            case 4:
                return "CNPJ";
            case 5:
                return "IE";
            case 6:
                return "Endereço";
            case 7:
                return "Número";
            case 8:
                return "Bairro";
            case 9:
                return "CEP";
            case 10:
                return "Cidade";
            case 11:
                return "Estado";
            case 12:
                return "Empresa";
            case 13:
                return "Fone";
            case 14:
                return "Celular";
            default:
                return "E-Mail";
        }
    }
}
