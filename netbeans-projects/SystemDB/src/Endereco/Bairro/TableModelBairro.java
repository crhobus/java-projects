package Endereco.Bairro;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelBairro extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private BairroControl controle;

    public TableModelBairro(BairroControl controle) {
        this.controle = controle;
        try {
            controle.listarBairros();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeBairroCadas();
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
                return "Bairro";
            case 2:
                return "CEP";
            case 3:
                return "Cidade";
            case 4:
                return "Estado";
            case 5:
                return "Região";
            default:
                return "País";
        }
    }
}
