package view.funcionario;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import dbOracle.FuncionarioDAO;

public class TableModelFuncionario extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private FuncionarioDAO funcionarioDAO;

    public TableModelFuncionario(FuncionarioDAO funcionarioDAO) {
        this.funcionarioDAO = funcionarioDAO;
        try {
            funcionarioDAO.listFuncionarios();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 13;
    }

    @Override
    public int getRowCount() {
        return funcionarioDAO.getQtdadeFuncionariosCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        try {
            return funcionarioDAO.conteudoTableModel(linha, coluna);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Codigo";
            case 1:
                return "Nome";
            case 2:
                return "CPF";
            case 3:
                return "RG";
            case 4:
                return "Endereço";
            case 5:
                return "Bairro";
            case 6:
                return "Número";
            case 7:
                return "CEP";
            case 8:
                return "Cidade";
            case 9:
                return "Contato";
            case 10:
                return "Cargo";
            case 11:
                return "Salario";
            default:
                return "Ativo";
        }
    }
}
