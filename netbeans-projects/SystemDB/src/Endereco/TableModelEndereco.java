package Endereco;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelEndereco extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private EnderecoControl controle;

    public TableModelEndereco(EnderecoControl controle) {
        this.controle = controle;
        try {
            controle.listarEnderecos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeEnderecoCadas();
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
                return "Endereço";
            case 2:
                return "Bairro";
            case 3:
                return "CEP";
            case 4:
                return "Cidade";
            case 5:
                return "Estado";
            case 6:
                return "Região";
            default:
                return "País";
        }
    }
}
