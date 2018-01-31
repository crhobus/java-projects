package Funcionarios;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelFunc extends AbstractTableModel {

    private static final long serialVersionUID = -113151957698818642L;
    private FuncionarioControl controle;

    public TableModelFunc(FuncionarioControl controle) {
        this.controle = controle;
        try {
            this.controle.listarFuncionarios();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 21;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeFuncCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return controle.conteudoTableModel(linha, coluna);
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
                return "N° Carteira Trab.";
            case 5:
                return "Cargo";
            case 6:
                return "Salário";
            case 7:
                return "Sexo";
            case 8:
                return "CEP";
            case 9:
                return "Endereço";
            case 10:
                return "Bairro";
            case 11:
                return "Número";
            case 12:
                return "Complemento";
            case 13:
                return "Cidade";
            case 14:
                return "UF";
            case 15:
                return "Fone";
            case 16:
                return "Celular";
            case 17:
                return "E-Mail";
            case 18:
                return "Idade";
            case 19:
                return "Data Contr./Demis.";
            default:
                return "Ativo";
        }
    }
}
