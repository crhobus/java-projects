package view.endereco;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import dbOracle.EnderecoDAO;

public class TableModelEndereco extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private EnderecoDAO enderecoDAO;

    public TableModelEndereco(EnderecoDAO enderecoDAO) {
        this.enderecoDAO = enderecoDAO;
        try {
            enderecoDAO.listEnderecos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public int getRowCount() {
        return enderecoDAO.getQtdadeEnderecosCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        try {
            return enderecoDAO.conteudoTableModel(linha, coluna);
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
                return "Endereço";
            case 2:
                return "Bairro";
            case 3:
                return "CEP";
            default:
                return "Cidade";
        }
    }
}
