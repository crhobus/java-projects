package view.endereco.bairro;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import dbOracle.BairroDAO;

public class TableModelBairro extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private BairroDAO bairroDAO;

    public TableModelBairro(BairroDAO bairroDAO) {
        this.bairroDAO = bairroDAO;
        try {
            bairroDAO.listBairros();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return bairroDAO.getQtdadeBairrosCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        try {
            return bairroDAO.conteudoTableModel(linha, coluna);
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
                return "Bairro";
            case 2:
                return "CEP";
            default:
                return "Cidade";
        }
    }
}
