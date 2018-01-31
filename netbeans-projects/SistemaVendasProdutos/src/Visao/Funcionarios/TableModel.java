package Visao.Funcionarios;

import Controle.FuncionarioControl;
import javax.swing.table.*;

public class TableModel extends AbstractTableModel {

    private FuncionarioControl controle;

    public TableModel(FuncionarioControl controle) {
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
                return "Data Admissão";
            case 3:
                return "Função";
            case 4:
                return "CPF";
            case 5:
                return "RG";
            case 6:
                return "Sexo";
            case 7:
                return "Endereço";
            case 8:
                return "Número";
            case 9:
                return "Bairro";
            case 10:
                return "CEP";
            case 11:
                return "Cidade";
            case 12:
                return "Estado";
            case 13:
                return "Complemento";
            case 14:
                return "Fone";
            case 15:
                return "Celular";
            case 16:
                return "Data Nascimento";
            case 17:
                return "Salário";
            default:
                return "E-Mail";
        }
    }
}
