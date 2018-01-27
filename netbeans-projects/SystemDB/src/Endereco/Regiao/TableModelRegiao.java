package Endereco.Regiao;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelRegiao extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private RegiaoControl controle;

    public TableModelRegiao(RegiaoControl controle) {
        this.controle = controle;
        try {
            controle.listarRegioes();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeRegiaoCadas();
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
                return "Região";
            default:
                return "País";
        }
    }
}
