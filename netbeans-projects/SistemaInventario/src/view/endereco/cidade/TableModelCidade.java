package view.endereco.cidade;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import dbOracle.CidadeDAO;

public class TableModelCidade extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private CidadeDAO cidadeDAO;

    public TableModelCidade(CidadeDAO cidadeDAO) {
        this.cidadeDAO = cidadeDAO;
        try {
            cidadeDAO.listCidades();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return cidadeDAO.getQtdadeCidadesCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        try {
            return cidadeDAO.conteudoTableModel(linha, coluna);
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
            default:
                return "Cidade";
        }
    }
}
