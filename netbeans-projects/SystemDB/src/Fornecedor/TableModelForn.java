package Fornecedor;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelForn extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private FornecedorControl controle;

    public TableModelForn(FornecedorControl controle) {
        this.controle = controle;
        try {
            controle.listarForn();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 14;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeFornCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return controle.conteudoTableModel(linha, coluna);
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Código";
            case 1:
                return "Nome";
            case 2:
                return "CNPJ";
            case 3:
                return "IE";
            case 4:
                return "IM";
            case 5:
                return "Tipo Fornecedor";
            case 6:
                return "Endereço";
            case 7:
                return "Bairro";
            case 8:
                return "Número";
            case 9:
                return "CEP";
            case 10:
                return "Cidade";
            case 11:
                return "Estado";
            case 12:
                return "E-Mail";
            default:
                return "Fone Comercial";
        }
    }
}
