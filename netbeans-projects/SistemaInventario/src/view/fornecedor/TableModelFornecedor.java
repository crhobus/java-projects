package view.fornecedor;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import dbOracle.FornecedorDAO;

public class TableModelFornecedor extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private FornecedorDAO fornecedorDAO;

    public TableModelFornecedor(FornecedorDAO fornecedorDAO) {
        this.fornecedorDAO = fornecedorDAO;
        try {
            fornecedorDAO.listFornecedores();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 11;
    }

    @Override
    public int getRowCount() {
        return fornecedorDAO.getQtdadeFornecedoresCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        try {
            return fornecedorDAO.conteudoTableModel(linha, coluna);
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
                return "CNPJ";
            case 3:
                return "IE";
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
                return "E-mail";
            default:
                return "Contato";
        }
    }
}
