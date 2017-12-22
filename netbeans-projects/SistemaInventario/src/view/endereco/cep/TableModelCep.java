package view.endereco.cep;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import dbOracle.CepDAO;

public class TableModelCep extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private CepDAO cepDAO;

    public TableModelCep(CepDAO cepDAO) {
        this.cepDAO = cepDAO;
        try {
            cepDAO.listCeps();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return cepDAO.getQtdadeCepsCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        try {
            return cepDAO.conteudoTableModel(linha, coluna);
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
                return "CEP";
            default:
                return "Cidade";
        }
    }
}
