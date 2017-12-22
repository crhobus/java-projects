package view.empresa;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import dbOracle.EmpresaDAO;

public class TableModelEmpresa extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private EmpresaDAO empresaDAO;

    public TableModelEmpresa(EmpresaDAO empresaDAO) {
        this.empresaDAO = empresaDAO;
        try {
            empresaDAO.listEmpresas();
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
        return empresaDAO.getQtdadeEmpresasCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        try {
            return empresaDAO.conteudoTableModel(linha, coluna);
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
