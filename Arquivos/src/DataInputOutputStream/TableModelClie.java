package DataInputOutputStream;

import javax.swing.table.*;

public class TableModelClie extends AbstractTableModel {

    private ClienteControl controle;

    public TableModelClie(ClienteControl controle) {
        this.controle = controle;
    }

    public int getRowCount() {
        return controle.tamanho();
    }

    public int getColumnCount() {
        return 19;
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
                return "RG";
            case 3:
                return "CPF";
            case 4:
                return "Profissão";
            case 5:
                return "Empresa";
            case 6:
                return "Fone Empresa";
            case 7:
                return "Sexo";
            case 8:
                return "CEP";
            case 9:
                return "Endereço";
            case 10:
                return "Bairro";
            case 11:
                return "Numero";
            case 12:
                return "Complemento";
            case 13:
                return "Cidade";
            case 14:
                return "Estado";
            case 15:
                return "Fone";
            case 16:
                return "Celular";
            case 17:
                return "E-Mail";
            default:
                return "Descrição";
        }
    }
}
